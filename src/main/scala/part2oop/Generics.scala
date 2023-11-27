package part2oop

object Generics extends App{

  class MyList[+A] {
    // use the type A
    def add[B >: A] (element: B): MyList[B] = ???

    /*
      A = Cat
      B = Animal
     */

  }

  class MyMap[Key, Value]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // generic methods

  object MyList {
    //def empty[A] : MyList[A] = ???
  }

  //val emptyListOfIntegers = MyList.empty[Int]

  // variance problem

  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // 1. yes, List[Cat] extends List[Animal] = COVARIANCE
  class CovarianceList[+A]
  val animal: Animal = new Cat
  val animalList: CovarianceList[Animal] = new CovarianceList[Cat]
  // animalList.add(new Dog) ??? HARD QUESTION => we return a list of Animals

  // 2. NO = INVARIANCE
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  // 3. Hell, no! CONTRAVARIANCE
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  // bounded types
  class Cage[A <: Animal](Animal: A) // this reads as: class named Cage accepts parameter of type A which is a subtype of Animal class
  // upper bounded type in Animal class (opposite sign is the lower bounded)
  val cage = new Cage (new Dog)

  class Car
  // val newCage = new Cage(new Car) throws an error because Car is not a subclass of Animal.

  // expand MyList to be generic

}
