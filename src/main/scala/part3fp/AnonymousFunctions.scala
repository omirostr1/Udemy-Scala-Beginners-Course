package part3fp

object AnonymousFunctions extends App{

  // anonymous function (LAMBDA)
  val doubler = (x: Int) => x * 2

  // the above is equivalent to this
  val doubler1 = new Function1[Int, Int] {
    override def apply(number: Int) : Int = number * 2
  }

  // multiple params in a lambda
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  // no params
  val justDoSomething: () => Int = () => 3

  // careful
  println(justDoSomething)    // prints the function itself
  println(justDoSomething())  // calls the function and prints the return value

  // curly braces with lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // MOAR syntactic sugar
  val niceIncrementer : Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a,b) => a + b

  /*
    1.  MyList: replace all FunctionX calls with lambdas
    2.  Rewrite the "special" adder as an anonymous function
   */

  // Solution to exercise 1 can be found in file MyListFunctional as comments

  // Solution to exercise 2

  val specialAdder = (integer: Int) => (anotherInteger: Int) => integer + anotherInteger
  println(specialAdder(3)(4))


}
