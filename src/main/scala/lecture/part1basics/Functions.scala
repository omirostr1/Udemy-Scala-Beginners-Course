package lecture.part1basics

import scala.io.StdIn.readLine
import scala.io.StdIn.readInt


object Functions extends App{
  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("hello", 3))

  def aParameterlessFunction() : Int = 42
  println(aParameterlessFunction())
  println(aParameterlessFunction)

  def aRepeatedFunction(aString: String, n: Int): String = {
    if ( n == 1) aString
    else aString + aRepeatedFunction(aString, n-1)
  }

  println(aRepeatedFunction("hello", 3))

  //WHEN YOU NEED LOOPS, USE RECURSION.
  //Best practise is to always specify the return type of a function.
  //However, you can omit it only if the function is non-recursive.

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)
  aFunctionWithSideEffects("Hey")

  def aBigFunction(n: Int) : Int = {
    def aSmallerFunction(a: Int, b: Int) : Int = a + b

    aSmallerFunction(n, n-1)
  }

  /*
    1. A greeting function (name,age) => "Hi, my name is $name and I am $age years old."
    2. Factorial function 1*2*3*4*...*n
    3. A Fibonacci function
        f(1) = 1
        f(2) = 1
        f(n) = f(n-1) + f(n-2)
    4. Tests if a number is prime.
  */

  //1.
    def greetingFunction(name: String, age: Int) : String = {
      "Hi, my name is " + name + " and I am " + age + " years old."

    }
 //Inputs from user for function
//  val name = readLine()
//  val age = readInt()
//  println(greetingFunction(name, age))

  //2.
  var factorial: Int = 1
  def factorialFunction(n: Int) : Int = {
    if (n <= 0) {
      factorial
    } else {
      factorial = factorial * n
      factorialFunction(n-1)
    }
  }
  println(factorialFunction(5))

  //3.
  val fibonacciZero = 0
  val fibonacciOne = 1
  def fibonacciFunction(x: Int): Int = {
    if (x == 0) {
      fibonacciZero
    } else if (x == 1) {
      fibonacciOne
    } else if (x == 2) {
      fibonacciOne + fibonacciZero
    } else {
      fibonacciFunction(x-1) + fibonacciFunction(x-2)
    }
  }

  println(fibonacciFunction(8))

  //4.
  def isPrime(y: Int): Boolean = {
    def division(y: Int, numberDividedBy: Int): Boolean = {
      if ((numberDividedBy > 1) & (y % (numberDividedBy) == 0)) false
      else if (numberDividedBy == 1) true
      else division(y, numberDividedBy - 1)
    }
    division(y,y-1)
  }

  println(isPrime(37))

}
