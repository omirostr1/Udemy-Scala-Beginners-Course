package lecture.part1basics

import lecture.part1basics.Functions.isPrime

import scala.annotation.tailrec

object Recursion extends App{
  def factorial(n: Int): Int =
    if (n<=1) 1
    else {
      println("Computing facorial of " + n + " - I first need factorial of " + (n-1))
      val result = n * factorial(n-1)
      println("Computed factorial of " + n)
      result
    }


  def factorialFunction(n: BigInt): BigInt = {
    @tailrec
    def factorialHelper(x: BigInt, accumulator: BigInt) : BigInt =
      if (x <= 1) accumulator
      else factorialHelper(x - 1 , x * accumulator)  //TAIL RECURSION = use recursive call as the LAST expression
    factorialHelper(n, 1)
  }

  //println(factorialFunction(200))

// WHEN YOU NEED LOOPS, USE TAIL RECURSION.

/*
  1. Concatenate a string n times
  2. IsPrime function tail recursive
  3. Fibonacci function, tail recursive
 */

  //1.
  def concatenationFunction(initialWords: String, number: Int): String = {
    def concatenationHelper(accumulator: String, number: Int): String = {
      if (number == 0) accumulator
      else concatenationHelper(accumulator + number, number - 1)
    }
    concatenationHelper(initialWords, number)
  }
  println(concatenationFunction("Let's start the final countdown ", 10))

  //OR

  def concatenateTailrec(aString: String, n: Int, accumulator: String) : String =
    if (n <=0) accumulator
    else concatenateTailrec(aString, n-1, aString + accumulator)

  println(concatenateTailrec("hello", 3, ""))

  //2.
  def isPrime(y: Int): Boolean = {
    def division(y: Int, numberDividedBy: Int): Boolean = {
      if ((numberDividedBy > 1) & (y % (numberDividedBy) == 0)) false
      else if (numberDividedBy == 1) true
      else division(y, numberDividedBy - 1)
    }

    division(y, y - 1)
  }

  println(isPrime(37))

  //3.
  // Remember: the number of recursive calls you will have is the number
  // of accumulators you will need.

  def fibonacci(x: Int) : Int = {
    def helper(i: Int, last: Int, nextToLast: Int) : Int = {
      if (i >= x) last
      else helper( i + 1, last + nextToLast, last)
    }
     if (x <= 2) 1
     else helper(2,1,1)
  }

  println(fibonacci(8))

}


