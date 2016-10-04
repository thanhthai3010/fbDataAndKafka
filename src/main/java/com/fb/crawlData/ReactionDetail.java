package com.fb.crawlData;

import java.io.Serializable;
import java.util.List;

public class ReactionDetail implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private ReactionCount like;
  private ReactionCount love;
  private ReactionCount wow;
  private ReactionCount haha;
  private ReactionCount sad;
  private ReactionCount angry;
  private String id;

  public ReactionCount getLike() {
    return like;
  }

  public void setLike(ReactionCount like) {
    this.like = like;
  }

  public ReactionCount getLove() {
    return love;
  }

  public void setLove(ReactionCount love) {
    this.love = love;
  }

  public ReactionCount getWow() {
    return wow;
  }

  public void setWow(ReactionCount wow) {
    this.wow = wow;
  }

  public ReactionCount getHaha() {
    return haha;
  }

  public void setHaha(ReactionCount haha) {
    this.haha = haha;
  }

  public ReactionCount getSad() {
    return sad;
  }

  public void setSad(ReactionCount sad) {
    this.sad = sad;
  }

  public ReactionCount getAngry() {
    return angry;
  }

  public void setAngry(ReactionCount angry) {
    this.angry = angry;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ReactionDetail() {
    super();
    // TODO Auto-generated constructor stub
  }

  public ReactionDetail(ReactionCount like, ReactionCount love, ReactionCount wow,
      ReactionCount haha, ReactionCount sad, ReactionCount angry, String id) {
    super();
    this.like = like;
    this.love = love;
    this.wow = wow;
    this.haha = haha;
    this.sad = sad;
    this.angry = angry;
    this.id = id;
  }



  public static class ReactionCount implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private List<String> data;
    private TotalCount summary;

    public List<String> getData() {
      return data;
    }

    public void setData(List<String> data) {
      this.data = data;
    }

    public TotalCount getSummary() {
      return summary;
    }

    public void setSummary(TotalCount summary) {
      this.summary = summary;
    }

    public ReactionCount() {
      super();
      // TODO Auto-generated constructor stub
    }

    public ReactionCount(List<String> data, TotalCount summary) {
      super();
      this.data = data;
      this.summary = summary;
    }

    public static class TotalCount implements Serializable {
      /**
       * 
       */
      private static final long serialVersionUID = 1L;
      private int total_count;

      public int getTotal_count() {
        return total_count;
      }

      public void setTotal_count(int total_count) {
        this.total_count = total_count;
      }

      public TotalCount(int total_count) {
        super();
        this.total_count = total_count;
      }

      public TotalCount() {
        super();
        // TODO Auto-generated constructor stub
      }

    }
  }
}
