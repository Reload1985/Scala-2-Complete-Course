package part3fp

object WhatsAFunction extends App {

  // DREAM: use functions as first class elements
  //problem: oop

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  // function types = Function[A, B]
  val stringToIntConverter = new Function1[String, Int] {
    override  def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("3") + 4)

  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  // Function types Function2[A, B, R] === (A,B) => R
  // All Scala functions are objects

  /*
  * 1. A function wich takes 2 strings and concatenates them
  * 2. Transform the MyPredicate and MyTrabsformer into function types
  * 3. Define a function wich takes an Int and return another function wich takes an Int and returns an Int
  *   - Whats the type of this function
  *   - how to do it
  * */

  def concatenator: (String, String) => String = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }

  println(concatenator("Hello ", "Scala"))
  
  // function1[Int, Function1[Int, Int]]
  val superAdder: Function1[Int, Function1[Int, Int]] = new Function[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }
  
  val adder3 = superAdder(3)
  println(adder3(4))
  println(superAdder(3)(4)) // curried function
}

trait MyFunction[A,B] {
  def apply(element: A): B
}

class ForPackageAccess(a: String, b: String) {
  def aPlusB: String = a + " " + b
}
