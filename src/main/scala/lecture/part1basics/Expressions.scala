package lecture.part1basics

object Expressions extends App{

  val x = 1 + 2
  println(x)

  println(2 + 3 + 4)

  println(1 == x)

  println(!(1 == x))

  var aVariable = 2
  aVariable +=3 //only works with variable as it alters its value
  println(aVariable)

  //Instructions (DO) vs Expressions (VALUE)

  //IF expression

  val aCondition = true
  val aConditionValue = if (aCondition) 5 else 3 // IF expression
  println(aConditionValue)
  println(if (aCondition) 5 else 3)

  // Everything in Scala is an expression!

  var i = 0
  while (i < 10) {
    println(i)
    i += 1
  }

  val aWeirdName = (aVariable =3) // Unit === void
  println(aWeirdName)

  // Code Blocks
  // Code blocks are also expressions

  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if (z > 2) "hello" else "goodbye"
  }

  // Expression: val anotherValue = z + 1 will not work as z is visible only inside the code block it is declared

  // 1. difference between "hello world" vs println("hello world")?
  // Answer: first one is a string and the second one is a unit

  // 2.
  // Answer: First one returns a Boolean value (in this case true) and the second one returns an Integer value

  val someValue = (
    2 < 3
  )

  val someOtherValue = (
    if (someValue) 239 else 986
    //42
  )
  println(someOtherValue)

}
