package part2oop

object Inheritance extends App {

  // single class inheritance
  class Animal {
    def eat = println("nomnom")
    private def sound = println("meow") // only accessible within this class scope.
    protected def isCat = true // only available within the class and subclasses.
    val creatureType = "wild"
  }

  class Cat extends Animal { // Cat is a subclass of Animal class and Animal is a superclass of Cat class.

    def crunch = {
      isCat
      println("crunch crunch")
    }

  }
  val cat = new Cat
  cat.eat // this is possible because of inheritance.
  cat.crunch

  //constructors
  class Person(name: String, age: Int)
  class Adult(name: String, age: Int, idCard: String) extends Person(name, age) // if you don't pass the name and age parameters then it won't compile

  // overriding

  class Dog(override val creatureType: String) extends Animal {
    override def eat = {
      super.eat // uses the eat method from the superclass
      println("crunch crunch")
    }
    //override val creatureType: String = "domestic" // one way of doing it, prints domestic because it overrides, if it is commented out then it would print wild as initially declared.
  }

  val dog = new Dog("K9")
  dog.eat
  println(dog.creatureType)

  // type substitution (broad: polymorphism)

  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat // prints crunch crunch as it uses dog method.

  // overRIDING vs overLOADING

  // super

  // preventing overrides
  // 1 - use final on member
  // 2 - use final on entire class
  // 3 - seal the class = extend classes in THIS FILE, prevent extension in other files

}
