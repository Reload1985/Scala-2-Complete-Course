package part3fp

import scala.util.Random


object Sequences extends App {

  // Seq
  val aSequence = Seq(1,3,2,4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(7,5,6))
  println(aSequence.sorted)


  // Ranges
  val aRange: Seq[Int] = 1 until 10
  aRange.foreach(println)

  (1 to 10).foreach(x => println(x))

  val aList = List(1,2,3)
  val prepand = 42 +: aList :+ 89
  println(prepand)

  val apples5 = List.fill(5)(aList)
  println(apples5)
  println(aList.mkString("-|-"))

  // Arrays
  val numbers = Array(1,2,3,4)
  val threeElements = Array.ofDim[String](5)
  println(threeElements)
  threeElements.foreach(println)

  // mutations
  numbers(2) = 0 // systax sugar for numbers.update(2,0)
  println(numbers.mkString(" "))

  // arrays and seq
  val numbersSeq: Seq[Int] = numbers // implicit conversion
  println(numbersSeq)

  //vectors
  val vector = Vector(1,2,3,4)
  println(vector)

  // vectors vs lists
  val maxRuns = 1000
  val maxCapacity = 1000000

  def getwriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      //operation
      collection.updated(r.nextInt(maxCapacity), 0)
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // keep reference to tail
  // updating an element in the middle takes long
  println(getwriteTime(numbersList))

  //depth of the tree is small
  // needs to replace an entire 32-element chunck
  println(getwriteTime(numbersVector))



}
