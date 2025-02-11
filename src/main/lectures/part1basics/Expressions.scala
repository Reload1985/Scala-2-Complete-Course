package part1basics

object Expressions extends App {
  val x = 1 + 2 // expressions
  println(x)
  println(2 + 3 * 4)
  println(1 == x)
  print(!(1 == x))

  var aVariable = 2
  aVariable += 3
  print(aVariable)

  // Instructions (DO) vs Expressions (VALUE)

  // If expression
  val aCondition = true
  val aConditionedValue = if(aCondition) 5 else 3 // IF EXPRESSION
  print(aConditionedValue)

  var i = 0
  while(i < 10){
    println(i)
    i += 1
  }

  // EVERYTHING in Scala is an EXPRESSION

  val aWeirdValue = (aVariable = 3) // Unit === void

  //side effects: println(), whiles, reassigning

  // Code block

  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if (z > 2 ) "Hello" else "Goodbye"
  }

  // 1. difference between "Hello World" vs println("Hello World")
  // first: value of type string | second: expression, side effects, returning unit (different types)
  // 2.

  val someValue = {
    2 < 3
  } //true

  val someOtherValue = {
    if(someValue) 239 else 986
    42
  } // 42

}
