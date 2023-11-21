package part2oop

object Objects{

  // SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static")

  object Person { // type + its only instance
    // "static"/"class" -level functionality
    val N_EYES = 2
    def canFly: Boolean = false

    // factory method
    def from(mother: Person, father: Person): Person = new Person("Bobbie")
  }

  class Person(val name: String) {
    // instance-level functionality
  }
  // COMPANIONS

  def main(args: Array[String]) : Unit = {

    println (Person.N_EYES)
    println (Person.canFly)

    // Scala object = SINGLETON INSTANCE

    val mary = Person
    val john = Person
    println (mary == john) //prints true to the console

    val mary1 = new Person ("Mary")
    val john1 = new Person ("John")
    println (mary1 == john1) //prints false to the console because they refer to different instances.

    val bobbie = Person.from (mary1, john1) // can do this with the apply method.

  }

  // Scala Applications = Scala object with
  // def main(args: Array[String]) : Unit

}
