package kalmanovich.shai.model

import kalmanovich.shai.utils.PropertiesUtils

import scala.util.matching.Regex

import kalmanovich.shai.utils.StringEnrichment._

/**
  * Created by Shai Kalmanovich on 5/3/2018.
  */
trait SensitiveData {

  val name: String
  val patternList : List[Regex]
  val contextKeyWords : List[String]
  val isActive : Boolean


  def isCheckSum(input: String) : Boolean

  //def isKeyWords(input: String, indexOfSensitiveData: Int, distance: Int) : Boolean



  def verifySensitiveData(input: String, indexOfSensitiveData: Int): Boolean = {
    isCheckSum(input) && isKeyWords(input, indexOfSensitiveData, PropertiesUtils.wordsDistance)
  }


  def isKeyWords(input: String, indexOfSensitiveDate: Int, distance: Int): Boolean = {
    val indexOfKeywords: Int = input.getIndexOfKeywordFromInput(contextKeyWords)
    // if index found (greater than -1) then need to count words for the distance
    (indexOfKeywords >= 0) && (input.countWordsDistance(indexOfKeywords, indexOfSensitiveDate) <= distance)
  }


  def getSensitiveData(input: String) : Option[String] = {
    getSensitiveData(input, patternList)
  }

  def getSensitiveData(input: String, patternList: List[Regex]) : Option[String] = {
    val answer: Seq[String] = patternList.flatMap(regex => {
      regex.findFirstIn(input)
    })
    answer match {
      case Nil => None
      case _ => Some(answer.head)
    }
  }
}