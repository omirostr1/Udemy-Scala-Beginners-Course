package part3fp

object TuplesAndMaps extends App{

  // tuples = finite ordered "lists"
  val aTuple = new Tuple2(2, "hello, Scala") // Tuple2[Int, String] = (Int, String)
  // can avoid keywords "new" and/or "Tuple2" when defining a new Tuple

  println(aTuple._1) // 1st element which is 2
  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap) // ("hello, Scala", 2)

  // Maps - associate keys -> values
  val aMap: Map[String, Int] = Map()

  val phonebook = Map(("Jim", 555), ("Daniel", 789), "Omiros" -> 357).withDefaultValue(-1)
  // a -> b is sugar for (a, b)
  println(phonebook)

  // map ops
  println(phonebook.contains("Jim"))
  println(phonebook("Jim"))
  println(phonebook("Mary"))
  // as Mary doesn't exist, it will print -1 which is the default value set on the map declaration

  // add a pairing
  val newPairing = "Mary" -> 678
  val newPhoneBook = phonebook + newPairing
  println(newPhoneBook)

  // functionals on maps
  // map, filterMap, filter
  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2))

  // filterKeys
  println((phonebook.view.filterKeys(x => x.startsWith("J")).toMap))
  // mapValues
  println((phonebook.view.mapValues(number => "0245-" + number)).toMap)

  // conversions to other collections
  println(phonebook.toList)
  println(List(("Daniel", 555)).toMap)
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0))) // groups based on the character of each name at position 0

  /*
    1. What would happen if in the above Lowercase I had two original entries "Jim" -> 555 and "JIM" -> 900?
    2. Overly simplified social network based on maps
        Person = String
        - add a person to the network
        - remove a person
        - friend (mutual)
        - unfriend (mutual)

        - number of friends of a person
        - person with most friends
        - how many people have No friends
        - if there is a social connection between two people (direct or not)
   */

  // Solution to question 1
  val exercisePhonebook = Map(("Jim", 555), ("Daniel", 789), "Omiros" -> 357, "JIM" -> 900).withDefaultValue(-1)
  println(exercisePhonebook)
  println(exercisePhonebook.map(pair => pair._1.toLowerCase -> pair._2))
  // as there are two entries, Jim and JIM, they overlap resulting with only one entry and the one key missing.
  // !!! Be careful with mapping keys.

  // Solution to question 2

  val socialMediaMap: Map[String, Int] = Map()

  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    network + (person -> Set())

  def remove(network: Map[String, Set[String]], person: String) : Map[String, Set[String]] = {
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] = {
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))
    }
    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

  def friend(network: Map[String, Set[String]], personA: String, personB: String) : Map[String, Set[String]] = {
    val friendsA = network(personA)
    val friendsB = network(personB)

    network + (personA -> (friendsA + personB), personB -> (friendsB + personA))
  }

  def unfriend(network: Map[String, Set[String]], personA: String, personB: String) : Map[String, Set[String]] = {
    val friendsA = network(personA)
    val friendsB = network(personB)

    network + (personA -> (friendsA - personB)) + (personB -> (friendsB - personA))
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"), "Mary")
  println(network)
  val test = friend(network, "Bob", "Mary")
  println(test)
  println(unfriend(test, "Bob", "Mary")) // order doesn't matter here
  println(remove(test, "Bob"))

  // Jim, Bob, Mary
  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val maryBob = friend(jimBob, "Bob", "Mary")

  println(maryBob)
  val numberOfFriendsMap: Map[String, Int] = Map()

  def numberOfFriends(network: Map[String, Set[String]], person: String): Int = {
    if (!network.contains(person)) 0
    else network(person).size
  }

  def mostPopular(network: Map[String, Set[String]]): String = {
    network.maxBy(pair => pair._2.size)._1
  }

  def noFriends(network: Map[String, Set[String]]): Int = {
    (network.view.filterKeys(pair => network(pair).isEmpty ).toMap).size
    // or network.count(pair => pair._2.isEmpty)
  }

  def connection(network: Map[String, Set[String]], personA: String, personB: String): Boolean = {
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }

    bfs(personB, Set(), network(personA) + personA)

  }

  println(numberOfFriends(maryBob, "Bob"))
  println(mostPopular(maryBob))
  println(noFriends(maryBob))
  println(connection(maryBob, "Mary", "Bob"))
  println(connection(maryBob, "Mary", "Jim"))
  println(connection(test, "Mary", "Jim"))

}
