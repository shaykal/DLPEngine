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
  def isKeyWords(input: String, contextKeyWords : List[String], sensitiveData: String, distance: Int): Boolean


  /**
    * <i>verifySensitiveData</i> - This method verifies that the "sensitive data" that was found is indeed sensitive and not just
    * a string that happens to match the pattern. It calls the checksum validation and key word validation methods.
    * @param input - The input string.
    * @param sensitiveData - The sensitive data that was already found.
    * @return - true if the data is indeed sensitive, false otherwise.
    */
  def verifySensitiveData(input: String, sensitiveData: String): Boolean = {
    isCheckSum(sensitiveData) && isKeyWords(input, contextKeyWords, sensitiveData, PropertiesUtils.wordsDistance)
  }

  /**
    * <i>isKeyWordsDefaultImpl</i> - This method returns true if any of the keywords in the contextKeyWords list appear in the input text and
    * also within the word distance. It returns false otherwise.
    * @param input - The input string.
    * @param sensitiveData - The sensitive data that was found.
    * @param distance - number that represents the word distance between the sensitive data and the location of the key words.
    * @return - true if found keywords within the distance, false otherwise.
    */
  def isKeyWordsDefaultImpl(input: String, contextKeyWords : List[String], sensitiveData: String, distance: Int): Boolean = {
    val indexOfSensitiveData: Int = input.indexOf(sensitiveData)
    val indexOfKeywords: Int = input.getIndexOfKeywordFromInput(contextKeyWords)
    // if index of keywords found (greater than -1) then need to count words for the distance
    (indexOfKeywords >= 0) && (input.countWordsDistance(indexOfKeywords, indexOfSensitiveData) <= distance)
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