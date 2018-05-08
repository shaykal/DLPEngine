package kalmanovich.shai

import java.io.{File, FileInputStream}

import kalmanovich.shai.model.Rules
import org.slf4j.{Logger, LoggerFactory}

import scala.io.BufferedSource

/**
  * Created by Shai Kalmanovich on 5/6/2018.
  */
object MainApp {

  val log: Logger = LoggerFactory.getLogger(this.getClass)

  def main(args: Array[String]) : Unit = {
    if(args.length != 1){
      log.error(s"Please enter file name and path as the parameter for the program.")
    } else {
      val fileName = args(0)
      log.debug("fileName is: " + fileName)
      val file: File = new File(fileName)
      if (file.exists()) {
        startReadingFile(fileName)
      } else {
        log.error(s"$fileName does Not exist.")
      }
    }
  }

  /**
    * <i>startReadingFile</i> - This is the main function that runs the logic of the assignment.
    *
    * @param fileName - The file name of the input file to process.
    */
  def startReadingFile(fileName: String): Unit = {
    log.info(s"Going to start reading from file $fileName")
    val inputStream: FileInputStream = new FileInputStream(fileName)
    val source: BufferedSource = io.Source.fromInputStream(inputStream) //getClass.getClassLoader.getResourceAsStream(fileName), "UTF-8")
    try {
      val linesList: List[String] = source.getLines.toList
      linesList.par.foreach(line => {
        log.debug(s"line is: $line")
        Rules.applyAllRulesOnText(Rules.rulesList, line).foreach(entry => log.info(entry))
      })
    } finally {
      source.close()
    }
  }
}