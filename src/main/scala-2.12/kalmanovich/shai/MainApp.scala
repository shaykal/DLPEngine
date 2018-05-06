package kalmanovich.shai

import java.io.File

import org.slf4j.{Logger, LoggerFactory}

import scala.io.BufferedSource

/**
  * Created by Shai Kalmanovich on 5/6/2018.
  */
object MainApp extends App {

  val log: Logger = LoggerFactory.getLogger(this.getClass)

  override def main(args: Array[String]) : Unit = {
    val fileName = "SampleTextFile_1000kb.txt"
    val outputFileName = "output.txt"

    startReadingFile(fileName, outputFileName)
  }

  /**
    * <i>startReadingFile</i> - This is the main function that runs the logic of the assignment.
    *
    * @param fileName - The file name of the input file to process.
    */
  def startReadingFile(fileName: String, outputFileName: String): Unit = {
    val outputFile: File = new File(outputFileName)
    if(outputFile.exists()) {
      outputFile.delete()
    }
    outputFile.createNewFile()
    log.info(s"Going to start reading from file $fileName")
    val source: BufferedSource = io.Source.fromInputStream(getClass.getClassLoader.getResourceAsStream(fileName), "UTF-8")
    try {
      val linesIterator = source.getLines
      for (line: String <- linesIterator) {
        log.debug(s"line is: $line")
      }

    } finally {
      source.close()
    }
  }
}