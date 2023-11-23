package exercises

abstract class MyList {

  /*

    head = first element of the list
    tail = remainder of the list
    isEmpty => is this list empty
    add(int) => new list with this element added
    toString => a string representation of the list

  */

  def head : Int
  def tail: MyList
  def isEmpty: Boolean
  def add(integer: Int) : MyList
  def printElements : String
  override def toString: String = "[" + printElements + "]"

}

object Empty extends MyList {

  def head: Int = throw new NoSuchElementException
  def tail: MyList = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add(integer: Int): MyList = new NonEmpty(integer, Empty)
  def printElements : String = ""

}

class NonEmpty(h: Int, t: MyList) extends MyList {

  def head: Int = h
  def tail: MyList = t
  def isEmpty: Boolean = false
  def add(integer: Int): MyList = new NonEmpty(integer, this)
  def printElements : String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements

}

object ListTest extends App {
  val listtest = new NonEmpty(0, Empty)
  println(listtest.head)
  println(listtest.tail)
  println(listtest.isEmpty)
  println(listtest.add(5))

  val listtest2 = Empty
  println(listtest2.isEmpty)
  println(listtest2.add(5))
  //println(listtest2.head) // throw the exception set.
  //println(listtest2.tail) // throw the exception set.
  //println(listtest2.string) // throw the exception set.

  val listtest3 = new NonEmpty(2, listtest)
  println(listtest3.head)
  println(listtest3.tail)
  println(listtest3.isEmpty)
  println(listtest3.add(5))
  println(listtest3.tail.head) // prints the head of the tail.
  println(listtest3.add(9).head) // same as the head method.

  println(listtest.toString)

}


