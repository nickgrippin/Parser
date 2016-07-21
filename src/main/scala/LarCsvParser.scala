package main.scala

import java.io.File
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

import scala.io.Source
import scala.util.Try

/**
  * Created by grippinn on 6/17/16.
  */
object LarCsvParser extends App with LarParser with TsParser {
  val file = pickFile()
  val lines = file.getLines.toList
  var error = false

  val tsErrors = parseTs(lines.head)
  if(tsErrors.nonEmpty) {
    println("TS Errors:")
    for(error <- tsErrors) {
      println("\t" + error)
    }
    error = true
  }

  for(line <- lines.tail) {
    val errors = parseLar(line)
    if(errors.nonEmpty) {
      error = true
      println("Errors on LAR #" + (lines.indexOf(line) + 1))
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

  def pickFile(): Source = {
    val chooser = new JFileChooser()
    chooser.setCurrentDirectory(new java.io.File("."))
    chooser.setDialogTitle("Input File")
    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY)
    chooser.setFileFilter(new FileNameExtensionFilter("txt", "txt"))

    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION &&
      Try(Source.fromFile(chooser.getSelectedFile)).isSuccess) {
      Source.fromFile(chooser.getSelectedFile)
    } else {
      println("\nWARNING: Unable to read file input.")
      Source.fromFile(".")
    }
  }
}
