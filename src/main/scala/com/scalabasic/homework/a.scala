package com.scalabasic.homework

import scala.annotation.tailrec

object a extends App {

  // Exercise 1 (Mandatory)
  val x: Double = 42.354562
  val y: Int = 3

  //The round value of x is 42.35
  println(f"$x%1.2f")
  //The left zero padding version of y is 0003 (
  println(f"$y%04d")

  // Exercise 2 (Mandatory)
  //Write a function to compute factorial (5! = 5*4*3*2*1)
  //Then write another function to call fact function and println few examples (i.e, 6,8,4.52).
  //Your program should return NA if the input is not integer.

  // Solution: use recursion Function i/o For or While loop to avoid side effects (return Unit type)
  // return BigInt is because the return value might be big.
  def factorial(n: Int): BigInt = {
    if (n == 1) 1
    else n * factorial(n - 1)
  }

  def factorial_num(input: Double): String = {
    val integerNum = input.toInt
    if (input > 0 && input - integerNum < 1e-7) {
      factorial(integerNum).toString
    } else {
      "NA"
    }
  }

  println(factorial_num(6))
  println(factorial_num(8))
  println(factorial_num(4.52))
  println(factorial_num(-3))
  //  println(factorial_num(50000)) -- will crash because the stack overflow issue, we should do improvement in next Exe 3.

  //Exercise 3 (Mandatory)
  //Repeat the previous exercise by accepting the double numbers into the factorial
  //function. Convert them to integer before calling the fact function.

  // solution: use Tail Recursion to avoid stack overflow issue and before calling this fact function
  // And use standin to get number, more practical.

  @tailrec
  def factorialTailrec(n: Int, accumulator: BigInt = math.BigInt.int2bigInt(1)): BigInt = {
    if (n == 1) accumulator
    else factorialTailrec(n - 1, n * accumulator)
  }

  println("Enter a number (Int or Double) : ")
  val input: Double = scala.io.StdIn.readDouble()
  val transInt = input.toInt
  println(transInt + " ! = " + factorialTailrec(transInt))

  //Exercise 4 (Optional)
  //Write a code that prints out the first 10 values of the Fibonacci sequence.

  // Solution: Function definitions can have types with default values
  def fib(a: Int = 0, b: Int = 1, count: Int = 2): List[Int] = {

    // Calculate the next value
    // In functional programming we prefer immutability so always try to use val instead of var
    val c = a + b

    // Stopping criteria, send back a list containing the latest value
    if (count >= 9) {
      List(c)
    }

    // If this is the first iteration create the first few fibonacci numbers, and make a recursive call
    // Adding one list to another is done using the ++ function
    else if (a == 0 && b == 1) {
      List(a, b, c) ++ fib(b, c, count + 1)
    }

    // If this wasn't the first iteration, just add the latest element and make the recursive call
    else {
      c +: fib(b, c, count + 1)
    }
  }

  // Since we gave default values the function parameters, you don't need to pass any when you call the function here
  println("The first 10 values of the Fibonacci sequence are: "+fib())

  //Exercise 5 (Optional)
  //Write a function that takes the number and says here is the cube of the input:
  //● 5 -> 125 is the cube
  //● Retry doing it via lambda function

  println("Enter a number to calculator its cube: ")
  val inputNumber: Double = scala.io.StdIn.readDouble()

  // method 1: a normal function
  def cubeCalculator(x: Double): Double = {
    val cube: Double = x*x*x
    return cube
  }
  val cube: Double = cubeCalculator(inputNumber)
  println(s"The cube of input number: $input is $cube ")

  // method 2: lambda expression
  val cube2 = (x:Double) => x * x * x
  println(s"The cube of input number: $input is "+ cube2(inputNumber))
}
