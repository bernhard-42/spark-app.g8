/* SparkApp.scala */
package com.example
import org.apache.spark.sql.SparkSession
//import com.databricks.dbutils_v1.DBUtilsHolder.dbutils

object SparkApp2 {
  
  def main(args: Array[String]) = {
    val folder = args(0)
    val version = args(1)

    val spark = SparkSession.builder.appName("Simple Application").getOrCreate()
    val secret = com.databricks.dbutils_v1.DBUtilsHolder.dbutils.secrets.get("a_scope", "a_secret")
  }
}