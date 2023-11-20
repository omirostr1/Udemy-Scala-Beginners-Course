package part2oop

object MethodNotations extends App{

  class Person(val name: String, favoriteMovie: String, val age: Int) {
    def likes(movie: String): Boolean = {
      movie == favoriteMovie
    }
    def hangOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    // others can be used as well, such like _,-, & etc
    def +(nickname: String): Person = new Person(s"$name ($nickname)", favoriteMovie, age)
    def unary_! : String = s"$name, what is going on?!"
    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)
    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie" //created by trainer
    def learns(language: String) : String = s"${this.name} is learning $language"
    def learnScala()  = {
      this learns "Scala"
    }
    def apply(integer: Int) : String = s"${this.name} watched $favoriteMovie $integer times."
  }


  val mary = new Person("Mary", "Inception", 22)
  println(mary.likes("Inception"))
  println(mary likes "Inception") //equivalent
  // infix notation = operator notation (syntactic sugar)
  //work only with functions that take only one parameter.

  //"operators" in Scala
  val tom = new Person("Tom", "Flight Club", 18)
  val ken = new Person("Ken", "Barbie", 31)
  println(mary + ken)
  println(mary hangOutWith tom)

  println(1 + 2)
  println(1.+(2))

  // ALL OPERATORS ARE METHODS.
  // Akka actors have ! ?

  //prefix notation (another form of syntactic sugar)
  val x = -1 //equivalent with 1.unary_-
  val y = 1.unary_-
  // unary_ prefix only works with -, +, ~, !


  println(!mary)
  println(mary.unary_!)

  //postfix notation
  println(mary.isAlive)
  //println(mary isAlive) it is avoided as it can cause errors and misunderstandings.

  //apply
  println(mary.apply())
  println(mary()) //equivalent

  /*

    1. Overload the + operator
    mary + "the rockstar" => new person "Mary (the rockstar)"

    2. Add an age to the Person class
       Add a unary + operator => new person with age +1
       +mary => mary with the age increment

    3. Add a "learns" method in the Person class => "Mary learns Scala"
       Add a learnScala method, calls learns, method with "Scala".
       Use it in postfix notation

    4. Overload the apply method
    mary.apply(2) => "Mary watched Inception 2 times"

   */


  println((mary + "The Rockstar")())
  println((+mary).age)
  println((mary.unary_+).age)
  println(mary.learnScala)
  println(mary.apply(2))
  println(mary(10)) //easier call of apply method.


}
