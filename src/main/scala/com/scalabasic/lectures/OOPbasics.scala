package com.scalabasic.lectures

object OOPbasics {


}


class Writer(first_name:String, surname:String, val year_of_birth:Int) {

  def fullname():String = first_name + "_" + surname

}

class Novel (book_name:String, year_of_release: Int, author:Writer) {
  def authorAge(): Int = year_of_release - author.year_of_birth
  def isWrittenBy ():Unit = println(s"This novel: $book_name is written by "+ author.fullname())
  def copy(new_year_of_release:Int):Novel = {
    new Novel(this.book_name, new_year_of_release,this.author)
  }


}