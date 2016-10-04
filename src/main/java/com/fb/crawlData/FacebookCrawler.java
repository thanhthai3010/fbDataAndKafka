package com.fb.crawlData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.spark.api.java.function.ForeachFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import com.google.gson.Gson;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.json.JsonObject;
import com.restfb.types.Post;


public class FacebookCrawler {

  private static final String REACTIONS_PARAMETERS =
      "reactions.type(LIKE).limit(0).summary(total_count).as(like)"
          + ",reactions.type(LOVE).limit(0).summary(total_count).as(love)"
          + ",reactions.type(WOW).limit(0).summary(total_count).as(wow)"
          + ",reactions.type(HAHA).limit(0).summary(total_count).as(haha)"
          + ",reactions.type(SAD).limit(0).summary(total_count).as(sad)"
          + ",reactions.type(ANGRY).limit(0).summary(total_count).as(angry)";
  private static final String STATUS_PARAMETERS =
      "message,link,permalink_url,created_time,type,name,id,comments.limit(0).summary(true),"
          + "shares,reactions.limit(0).summary(true),from";
  private static final SparkSession session = SparkUtils.getSparkSession();
  private static final String CSV_FORMAT = "com.databricks.spark.csv";
  final static Gson gSon = new Gson();

  /**
   * Facebook library
   */
  private static FacebookClient facebookClient23;

  public Dataset<Row> convertedStatus;

  private int count = 0;

  /**
   * default constructor
   */
  public FacebookCrawler() {}

  public void scrapeFacebookPageFeedStatus(String group_id, String access_token) {

    facebookClient23 = new DefaultFacebookClient(access_token, Version.VERSION_2_6);

    // get all Post of fbPage
    Connection<Post> listPostOfFanPage = facebookClient23.fetchConnection(group_id + "/feed",
        Post.class, Parameter.with("fields", STATUS_PARAMETERS), Parameter.with("limit", 100),
        Parameter.with("since", "1474794129"));

    // add the first post to List.
    // analyze the first post.
    this.convertedStatus =
        session.createDataFrame(saveToFile(listPostOfFanPage.getData()), StatusData.class);

    String nextPageURL = listPostOfFanPage.getNextPageUrl();

    while (nextPageURL != null) {
      Connection<Post> listPostNextPage =
          facebookClient23.fetchConnectionPage(nextPageURL, Post.class);

      // check if it still has next page.
      List<Post> postsNextPage = listPostNextPage.getData();
      if (postsNextPage.size() > 0) {
        this.convertedStatus
            .union(session.createDataFrame(saveToFile(postsNextPage), StatusData.class));
      }
      nextPageURL = listPostNextPage.getNextPageUrl();
    }
  }


  public List<StatusData> saveToFile(List<Post> listPostInPage) {

    List<StatusData> returnList = new ArrayList<>();

    for (Post post : listPostInPage) {
      JsonObject reactionObject = facebookClient23.fetchObject(post.getId(), JsonObject.class,
          Parameter.with("fields", REACTIONS_PARAMETERS));
      ReactionDetail reactionDetail =
          gSon.fromJson(reactionObject.toString(), ReactionDetail.class);

      int num_likes = reactionDetail.getLike().getSummary().getTotal_count();
      int num_loves = reactionDetail.getLove().getSummary().getTotal_count();
      int num_wows = reactionDetail.getWow().getSummary().getTotal_count();
      int num_hahas = reactionDetail.getHaha().getSummary().getTotal_count();
      int num_sads = reactionDetail.getSad().getSummary().getTotal_count();
      int num_angrys = reactionDetail.getAngry().getSummary().getTotal_count();

      DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String dateTime = formatter.format(post.getCreatedTime());

      StatusData returnObject = new StatusData(post.getId(), post.getMessage(),
          post.getFrom().getName(), post.getName(), post.getType(), post.getLink(),
          post.getPermalinkUrl(), dateTime, post.getReactionsCount(), post.getCommentsCount(),
          post.getSharesCount(), num_likes, num_loves, num_wows, num_hahas, num_sads, num_angrys);

      returnList.add(returnObject);
    }

    // convertedStatus.union(session.createDataFrame(returnList, StatusData.class));

    // convertedStatus.toJavaRDD().saveAsTextFile("/home/thaint/data/output/statusFB");

    // convertedStatus.coalesce(1).write().format(CSV_FORMAT).option("header", "true")
    // .mode(SaveMode.Append).save("/home/thaint/data/output/statusFB");

    count++;
    System.out.println("Number of processed " + count);
    return returnList;
  }

  public static void main(String[] args) {
    FacebookCrawler fbCrawler = new FacebookCrawler();

    String group_id = "262700667105773";
    String access_token =
        "EAAJbNXsfn4MBALv9SppE27g9OpWLCmcmGggZAQZB07dUZCnhSO7CRMFcKTjXUG40mifwAfAw57FC1DYNiKwZBFWgysWEQMfA6ZBDKvfnUxhSijZCdY36vjpj3rpdXfCeeyw2aZBiO5g1qa6rZCC1IozXopUsTnDxsQoZD";

    fbCrawler.scrapeFacebookPageFeedStatus(group_id, access_token);

    fbCrawler.convertedStatus.createOrReplaceTempView("VNEXPRESS");
    // Dataset<Row> query = session.sql("SELECT status_message, num_reactions, status_link"
    // + " FROM VNEXPRESS" + " ORDER BY num_reactions DESC LIMIT 5");

    Dataset<Row> query = session.sql("SELECT status_message, num_reactions, status_link"
        + " FROM VNEXPRESS" + " WHERE num_reactions > (SELECT AVG(num_reactions) FROM VNEXPRESS)"
        + " ORDER BY num_reactions DESC LIMIT 20");

    query.foreach(new ForeachFunction<Row>() {

      /**
       * 
       */
      private static final long serialVersionUID = 1L;

      @Override
      public void call(Row t) throws Exception {
        System.out.println(t.getString(0) + t.getLong(1) + t.getString(2));
      }
    });

    // Dataset<Row> convertedStatus = session.createDataFrame(result, StatusData.class);
    //
    // convertedStatus.coalesce(1).write().format(CSV_FORMAT).option("header", "true")
    // .mode(SaveMode.Append).save("/home/thaint/data/output/statusFB");

  }
}
