package com.scalabasic.homework


import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

object b_part2 extends App{

  //Exercise 1 (Mandatory)
  //Submit three in-class exercises:
  //● The third one in .scala format

  Logger.getLogger("org").setLevel(Level.ERROR)
  val session = SparkSession
    .builder()
    .master("local")
    .appName("WordCount")
    .getOrCreate()

  val input = session.read.textFile("Files/TheHungerGames.txt")
  import session.implicits._
  val words = input.flatMap( x => x.split(" "))
  val wordCounts = words.rdd.countByValue()

  // print out the result
  wordCounts.foreach(println)

  //Exercise 4 (Optional)
  //Improve the wordCount example by
  //1. Remove the non-letter characters
  val letterWords = words.filter(_.matches("[A-Za-z]+")).map(_.toLowerCase())
  //2. Merge uppercase and lowercase words
  val mergedCounts = letterWords.rdd.countByValue().toList
  //3. Sort (descending) the pairs
  val sortedWordCounts = mergedCounts.sortWith(_._2 >_._2).take(20)

  // print out the results
  sortedWordCounts.foreach(println)

}

