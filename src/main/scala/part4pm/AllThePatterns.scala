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
  val aStandardList = List(1,2,3,42)
  val aStandardListMatching = aStandardList match {
    case List(1, _, _, _) => // extractor - advanced
    case List(1, _*) => // list of arbitrary length - advanced
    case 1 :: List(_) => // infix pattern
    case List(1,2,3) :+ 42 => // infix patterm
  }

  // 6 - type specifiers
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => // explicit type specifier
    case _ =>
  }

  // 7 - name binding
  val nameBindingMatch = aList match {
    case NonEmptyList @ NonEmpty2(_, _) => // name binding => use the same name later(here)
    case NonEmpty2(1, rest @ NonEmpty2(2, _)) => // name binding inside nested patterns
  }

  // 8 - multi-patterns
  val multipattern = aList match {
    case Empty2 | NonEmpty2(0, _) => // compound pattern (multi-pattern)
  }

  // 9 - if guards
  val secondElementSpecial = aList match {
    case NonEmpty2(_, NonEmpty2(specialElement, _)) if specialElement % 2 == 0 =>
  }

  // ALL.

  /*
    Question.
   */

  val numbers = List(1,2,3)
  val numbersMatch = numbers match {
    case listOfStrings: List[String] => "a list of strings"
    case listOfNumbers: List[Int] => "a list of numbers"
    case _ => ""
  }

  println(numbersMatch)
  // JVM trick question

}
