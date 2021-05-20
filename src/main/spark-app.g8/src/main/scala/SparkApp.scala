/* SparkApp.scala */
package com.example
import org.apache.spark.sql.SparkSession
//import com.databricks.dbutils_v1.DBUtilsHolder.dbutils

object SparkApp2 {
  
  def main(args: Array[String]) = {
    val folder = args(0)
    val version = args(1)

    val adls = "my data lake"
    val tenant = "ab..90"

    val spark = SparkSession.builder.appName("Simple Application").getOrCreate()

    spark.conf.set(s"fs.azure.account.auth.type.$adls.dfs.core.windows.net", 
                  "OAuth")
    spark.conf.set(s"fs.azure.account.oauth.provider.type.$adls.dfs.core.windows.net", 
                  "org.apache.hadoop.fs.azurebfs.oauth2.ClientCredsTokenProvider")
    spark.conf.set(s"fs.azure.account.oauth2.client.endpoint.$adls.dfs.core.windows.net", 
                  "https://login.microsoftonline.com/$tenant/oauth2/token")
    spark.conf.set(s"fs.azure.account.oauth2.client.id.$adls.dfs.core.windows.net", 
                   com.databricks.dbutils_v1.DBUtilsHolder.dbutils.secrets.get("rest-kv", "client-id"))
    spark.conf.set(s"fs.azure.account.oauth2.client.secret.$adls.dfs.core.windows.net", 
                   com.databricks.dbutils_v1.DBUtilsHolder.dbutils.secrets.get("rest-kv", "client-secret"))

    try {                  
      val df = spark.read
          .option("header","true")
          .option("inferSchema", "true")
          .csv("abfss://data@" + adls + ".dfs.core.windows.net/" + folder + "/iris.csv")
      df.take(5).foreach(println)
    } catch {
      case e: Exception => e.printStackTrace
    }
  }
}