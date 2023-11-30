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

}
