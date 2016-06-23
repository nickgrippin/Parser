package main.scala

import scala.util.Try

/**
  * Created by grippinn on 6/22/16.
  */
trait LarParser {
  def parseLar(s: String): List[String] = {
    val values = s.split('|').map(_.trim)
    var errors = List[String]()
    val intFields = Map(
      0 -> "Record Identifier",
      2 -> "Agency Code",
      5 -> "Loan Type",
      6 -> "Property Type",
      7 -> "Loan Purpose",
      8 -> "Owner Occupancy",
      9 -> "Loan Amount",
      10 -> "Preapprovals",
      11 -> "Type of Action Taken",
      12 -> "Date of Action",
      17 -> "Applicant Ethnicity",
      18 -> "Co-applicant Ethnicity",
      19 -> "Applicant Race: 1",
      24 -> "Co-applicant Race: 1",
      29 -> "Applicant Sex",
      30 -> "Co-applicant Sex",
      32 -> "Type of Purchaser",
      37 -> "HOEPA Status",
      38 -> "Lien Status"
    )

    if (values.length != 40 && values.length != 39) {
      errors = errors :::  List("Incorrect number of fields: " + values.length)
    }

    for (int <- intFields.keys) {
      if(Try(values(int).toInt).isFailure) {
        val field = intFields(int)
        errors = errors ::: List(s"$field is not an Integer")
      }
    }

    errors
  }
}
