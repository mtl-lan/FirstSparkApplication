package com.scalabasic.homework

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

object d_inclass_breakoutRoom1 {

  def main(args: Array[String]) {
    Logger.getLogger("org").setLevel(Level.ERROR)
    // Breakout Room 1
    val session = SparkSession
      .builder
      .master("local[2]")
      .appName("word_1")
      .getOrCreate()
    // Read "The Hunger Games.txt" into a RDD
    val input = session.read.textFile("Files/TheHungerGames.txt")
    import session.implicits._
    // Split words and CountByValue -> then print results
    val words = input.flatMap(x => x.split(" "))
    val wordCounts = words.rdd.countByValue()
    wordCounts.foreach(println)
    //Use regular express to extract only the words
    val cleanedWords = input.flatMap(x => x.split("\\W+")).map(x => x.toLowerCase()).rdd.countByValue()
    cleanedWords.foreach(println)
    //sort the results to show the popular words
    val sortedCleanWords = cleanedWords.toList.sortBy(word => word._2)
    // update the code and print in ascending order
    sortedCleanWords.foreach(println)

  }

}
