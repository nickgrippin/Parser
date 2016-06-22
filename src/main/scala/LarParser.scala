package main.scala

import scala.util.Try

/**
  * Created by grippinn on 6/22/16.
  */
trait LarParser {
  def parseLar(s: String): List[String] = {
    val values = s.split('|').map(_.trim)
    var errors = List[String]()
    val intFields = List(0, 2, 5, 6, 7, 8, 9, 10, 11, 12, 17, 18, 24, 29, 30, 32, 37, 38)

    if (values.length != 40 && values.length != 39) {
      errors = errors :::  List("Incorrect number of fields: " + values.length)
    }

    for (int <- intFields) {
      if(Try(values(int).toInt).isFailure) {
        errors = errors ::: List(s"Field at index $int is not an Integer")
      }
    }

    errors
  }
}
