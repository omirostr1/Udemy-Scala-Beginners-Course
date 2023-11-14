package lecture.part1basics

object CBNvsCBV extends App{

  def calledByValue(x: Long) : Unit = {
    println("by value: " + x)
    println("by value: " + x)
  }

  def calledByName(x: => Long): Unit = {
    println("by name: " + x)
    println("by name: " + x)
  }

  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())

  //When called by value, the value is computed before call, whereas
  //when called by name, it is computed only when used/called at the
  //exact time needed.

}
