package part2oop

object Inheritance extends App {

  // single class inheritance
  sealed class Animal {
    val creatureType = "wild"
    def eat = println("nhom nhom nhom")
  }

  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch crunch")
    }
  }

  val cat = new Cat
  cat.eat

  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  //overriding
  class Dog (override val creatureType: String) extends Animal{
    // override val creatureType = "domestic"

    override def eat = {
      super.eat
      println("crunch, crunch, crunch")
    }
  }

  val dog = new Dog("K9")
  dog.eat
  println(dog.creatureType)

  // type substitution(broad: Polymorphism)
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat

  // overRIDING vs overLOADING
  // super

  // prevent uses of overriding
  // 1- use final on member
  // 2 - use final on the entire class
  // 3 - seal the class = extend classes in THIS - FILE, prevent extension in other files

}
