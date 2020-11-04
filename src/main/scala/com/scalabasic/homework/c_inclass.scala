package com.scalabasic.homework

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import scala.math.random


object c_inclass {
    case class Number(i: Int, english: String, french: String)
    case class Record(key: Int, value: String)
    def main(args: Array[String]): Unit = {
        Logger.getLogger("org").setLevel(Level.ERROR)
        // Exe1: local_pi
        var count = 0
        for (i <- 1 to 100000) {

            val x = random * 2 - 1
            val y = random * 2 - 1
            if (x * x + y * y <= 1) count += 1
        }
        println(s"Pi is ${4 * count / 100000}")

        // Exe2: spark_pi
        val spark = SparkSession.builder().master("local[2]").appName("Spark pi").getOrCreate()

        val slices =if(args.length >0) args(0).toInt else 2
        val n= math.min(100000L *slices, Int.MaxValue).toInt
        val count2=spark.sparkContext.parallelize(1 until n, slices).map{i=>
            val x= random*2-1
            val y = random*2-1
            if (x*x+y*y<=1) 1 else 0
        }.reduce(_+_)
        println(s"Pi is ${4*count2/(n-1)}")
        spark.stop()

        //Exe3: Dataset1
        val spark2 =
            SparkSession.builder()
              .appName("Dataset-CaseClass")
              .master("local[4]")
              .getOrCreate()
        import spark2.implicits._
        val numbers = Seq(
          Number(1, "one", "un"),
          Number(2, "two", "deux"),
          Number(3, "three", "trois"))
        val numberDS=numbers.toDS()
        println("Dataset Types")
        numberDS.dtypes.foreach(println(_))
        println("filter one column and fetch another ones")
        numberDS.select($"english",$"french").where($"i" >1).show()
        println("sparkSession dataset")
        val anotherDS=spark2.createDataset(numbers)
        println("Spark Dataset Types")
        anotherDS.dtypes.foreach(println(_))

        //Exe 4: Dataset2
        val df = spark2.createDataFrame((1 to 100).map(i => Record(i, s"val_$i")))
        // Any RDD containing case classes can be used to create a temporary view.  The schema of the
        // view is automatically inferred using scala reflection.
        df.createOrReplaceTempView("records")
        // Once tables have been registered, you can run SQL queries over them.
        println("Result of SELECT *:")
        spark2.sql("SELECT * FROM records").collect().foreach(println)
        // Aggregation queries are also supported.
        val count3 = spark2.sql("SELECT COUNT(*) FROM records").collect().head.getLong(0)
        println(s"COUNT(*): $count3")
        // The results of SQL queries are themselves RDDs and support all normal RDD functions. The
        // items in the RDD are of type Row, which allows you to access each column by ordinal.
        val rddFromSql = spark2.sql("SELECT key, value FROM records WHERE key < 10")
        println("Result of RDD.map:")
        rddFromSql.rdd.map(row => s"Key: ${row(0)}, Value: ${row(1)}").collect().foreach(println)
        // Queries can also be written using a LINQ-like Scala DSL.
        df.where($"key" === 1).orderBy($"value".asc).select($"key").collect().foreach(println)
        spark2.stop()

    }
}
