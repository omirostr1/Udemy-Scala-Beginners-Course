package part3fp

object MapFlatmapFilterFor extends App{

  val list = List(1,2,3)
  println(list)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a member "))

  // filter
  println(list.filter(_ % 2 ==0))

  // flatMap
  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  // print all combinations between two lists
  val numbers = List(1,2,3,4)
  val chars = List("a", "b", "c", "d")
  // List("a1", "a2", "a3", ... , "d3", "d4")

  val combo = (x: String) => List(x + numbers(0).toString, x + numbers(1).toString, x + numbers(2).toString, x + numbers(3).toString)
  println(chars.flatMap(combo))

  // or val combinations = numbers.flatMap(n => chars.map(c => "" + c + n))

  // "iterating"
  val colors = List("black", "white")
  val newcombo = numbers.flatMap(n => chars.flatMap(c => colors.map(color => "" + c + n + "-" + color)))
  println(newcombo)

  // foreach
  list.foreach(println)

  // for-comprehensions
  val forCombinations = for {
    n <- numbers
    c <- chars
    color <- colors
  } yield "" + c + n + "-" + color
  println(forCombinations)

  val forCombinationsWithCondition = for {
    n <- numbers if n % 2 == 0  // same as using .filter(_ % 2 == 0) on a flatMap (see line below)
    // val newcombo = numbers.filter(_ % 2 == 0).flatMap(n => chars.flatMap(c => colors.map(color => "" + c + n + "-" + color)))
    c <- chars
    color <- colors
  } yield "" + c + n + "-" + color
  println(forCombinationsWithCondition)

  for {
    n <- numbers
  } println(n)

  // syntax overload
  println(list.map ( x =>
    x * 2
  ))

  /*
    1.  MyList supports for comprehensions?
        map(f: A => B) => MyList[B]
        filter(p: A => Boolean) => MyList[A]
        flatMap(f: A => MyList[B]) => MyList[B]
    2.  A small collection of at most ONE element - Maybe[+T]
          - map, flatMap, filter
   */

  // Solution to exercise 1 can be found in MyListFunctional file

  // Solution to exercise 2 can be found in Maybe file under exercise package

}
