package kalmanovich.shai

import kalmanovich.shai.model.Rules
import org.slf4j.{Logger, LoggerFactory}

import scala.io.BufferedSource

/**
  * Created by Shai Kalmanovich on 5/6/2018.
  */
object MainApp {

  val log: Logger = LoggerFactory.getLogger(this.getClass)

  def main(args: Array[String]) : Unit = {
    //println(System.getProperty("user.dir"))
    //val fileName = System.getProperty("user.dir") + """\src\main\resources\SampleTextFile_10kb.txt"""
    val fileName = "SampleTextFile_10kb.txt"
    println("fileName is: " + fileName)
    startReadingFile(fileName)
  }

  /**
    * <i>startReadingFile</i> - This is the main function that runs the logic of the assignment.
    *
    * @param fileName - The file name of the input file to process.
    */
  def startReadingFile(fileName: String): Unit = {
    //val outputList : scala.collection.mutable.ListBuffer[String] = ListBuffer()
    log.info(s"Going to start reading from file $fileName")
    val source: BufferedSource = io.Source.fromInputStream(getClass.getClassLoader.getResourceAsStream(fileName), "UTF-8")
    try {
      val linesIterator = source.getLines
      for (line: String <- linesIterator) { // TODO use par
        log.debug(s"line is: $line")
        Rules.applyAllRulesOnText(Rules.rulesList, line).foreach(entry => log.info(entry))
      }

    } finally {
      source.close()
    }
  }
}