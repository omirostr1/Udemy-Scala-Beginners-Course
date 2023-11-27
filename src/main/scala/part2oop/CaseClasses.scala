package part2oop

object CaseClasses extends App{

  /*
    equals, hashCode, toString
   */

  case class Person(name: String, age: Int)

  // 1. class parameters are fields
  val jim = new Person("jim", 34)
  println(jim.name)

  // 2. sensible toString
  // println(instance) = println(instance.toString) // syntactic sugar
  println(jim)

  // 3. equals and hashCode implemented OOTB (Out Of The Box)
  val jim2 = new Person("Jim", 34)
  println(jim == jim2)

  // 4. CCs have handy copy method
  val jim3 = jim.copy()
  val jim4 = jim.copy(age = 25)
  println(jim3)
  println(jim4)

  // 5. CCs have companion objects
  val thePerson = Person
  val mary = Person("Mary", 23)

  // 6. CCs are serializable
  // Akka

  // 7. CCs have extractor patterns = CCs can be used in PATTERN MATCHING

  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }

  // All the above apply to an object, except #5 cause it is already an object on its own.

  /*
    Expand MyList - use case classes and case objects
   */

}
