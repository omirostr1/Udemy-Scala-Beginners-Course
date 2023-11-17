package part2oop

object OOBasics extends App {

  val person = new Person("John", 19)
  println(person.age)
  println(person.x)
  person.greet("Daniel")
  person.greet()
  val second_person = new Person("Ben")
  println(second_person.age)

  val writer1 = new Writer("William", "Shakespeare", 1564)
  writer1.fullName()

  val novel1 = new Novel("Romeo and Juliet", 1597)
  val novel1copy = new Novel("Romeo and Juliet", 1650)

  println(novel1.authorAge(writer1))
  novel1.isWrittenBy(writer1)
  novel1.copy()

  val counter = new Counter(3)
  counter.accumulator(integer = 3)
  counter.incrementCounter(3)
  counter.decrementCounter(3)

}

//constructor
class Person (name: String, val age: Int = 0) {
  //body can have anything, which will run when a new instance of the class is created.
  val x = 2
  println(4)

  //method
  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

  //overloading

  def greet(): Unit = println(s"Hi, I am ${this.name}") //here this is not needed as the
  //name parameter is only the one of the Person class.
  // def greet(): Int = 42 will confuse the compiler as it returns a different value from
  // the other functions that have the same name.

  //multiple constructors
  def this(name: String) = this(name, 0)

  def this() = this("John Doe")
}
  /*
    Novel and a Writer

    Writer: name, surname, year of birth
      - Method fullname

    Novel: name, year of release, author
      - Methods authorAge, isWrittenBy(author), copy(new year of release) = new instance of novel

   */

  /*

  Counter class
  - receives an Int value
  - method current count
  - method to increment-decrement => new Counter
  -overload inc/dec to receive an amount

   */

  class Writer(val name: String, surname: String, val yearOfBirth: Int) {
    def fullName() : String = {
      name + " " + surname
    }

  }

  class Novel(name: String, yearOfRelease: Int) {
    def authorAge(writer: Writer): Int = {
      yearOfRelease - writer.yearOfBirth
    }

    def isWrittenBy(writer: Writer) = {
      val fullname = writer.fullName()
      println(s"The novel $name is written by $fullname")
    }

    def copy() = {
      val yearOfRelease = 1650
      println(yearOfRelease)
    }

  }

class Counter(integer : Int) {
  def accumulator(integer: Int) : Unit = {
    if (integer >0) {
      println(integer)
      accumulator(integer - 1)
    }
  }

  def incrementCounter(integer : Int) = {
    accumulator(integer + 1)
  }

  def decrementCounter(integer: Int) = {
    accumulator(integer - 1)
  }


}


// class parameters are NOT fields (so without the val it cannot be accessed using the .)
// class fields are those with the command val in front of them and can be accessed with the .)




