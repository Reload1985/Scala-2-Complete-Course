import java.util.stream.Gatherers
import scala.language.postfixOps

abstract class MyList[+A] {

  /*
  * head = first element of the list
  * tail = remainder of the list
  * isEmpty = is this list empty
  * add(int) => new list with this element added
  * toString => a string representation of the list
  *  */

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  // polymorphic call
  override def toString: String = "[" + printElements + "]"

  // higher-order functions
  def map[B](transfromer: A => B): MyList[B]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]

  // concatenation
  def ++[B >: A](list: MyList[B]): MyList[B]

  // hofs
  def foreach(f: A => Unit): Unit
  def sort(compare: (A, A) => Int): MyList[A]
  def zipwith[B, C](list: MyList[B], zip:(A, B) => C): MyList[C]
  def fold[B](start: B)(operator: (B, A) => B): B
}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] =  throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
  def printElements: String = ""

  def map[B](transfrom: Nothing => B): MyList[B] = Empty
  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty
  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  //hofs
  def foreach(f: Nothing => Unit): Unit = ()
  def sort(compare: (Nothing, Nothing) => Int) = Empty
  def zipwith[B,C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] =
    if(!list.isEmpty) throw new RuntimeException(("Lists do not have the same length"))
    else Empty

  def fold[B](start: B)(operator: (B, Nothing) => B): B = start

}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(element, this)
  def printElements: String =
    print("__: "+h)
    if(t.isEmpty) "" + h
    else "" + h + " " +t.printElements // h + " " +

  def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  def map[B](transformer: A => B): MyList[B] =
    new Cons(transformer(h), t.map(transformer))

  /*
  * [1,2].flatMap(n => [n, n + 1])
    * = [1,2] ++ [2].flatMap(n => n + 1)
    * = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n + 1])
    * = [1,2] ++ [2,3] ++ Empty
    * = [1,2,2,3]
  * */

  def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

  // hofs
  def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  def sort(compare: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A] =
      if(sortedList.isEmpty) new Cons(x, Empty)
      else if(compare(x, sortedList.head) <= 0) new Cons(x, sortedList)
      else new Cons(sortedList.head, insert(x, sortedList.tail))

    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }

  def zipwith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] =
    if(list.isEmpty) throw new RuntimeException("Lists do not have the same length.")
    else new Cons(zip(h, list.head), t.zipwith(list.tail, zip))

  /*
  * [1,2,3].fold(0)(+) =
    [2,3].fold(1)(+) =
    [3].fold(3)(+) =
    [].fold(6)(+) = 6
  * */
  def fold[B](start: B)(operator: (B, A) => B): B =
    val newStart = operator(start, h)
    t.fold(operator(start, h))(operator)


  /*
  * [1,2] ++ [3,4,5]
  * = mew Cons(1, [2] ++ [3,4,5])
  * = new Cons(1, new Cons(2, Empty ++ [3,4,5]))
  * = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5)))))
  * */

  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)
}
/*
trait MyPredicate[-T] {
  def test(elem: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(elem: A): B
}
*/

object ListTest extends App{
  val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val clonelistOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val anotherListOfIntegers: MyList[Int] = new Cons(4, new Cons(5, Empty))
  val listOfStrings: MyList[String] = new Cons("Hello", new Cons("Scala", Empty))

  println(listOfStrings.toString)
  println(listOfIntegers.toString)

  println(listOfIntegers.map(elem => elem * 2).toString) // elem => elem * 2 === _ * 2

  println(listOfIntegers.filter(elem => elem % 2 == 0)) // elem => elem % 2 == 0 ==== _ % 2

  println((listOfIntegers ++ anotherListOfIntegers).toString)
  println(listOfIntegers.flatMap(elem => new Cons(elem, new Cons(elem + 1, Empty))).toString)

  println(clonelistOfIntegers == listOfIntegers)

  listOfIntegers.foreach(println)
  println(listOfIntegers.sort((x, y) => y - x))

  println(anotherListOfIntegers.zipwith(listOfStrings, _ + "-" + _))

  println(listOfIntegers.fold(0)(_ + _))

  // for comprehensions
  val combinations = for {
    n <- listOfIntegers
    string <- listOfStrings
  } yield n + "-" + string

  println(combinations)
}

