package part2oop

import part3fp._ // {ForPackageAccess, PrinceCharmming, WhatsAFunction}
import java.util.Date
import java.sql.{Date => SQLDate}

object PackagingAndImports extends App {

  //package members are accessble by their simple name
  val writer = new Writer("Daniel", "RockTheJVM", 2018)

  // import the package
  val ab = new ForPackageAccess("weipa", "ali") // part3fp.ForPackageAccess = fully qualified name
  println(ab.aPlusB)

  // packages are in hierarchy
  // matching folder structure


  // package object
  sayHello
  println(SPEED_OF_LIGHT)

  //imports
  val prince = new PrinceCharmming

  // 1. use FQ names
  val d = new Date
  val sqlDate = new SQLDate(2018, 5, 4)
  println(d)
  println(sqlDate)

  // 2. use aliasing

  // default imports
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???


}
