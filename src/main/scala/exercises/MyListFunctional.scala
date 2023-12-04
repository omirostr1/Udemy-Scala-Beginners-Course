package exercises

abstract class MyListFunctional[+A] {

  /*

    head = first element of the list
    tail = remainder of the list
    isEmpty => is this list empty
    add(int) => new list with this element added
    toString => a string representation of the list

  */

  def head : A
  def tail: MyListFunctional[A]
  def isEmpty: Boolean
  def add[B >: A](integer: B) : MyListFunctional[B]
  def printElements : String
  override def toString: String = "[" + printElements + "]"

  def map[B](transformer: (A => B)) : MyListFunctional[B]
  def filter(predicate: A => Boolean) : MyListFunctional[A]
  def flatMap[B](transformer: A => MyListFunctional[B]): MyListFunctional[B]

  //concatenation
  def ++[B >: A](list: MyListFunctional[B]): MyListFunctional[B]

  //hofs
  def foreach(f: A => Unit) : Unit
  def sort(compare: (A, A) => Int) : MyListFunctional[A]

  def zipWith[B, C](list: MyListFunctional[B], g: (A, B) => C) : MyListFunctional[C]

  def fold[B](start: B)(function: (A, B) => B ) : B

}

case object Empty2 extends MyListFunctional[Nothing] {

  def head: Nothing = throw new NoSuchElementException
  def tail: MyListFunctional[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](integer: B): MyListFunctional[B] = new NonEmpty2(integer, Empty2)
  def printElements : String = ""

  def map[B](transformer: Nothing => B): MyListFunctional[B] = Empty2
  def filter(predicate: Nothing => Boolean): MyListFunctional[Nothing] = Empty2
  def flatMap[B](transformer: Nothing => MyListFunctional[B]): MyListFunctional[B] = Empty2

  def ++[B >: Nothing] (list: MyListFunctional[B]) : MyListFunctional[B] = list

  def foreach(f: Nothing => Unit): Unit = ()

  def sort(compare: (Nothing, Nothing) => Int): MyListFunctional[Nothing] = Empty2

  def zipWith[B, C](list: MyListFunctional[B], g: (Nothing, B) => C) : MyListFunctional[C] = {
    if (!list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else Empty2
  }

  def fold[B](start: B)(function: (Nothing, B) => B ) : B = start

}

case class NonEmpty2[+A](h: A, t: MyListFunctional[A]) extends MyListFunctional[A] {

  def head: A = h
  def tail: MyListFunctional[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](integer: B): MyListFunctional[B] = new NonEmpty2(integer, this)
  def printElements : String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements

  /*
    [1,2,3].filter(n % 2 == 0) =
      [2,3].filter(n % 2 == 0) =
      = new NonEmpty1(2, [3].filter(n % 2) == 0))
      = new NonEmpty1(2, Empty.filter(n % 2 == 0))
      = new NonEmpty(2, Empty)
   */

  def map[B](transformer: A => B): MyListFunctional[B] =
    new NonEmpty2(transformer(h), t.map(transformer))

  /*
    [1,2,3].map(n * 2)
     = new NonEmpty1(2, [2,3].map(n * 2)
     = new NonEmpty1(2, new NonEmpty1(4, [3].map(n * 2)))
     = new NonEmpty2(2, new NonEmpty1(4, new NonEmpty(6, Empty.map(n * 2))))
     = new NonEmpty2(2, new NonEmpty1(4, new NonEmpty(6, Empty)))
   */

  def filter(predicate: A => Boolean): MyListFunctional[A] =
    if (predicate(h)) new NonEmpty2(h, t.filter(predicate))
    else t.filter(predicate)

  /*
    [1,2].flatMap(n => [n,n+1])
    = [1,2] ++ [2].flatMap(n => [n, n+1])
    = [1,2] ++ [2,3} ++ Empty1.flatMap(n => [n, n+1])
    = [1,2] ++ [2,3] ++ Empty
    = [1,2,2,3]
   */

  def flatMap[B](transformer: A => MyListFunctional[B]): MyListFunctional[B] =
    transformer.apply(h) ++ t.flatMap(transformer)

  /*
     [1, 2] ++ [3, 4, 5]
     = new NonEmpty1(1, [2] ++ [3, 4, 5])
     = new NonEmpty(1, new NonEmpty(2, ++ [3, 4, 5]))
     = new NonEmpty(1, new NonEmpty(2, new NonEmpty(3, new NonEmpty(4, new NonEmpty(5)))))
   */

  def ++[B >: A] (list: MyListFunctional[B]) : MyListFunctional[B] = new NonEmpty2(h, t ++ list)

  def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  def sort(compare: (A,A) => Int) : MyListFunctional[A] = {
    def insert(x: A, sortedList: MyListFunctional[A]): MyListFunctional[A] = {
      if (sortedList.isEmpty) new NonEmpty2(x, Empty2)
      else if (compare(x, sortedList.head) <= 0) new NonEmpty2(x, sortedList)
      else new NonEmpty2(sortedList.head, insert(x, sortedList.tail))
    }
    val sortedTail = t.sort(compare)
    insert (h, sortedTail)
  }

  def zipWith[B, C](list: MyListFunctional[B], g: (A, B) => C) : MyListFunctional[C] = {
    if (list.isEmpty) {
      throw new RuntimeException("Lists do not have the same length")
    }
    else {
      new NonEmpty2(g(h,list.head), t.zipWith(list.tail, g))

    }
  }

  /*
    [1,2,3].fold(0) (+) =
    = [2,3].fold(1) (+) =
    = [3].fold(3) (+) =
    = [].fold(6) (+) =
    = 6
   */

  def fold[B](start: B)(function: (A, B) => B ) : B = {
    t.fold(function(h, start))(function)
  }


}
object ListTest2 extends App {

  val listOfIntegers: MyListFunctional[Int] = new NonEmpty2(1, new NonEmpty2(2, new NonEmpty2(3, Empty2)))
  val cloneListOfIntegers: MyListFunctional[Int] = new NonEmpty2(1, new NonEmpty2(2, new NonEmpty2(3, Empty2)))
  val anotherListOfIntegers: MyListFunctional[Int] = new NonEmpty2(4, new NonEmpty2(5, Empty2))
  val listOfStrings: MyListFunctional[String] = new NonEmpty2("Hello", new NonEmpty2("Scala", Empty2))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)

  println(listOfIntegers.map(new Function1[Int, Int] {
    override def apply(transformable: Int): Int = transformable * 2
  }))
  // or   println(listOfIntegers.map(elem=> elem * 2))
  // or   println(listOfIntegers.map(_ * 2))

  println(listOfIntegers.filter(new Function1[Int, Boolean] {
    override def apply(tester: Int) : Boolean = tester % 2 == 0
  }).toString)
  //  or println(listOfIntegers.filter(tester => tester % 2 == 0).toString)
  //  or println(listOfIntegers.filter(_ % 2 == 0).toString)

  println((listOfIntegers ++ anotherListOfIntegers).toString)
  println(listOfIntegers.flatMap(new Function1[Int, MyListFunctional[Int]] {
    override def apply(transformable: Int) : MyListFunctional[Int] = new NonEmpty2(transformable, new NonEmpty2(transformable + 1, Empty2))
  }).toString)
  // or println(listOfIntegers.flatMap(transformable => new NonEmpty2(transformable, new NonEmpty2(transformable + 1, Empty2))}).toString)

  println(listOfIntegers == cloneListOfIntegers)

    listOfIntegers.foreach(x => println(x))

  println(listOfIntegers.sort((x, y) => y - x))
  println(anotherListOfIntegers.zipWith[String, String](listOfStrings, _ + "-" + _))
  println(listOfIntegers.fold(0) (_ + _))

  // for comprehensions
  val combinations = for {
    n <- listOfIntegers
    string <- listOfStrings
  } yield n + " - " + string

  println(combinations)

}