package part2oop

object Enums extends App{

  object Permissions extends Enumeration {
    val read = Value("READ")
    val write = Value("WRITE")
    val execute = Value("EXECUTE")
    val none = Value("NONE")

    // add fields/methods
    def openDocument(): Unit = {
      if (this == read) println("opening document..")
      else println("reading not allowed.")
    }
  }

  val somePermissions: Permissions.Value = Permissions.read

  // constructor args
  class PermissionsWithBits(bits: Int) extends Enumeration {
//    val read extends PermissionsWithBits(4)
//    val write = Value("WRITE", 2)
//    val execute = Value("EXECUTE", 1)
//    val none = Value("NONE", 0)
  }

//  object PermissionsWithBits {
//    def fromBits(bits: Int): PermissionsWithBits = //whatever
//      PermissionsWithBits.NONE
//  }
//
//  // standard API
//  val somePermissionOrdinal = somePermissions.ordinal
//  val allPermissions = PermissionsWithBits.values
//
//  somePermissions.openDocument()
//  println(somePermissionsOrdinal)

}
