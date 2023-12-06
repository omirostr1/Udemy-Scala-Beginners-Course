package part4pm

import exercises.MyListFunctional
import exercises.Empty2
import exercises.NonEmpty2

object AllThePatterns extends App{

  // 1 - constants
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "a number"
    case "Scala" => "THE Scala"
    case true => "The Truth"
    case AllThePatterns => "A singleton object"
  }

  // 2 - match anything
  // 2.1 wildcard

  val matchAnything = x match {
    case _ =>
  }

  // 2.2 variable
  val matchAVariable = x match {
    case something => s"I've found $something"
  }

  // 3 - tuples
  val aTuple = (1,2)
  val matchATuple = aTuple match {
    case (1, 1) => 1
    case (something, 1) => s"I've found $something"
  }

  val nestedTuple = (1, (2, 3))
  val matchANestedTuple = nestedTuple match {
    case (_, (2, v)) =>
  }

  // PM can be NESTED!

  // 4 - case classes - constructor pattern
  // PMs can be nested with case classes as well
  val aList: MyListFunctional[Int] = NonEmpty2(1, NonEmpty2(2, Empty2))
  val matchAList = aList match {
    case Empty2 =>
    case NonEmpty2(subhead, subtail) =>
  }

  // 5 - List patterns

}
