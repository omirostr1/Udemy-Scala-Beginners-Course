package part2oop

import playground.{Cinderella => Princess, PrinceCharming}

import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImports extends App{

  // package members are accessible by their simple name
  //val writer = new Writer("Omiros", "Trypatsas", 2000)

  // import the package
  // val princess = new playground.Cinderella
  // this ^^ is an alternative way of importing the Cinderella class from the playground file without importing it on top.
  val princess = new Princess    // this is acceptable due to importing it on top

  // packages are in hierarchy
  // matching folder structure.

  //package object

  sayHello
  println(SPEED_OF_LIGHT)

  // imports

  val prince = new PrinceCharming
  // this ^^ is the normal way of importing, which will make the above command for import look like below:
  // import playground.{Cinderella, PrinceCharming}

  // val d = new Date   // the IDE will consider that the date used here is from the first import if they have the exact same name.

  // 1. use FQ names
  val date = new Date
  val sqlDate = new SqlDate(2018, 5, 4)

  // 2. use aliasing

  // default imports
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ??


}
