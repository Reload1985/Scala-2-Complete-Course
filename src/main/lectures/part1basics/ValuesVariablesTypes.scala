package part1basics

object ValuesVariablesTypes extends App{

  private val x: Int = 42
  println(x)

  // vals are immutable
  //compiler can infer types

  val aString: String = "Hello"
  val aChar: Char = 'a'
  val anInt: Int = x
  val aShort: Short = 4613
  val aLong: Long = 123123123L
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.14

  //variables
  var aVariable: Int = 4

}
