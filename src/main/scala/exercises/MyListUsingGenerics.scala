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

  // higher-order functions
  def map[B](transformer: MyTransformer[A, B]) : MyListUsingGenerics[B]
  def filter(predicate: MyPredicate[A]) : MyListUsingGenerics[A]
  def flatMap[B](transformer: MyTransformer[A, MyListUsingGenerics[B]]): MyListUsingGenerics[B]

  //concatenation
  def ++[B >: A](list: MyListUsingGenerics[B]): MyListUsingGenerics[B]

}

case object Empty1 extends MyListUsingGenerics[Nothing] {

  def head: Nothing = throw new NoSuchElementException
  def tail: MyListUsingGenerics[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](integer: B): MyListUsingGenerics[B] = new NonEmpty1(integer, Empty1)
  def printElements : String = ""

  def map[B](transformer: MyTransformer[Nothing, B]): MyListUsingGenerics[B] = Empty1
  def filter(predicate: MyPredicate[Nothing]): MyListUsingGenerics[Nothing] = Empty1
  def flatMap[B](transformer: MyTransformer[Nothing, MyListUsingGenerics[B]]): MyListUsingGenerics[B] = Empty1

  def ++[B >: Nothing] (list: MyListUsingGenerics[B]) : MyListUsingGenerics[B] = list

}

case class NonEmpty1[+A](h: A, t: MyListUsingGenerics[A]) extends MyListUsingGenerics[A] {

  def head: A = h
  def tail: MyListUsingGenerics[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](integer: B): MyListUsingGenerics[B] = new NonEmpty1(integer, this)
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

  def map[B](transformer: MyTransformer[A, B]): MyListUsingGenerics[B] =
    new NonEmpty1(transformer.transform(h), t.map(transformer))

  /*
    [1,2,3].map(n * 2)
     = new NonEmpty1(2, [2,3].map(n * 2)
     = new NonEmpty1(2, new NonEmpty1(4, [3].map(n * 2)))
     = new NonEmpty2(2, new NonEmpty1(4, new NonEmpty(6, Empty.map(n * 2))))
     = new NonEmpty2(2, new NonEmpty1(4, new NonEmpty(6, Empty)))
   */

  def filter(predicate: MyPredicate[A]): MyListUsingGenerics[A] =
    if (predicate.test(h)) new NonEmpty1(h, t.filter(predicate))
    else t.filter(predicate)

  /*
    [1,2].flatMap(n => [n,n+1])
    = [1,2] ++ [2].flatMap(n => [n, n+1])
    = [1,2] ++ [2,3} ++ Empty1.flatMap(n => [n, n+1])
    = [1,2] ++ [2,3] ++ Empty
    = [1,2,2,3]
   */

  def flatMap[B](transformer: MyTransformer[A, MyListUsingGenerics[B]]): MyListUsingGenerics[B] =
    transformer.transform(h) ++ t.flatMap(transformer)

  /*
     [1, 2] ++ [3, 4, 5]
     = new NonEmpty1(1, [2] ++ [3, 4, 5])
     = new NonEmpty(1, new NonEmpty(2, ++ [3, 4, 5]))
     = new NonEmpty(1, new NonEmpty(2, new NonEmpty(3, new NonEmpty(4, new NonEmpty(5)))))
   */

  def ++[B >: A] (list: MyListUsingGenerics[B]) : MyListUsingGenerics[B] = new NonEmpty1(h, t ++ list)

}
object ListTest1 extends App {

  val listOfIntegers: MyListUsingGenerics[Int] = new NonEmpty1(1, new NonEmpty1(2, new NonEmpty1(3, Empty1)))
  val cloneListOfIntegers: MyListUsingGenerics[Int] = new NonEmpty1(1, new NonEmpty1(2, new NonEmpty1(3, Empty1)))
  val anotherListOfIntegers: MyListUsingGenerics[Int] = new NonEmpty1(4, new NonEmpty1(5, Empty1))
  val listOfStrings: MyListUsingGenerics[String] = new NonEmpty1("Hello", new NonEmpty1("Scala", Empty1))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)

  println(listOfIntegers.map(new MyTransformer[Int, Int] {
    override def transform(transformable: Int): Int = transformable * 2
  }))
  println(listOfIntegers.filter(new MyPredicate[Int] {
    override def test(tester: Int) : Boolean = tester % 2 == 0
  }).toString)

  println((listOfIntegers ++ anotherListOfIntegers).toString)
  println(listOfIntegers.flatMap(new MyTransformer[Int, MyListUsingGenerics[Int]] {
    override def transform(transformable: Int) : MyListUsingGenerics[Int] = new NonEmpty1(transformable, new NonEmpty1(transformable + 1, Empty1))
  }).toString)

  println(listOfIntegers == cloneListOfIntegers)

}

trait MyPredicate[-T] {
  def test(tester: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(transformable: A): B
}


/*

    1. Generic trait MyPredicate[-T] with a little method test (T) => Boolean
    2. Generic trait MyTransformer[-A, B] with a method transform(A) => B
    3. MyList:
        - map(transformer) => MyList
        - filter(predicate) => MyList
        - flatMap (transformer from A to MyList[B] => MyList[B]

        class EvenPredicate extends MyPredicate[Int]
        class StringToIntTransformer extends MyTransformer[String, Int]

        [1,2,3].map(n * 2) = [2,4,6]
        [1, 2, 3, 4].filter(n % 2) = [2,4]
        [1, 2, 3].flatMap (n => [n, n+1]) => [1,2,2,3,3,4]

 */

