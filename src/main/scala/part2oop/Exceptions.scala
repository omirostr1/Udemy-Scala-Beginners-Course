package part2oop

import com.sun.nio.sctp.SctpSocketOption

object Exceptions extends App{

  val x: String = null
  // println(x.length)
  // this ^^ will crash with a NPE

  // 1. throwing exceptions

  // val aWeirdValue: String = throw new NullPointerException

  // throwable classes extend the Throwable class.
  // Exception and Error are the major Throwable subtypes.

  // 2. how to catch exceptions
  def getInt(withExceptions: Boolean) : Int =
    if (withExceptions) throw new RuntimeException("No int for you!")
    else 42

  val potentialFail =  try {
    // code that might throw an exception
    getInt(false)
  } catch {
    case e: RuntimeException => 43
  } finally {
    // code that will get executed NO MATTER WHAT
    // optional
    // does not influence the return type of this exception
    // use finally only for side effects
    println("finally")
  }

  println(potentialFail)

  // 3. how to define your own exceptions
  class MyException extends Exception
  val exception = new MyException

  //throw exception

  /*
    1. Crash your program with an OutOfMemoryError
    2. Crash with SOError
    3. PocketCalculator
          - add (x,y)
          - subtract (x,y)
          - multiply (x,y)
          - divide (x,y)
          where x and y are integers for simplicity

          Throw
              - OverflowException if add(x,y) exceeds Int.MAX_VALUE
              - UnderflowException if subtract (x,y) exceeds Int.MIN_VALUE
              - MatchCalculationException for division by 0
   */

  // Solution to exercise 1. (OOM)

  // val array = Array.ofDim(Int.MaxValue) this line causes a OutOfMemoryError because too much memory is required that exceeds VM limit.

  // Solution to exercise 2. (S0)

  def testFunc() : Unit = {
    def addition(integer: Int): Int = {
      println(integer + 100000)
//      //imePellos() if this line of code triggers, it causes a SOError due to bad recursive call (it calls itself forever).
      addition(integer)
    }
    addition(394020502)
    println("hey")
  }

  //testFunc()

  // Solution to exercise 3.

  class OverflowException extends Exception
  class UnderflowException extends Exception
  class MatchCalculationException extends RuntimeException("Division by zero")

  class PocketCalculator{

    def add(x: Int, y: Int): Unit = {

      val result = x + y

      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else println(result)

    }

    def subtract(x: Int, y: Int) : Unit = {

      val result = x - y

      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else println(result)

    }

    def multiply(x: Int, y: Int): Unit = {

      val result = x * y

      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else println(result)

    }

    def divide(x: Int, y: Int): Unit = {

      if (y == 0) throw new MatchCalculationException
      else println(x / y)

    }
  }


  val calculator = new PocketCalculator
  //calculator.add(2,Int.MaxValue)
  calculator.add(2,993453446)
  //calculator.subtract(-Int.MaxValue,7)
  calculator.subtract(10,7)
  calculator.multiply(2,3)
  //calculator.multiply(-Int.MaxValue,3)
  //calculator.multiply(-10,-Int.MaxValue)
  //calculator.multiply(Int.MaxValue,Int.MaxValue)
  calculator.divide(4,2)
  // calculator.divide(4,0)


}
