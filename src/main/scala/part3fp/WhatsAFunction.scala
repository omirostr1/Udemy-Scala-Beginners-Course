package part3fp

object WhatsAFunction extends App{

  // DREAM: use functions as first class elements
  // problem: oop mindset

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  // function types = Function[A, B]
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("3") + 4)

  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  // Function types Function2[A, B, R] === (A,B) => R

  // ALL SCALA FUNCTIONS ARE OBJECTS

  /*
    1. a function which takes 2 strings and concatenates them
    2. transform the MyPredicate and MyTransformer into function types
    3. define a function which takes an int and returns another function which takes an int and returns an int
          - what's the type of this function
          - how to do it
   */

  // Solution to exercise 1

  val concatenator: ((String, String) => String) = new Function2[String, String, String] {
    override def apply(string1: String, string2: String): String = string1 + string2
  }

  println(concatenator("Omiros ", "Trypatsas"))

  // Solution to exercise 2 can be found in MyListFunctional under exercises package

  // Solution to exercise 3

  // Function[Int, Function[Int, Int]]
  val speicalFunction: (Function1[Int, Function1[Int, Int]]) = new Function1[Int, Function1[Int, Int]] {
    override def apply(integer: Int): Function1[Int, Int]= new Function1[Int, Int] {
      override def apply(anotherInteger: Int): Int = integer + anotherInteger
    }
  }

  val adder3 = speicalFunction(3)
  println(adder3(4))
  println(speicalFunction(3)(4))  // curried function

  trait MyFunction[A, B] {
    def apply(element: A): B
  }

}
