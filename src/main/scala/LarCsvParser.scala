package main.scala

import java.io.File
import javax.swing.JFileChooser

import scala.io.Source
import scala.util.Try

/**
  * Created by grippinn on 6/17/16.
  */
object LarCsvParser extends App with LarParser {
  val file = pickFile()
  val lines = file.getLines.toList
  var error = false

  if(!parseTS(lines.head)) {
    println("TS ERROR: " + lines.head)
    error = true
  }

  for(line <- lines.tail) {
    val errors = parseLar(line)
    if(errors.nonEmpty) {
      error = true
      println("Errors on line #" + (lines.indexOf(line) + 1))
      for(error <- errors) {
        println("\t" + error)
      }
    }
  }

  if(!error) {
    for(line <- Source.fromFile(new File(getClass.getResource("/Success.txt").getPath)).getLines) {
      println(line)
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

      if(respName == "HMDATestBank202") {
        println("\nWARNING: Unable to read file input: Using clean test file instead\n")
      }

      true
    } catch {
      case e: Exception => false
    }

  }

  def pickFile(): Source = {
    val chooser = new JFileChooser()
    chooser.setCurrentDirectory(new java.io.File("."))
    chooser.setDialogTitle("Input File")
    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY)
    chooser.setAcceptAllFileFilterUsed(false)
    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory)
      System.out.println("getSelectedFile() : " + chooser.getSelectedFile)
      Try(Source.fromFile(chooser.getSelectedFile))
        .getOrElse(Source.fromFile(new File(getClass.getResource("/testClean.txt").getPath)))
    } else {
      Source.fromFile(new File(getClass.getResource("/testClean.txt").getPath))
    }
  }
}
