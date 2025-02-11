package part1basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): Int =
    if (n <= 0) 1
    else {
      println("Computing factorial of "+ n +" - I first need factorial of "+ (n - 1))
      val result = n * factorial(n - 1)
      println("Computed factorial of "+ n)
      result
    }
  println(factorial(10))


  def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factHelper(x: Int, accumulator: BigInt): BigInt =
      if(x<=1) accumulator
      else factHelper(x-1, x * accumulator) // TAIL RECURSION = use recursive call as the last expression
    factHelper(n, 1)
  }

  /*
  * anotherFactorial(10) = factHelper(10,1)
  * factHelper(9, 1 * 10)
  * factHelper(8, 1 * 9 * 10)
  * factHelper(7, 1 * 8 * 9 * 10)
  * .....
  * factHelper(2, 3 * 4 * ... * 9 * 10)
  * factHelper(1, 1 * 2 * 3 * ... * 9 * 10)
  * 1 * 2 * 3 * 4 * ... * 10
  * */

  println(anotherFactorial(5000))

  // WHEN WE NEED TO USE LOOPS, USE _TAIL_RECURSION

  /*
  * 1. Concatenate a string n times
  * 2. isPrime function Tail Recursive
  * 3. Fibonacci function, tail recursive
  * */

  def concatonateTailrec(aString: String, n: Int, accumulator: String): String =
    if(n <= 0)accumulator
    else concatonateTailrec(aString, n - 1, aString + accumulator)

  println(concatonateTailrec("hello", 3, ""))

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeTailRec(t: Int, isStillPrime: Boolean): Boolean =
      if(!isStillPrime) false
      else if (t <= 1) true
      else isPrimeTailRec(t - 1, n % t != 0 && isStillPrime)

    isPrimeTailRec(n / 2, true)
  }

  println(isPrime(2003)) //true
  println(isPrime(1876)) //false

  def fibonacci(n: Int): Int = {
    def fiboTailrec(i: Int, last: Int, nextToLast: Int): Int =
      if(i >= n) last
      else fiboTailrec(i + 1, last + nextToLast, last)

    if(n < 2) 1
    else fiboTailrec(2, 1, 1)
  }

  println(fibonacci(8))
}
