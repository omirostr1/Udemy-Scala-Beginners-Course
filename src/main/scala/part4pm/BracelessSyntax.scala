package part4pm

object BracelessSyntax {

  // if - expressions
  val anIfExpression = if (2 > 3) "bigger" else "smaller"

  // java-style
  val anIfExpression_v2 =
    if (2 > 3) {
      "bigger"
    } else {
      "smaller"
    }

  // compact
  val anIfExpression_v3 =
    if (2 > 3) "bigger"
    else "smaller"

//  // scala 3
//  val anIfExpression_v4 =
//    if 2 > 3 then
//      "bigger"  // needs to have a higher indentation than the if part
//    else
//      "smaller"

//    val anIfExpression_v5 =
//      if 2 > 3 then
//        val result = "bigger"
//      else
//        val result = "smaller"
//        result

  def main(args: Array[String]): Unit = {

  }

}
