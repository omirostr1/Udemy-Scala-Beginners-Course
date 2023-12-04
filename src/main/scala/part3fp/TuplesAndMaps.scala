package part3fp

object TuplesAndMaps extends App{

  // tuples = finite ordered "lists"
  val aTuple = new Tuple2(2, "hello, Scala") // Tuple2[Int, String] = (Int, String)
  // can avoid keywords "new" and/or "Tuple2" when defining a new Tuple

  println(aTuple._1) // 1st element which is 2
  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap) // ("hello, Scala", 2)

  // Maps - associate keys -> values
  val aMap: Map[String, Int] = Map()

  val phonebook = Map(("Jim", 555), ("Daniel", 789), "Omiros" -> 357)
  // a -> b is sugar for (a, b)
  println(phonebook)

}
