package main.scala

import scala.util.Try

/**
  * Created by grippinn on 6/22/16.
  */
trait TsParser {
  def parseTs(s: String): List[String] = {
    val values = s.split('|').map(_.trim)
    var errors = List[String]()
    val numericFields = Map(
      0 -> "Record Identifier",
      2 -> "Agency Code",
      4 -> "Activity Year",
      6 -> "Total Lines"
    )

    if (values.length != 21) {
      errors = errors :::  List("Incorrect number of fields: " + values.length)
    }

    for (num <- numericFields.keys) {
      if(Try(values(num).toInt).isFailure) {
        val field = numericFields(num)
        errors = errors ::: List(s"$field is not an Int")
      }
    }

    if(Try(values(3).toLong).isFailure) {
      errors = errors ::: List("Timestamp is not a Long")
    }

    errors
  }
}
