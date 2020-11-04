package com.scalabasic.homework


import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

object c_insurance {

  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)
    //1. Read insurance.csv file
      val spark = SparkSession.builder().master("local[2]").appName("Insurance_csv").getOrCreate()
      val InsuranceDFCsv = spark.read.format("csv")
      .option("sep", ",")
      .option("inferSchema", "true")
      .option("header", "true")
      .load("Files/insurance.csv")

    InsuranceDFCsv.printSchema()
    InsuranceDFCsv.show()

    // 2. Print the size
    println("Table Size")
    println(InsuranceDFCsv.count())

    // 3. Print sex and count of sex (use group by in sql)
    println("Female vs Male")
    InsuranceDFCsv.groupBy("sex").count().show()

    // 4. Filter smoker=yes and print again the sex,count of sex
    println("Female smoker vs Male smoker")
    InsuranceDFCsv.filter(InsuranceDFCsv("smoker") === "yes").groupBy("sex").count().show()

    // 5. Group by region and sum the charges (in each region), then print rows by descending order (with respect to sum)
    println("Total charges on different regions")
    InsuranceDFCsv.groupBy("region").sum("charges").show()

  }
}
