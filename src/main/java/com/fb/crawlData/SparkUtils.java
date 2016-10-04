package com.fb.crawlData;

import org.apache.spark.sql.SparkSession;

public class SparkUtils {

  /*
   * SparkSession - entry point to access Spark SQL.
   */
  private static SparkSession sparkSession = initSparkSession();

  private static SparkSession initSparkSession() {
    SparkSession session = SparkSession.builder().master("local[4]").appName("get report")
        .config("spark.executor.memory", "4g").config("spark.driver.allowMultipleContexts", true)
        .getOrCreate();

    return session;
  }

  public static SparkSession getSparkSession() {
    return sparkSession;
  }
}
