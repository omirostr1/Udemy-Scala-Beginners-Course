package part2oop

object AnonymousClasses extends App{

  abstract class Animal {
    def eat: Unit
  }

  // anonymous class
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("hahahahah")
  }

  /*
      equivalent with the following, which is what the compiler does:

      class part2oop.AnonymousClasses$$anon$1 extends Animal {
        override def eat: Unit = println("hahahahah")
      }
      val funnyAnimal = new part2oop.AnonymousClasses$$anon$1

   */

  println(funnyAnimal.getClass)

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name, how can I help")
  }

  //  val jim = new Person {
  //    def sayHi: Unit = println(s"Hi, my name is Jim, how can I be of service")
  //  }
  // this won't compile as we are not passing all the parameters of the class
  // which in this case is only the argument "name"

    val jim = new Person("Jim") {
      override def sayHi: Unit = println(s"Hi, my name is Jim, how can I be of service")
    }

}
