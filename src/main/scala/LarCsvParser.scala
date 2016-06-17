package main.scala

import java.io.File

import scala.io.Source

/**
  * Created by grippinn on 6/17/16.
  */
object LarCsvParser extends App {
  val lines = Source.fromFile(new File(args(0))).getLines.toList
  var error = false

  for(line <- lines) {
    check(line)
  }

  if(!error) {
    println("******************************************\n* Success!  The file parsed successfully *\n******************************************")
  }

  def check(s: String) = {
    if(s.substring(0, 1) == "1" && !parseTS(s)) {
      println("TS ERROR: " + s)
      error = true
    } else if(s.substring(0, 1) == "2" && !parseLAR(s)) {
      println("LAR ERROR: " + s)
      error = true
    }
  }

  def parseLAR(s: String): Boolean = {
    val values = s.split('|').map(_.trim)
    try {
      val id = values(0).toInt
      val respId = values(1)
      val agencyCode = values(2).toInt
      val loanId = values(3)
      val loanDate = values(4)
      val loanType = values(5).toInt
      val propertyType = values(6).toInt
      val loanPurpose = values(7).toInt
      val occupancy = values(8).toInt
      val loanAmount = values(9).toInt
      val preapprovals = values(10).toInt
      val actionType = values(11).toInt
      val actionDate = values(12).toInt
      val msa = values(13)
      val state = values(14)
      val county = values(15)
      val tract = values(16)
      val appEthnicity = values(17).toInt
      val coAppEthnicity = values(18).toInt
      val appRace1 = values(19).toInt
      val appRace2 = values(20)
      val appRace3 = values(21)
      val appRace4 = values(22)
      val appRace5 = values(23)
      val coAppRace1 = values(24).toInt
      val coAppRace2 = values(25)
      val coAppRace3 = values(26)
      val coAppRace4 = values(27)
      val coAppRace5 = values(28)
      val appSex = values(29).toInt
      val coAppSex = values(30).toInt
      val appIncome = values(31)
      val purchaserType = values(32).toInt
      val denial1 = values(33)
      val denial2 = values(34)
      val denial3 = values(35)
      val rateSpread = values(36)
      val hoepaStatus = values(37).toInt
      val lienStatus = values(38).toInt

      true
    } catch {
      case e: Exception => false
    }
  }

  def parseTS(s: String): Boolean = {
    val values = s.split('|').map(_.trim)
    try {
      val id = values(0).toInt
      val respId = values(1)
      val code = values(2).toInt
      val timestamp = values(3).toLong
      val activityYear = values(4).toInt
      val taxId = values(5)
      val totalLines = values(6).toInt
      val respName = values(7)
      val respAddress = values(8)
      val respCity = values(9)
      val respState = values(10)
      val respZip = values(11)
      val parentName = values(12)
      val parentAddress = values(13)
      val parentCity = values(14)
      val parentState = values(15)
      val parentZip = values(16)
      val contactPerson = values(17)
      val contactPhone = values(18)
      val contactFax = values(19)
      val contactEmail = values(20)

      true
    } catch {
      case e: Exception => false
    }

  }
}
