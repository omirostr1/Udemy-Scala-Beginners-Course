package exercises

abstract class MyListUsingGenerics[+A] {

  /*

    head = first element of the list
    tail = remainder of the list
    isEmpty => is this list empty
    add(int) => new list with this element added
    toString => a string representation of the list

  */

  def head : A
  def tail: MyListUsingGenerics[A]
  def isEmpty: Boolean
  def add[B >: A](integer: B) : MyListUsingGenerics[B]
  def printElements : String
  override def toString: String = "[" + printElements + "]"

}

object Empty1 extends MyListUsingGenerics[Nothing] {

  def head: Nothing = throw new NoSuchElementException
  def tail: MyListUsingGenerics[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](integer: B): MyListUsingGenerics[B] = new NonEmpty1(integer, Empty1)
  def printElements : String = ""

}

class NonEmpty1[+A](h: A, t: MyListUsingGenerics[A]) extends MyListUsingGenerics[A] {

  def head: A = h
  def tail: MyListUsingGenerics[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](integer: B): MyListUsingGenerics[B] = new NonEmpty1(integer, this)
  def printElements : String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements

}
object ListTest1 extends App {

  val listOfIntegers: MyListUsingGenerics[Int] = new NonEmpty1(1, new NonEmpty1(2, new NonEmpty1(3, Empty1)))
  val listOfStrings: MyListUsingGenerics[String] = new NonEmpty1("Hello", new NonEmpty1("Scala", Empty1))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)

}

