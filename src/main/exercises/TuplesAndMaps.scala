object TuplesAndMaps extends App{

  // tupples = finite order of "Lists"
  val aTuple = (2, "Hello, Scala")  // tupple2[Int, String] = (Int, String)

  println(aTuple._1)
  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap) // ("hello, Scala", 2)

  // Maps - keys -> value
  val aMap: Map[String, Int] = Map()

  val phonebook = Map(("Jim", 555), "Daniel" -> 789, ("JIM", 9000)).withDefaultValue(-1)
  // a -> b is a sugar for (a, b)
  println(phonebook)

  // map ops
  println(phonebook.contains("Jim"))
  println(phonebook(""))

  // add pairing
  val newPairing = "Mary" -> 678
  val newPhoneBook = phonebook + newPairing
  println(newPhoneBook)


  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))

  /*
  * 1.  What would happen if i had two original entries "Jim" -> 555 and "Jim" -> 900?
    !!! carefull with mapping keys - overlapping
    !!! Exemple to prevent overlapping:
        val groupedData = data.groupBy(_._1).mapValues(_.map(_._2))
        println(groupedData)
        Map(A -> List(1, 3), B -> List(2))
  * 2.  Over simplified social network based on maps
    Person = String
    - add person to the network
    - remove
    - friend mutual
    - unfriend

    - number of friends of a person
    - Person with most friends
    - how many people have no friends
    - if there is a social connection between two people (direct or not)

  *
  * */

  // Adiciona uma pessoa ao network, inicializando a lista de amigos como um conjunto vazio
  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    network + (person -> Set()) // Adiciona a pessoa ao mapa, associando-a a um conjunto vazio de amigos
  }

  // Faz duas pessoas se tornarem amigas (conexão bidirecional)
  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a) // Obtém os amigos da pessoa 'a'
    val friendsB = network(b) // Obtém os amigos da pessoa 'b'

    // Atualiza o mapa adicionando 'b' à lista de amigos de 'a' e vice-versa
    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
  }

  // Remove a amizade entre duas pessoas
  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a) // Obtém os amigos de 'a'
    val friendsB = network(b) // Obtém os amigos de 'b'

    // Atualiza o mapa removendo 'b' da lista de amigos de 'a' e vice-versa
    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }

  // Remove uma pessoa da rede completamente (removendo todas as amizades associadas)
  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    // Função auxiliar para remover todas as amizades dessa pessoa
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] = {
      if (friends.isEmpty) networkAcc // Se não houver mais amigos para remover, retorna o network atualizado
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head)) // Remove um amigo e chama a função recursivamente
    }

    val unfriended = removeAux(network(person), network) // Remove todas as conexões dessa pessoa
    unfriended - person // Remove a pessoa completamente do mapa
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"), "Mary")
  val newNetwork = add(network, "David")
  val updateNetowrk = friend(newNetwork, "Bob", "David")
  println(network)
  println(friend(network, "Bob", "Mary"))
  println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
  println(remove(friend(network, "Bob", "Mary"), "Bob"))

  println("------------")
  println(network)
  println(newNetwork)

  // Jum, Bob, Mary
  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val testNet = friend(jimBob, "Bob", "Mary")

  println(testNet)

  def nFriends(network: Map[String, Set[String]], person: String): Int =
    if(!network.contains(person)) 0
    else network(person).size

  println(nFriends(testNet, "Bob"))

  def mostFriends(network: Map[String, Set[String]]): String =
    network.maxBy()

}
