package kalmanovich.shai.model.Impl

import kalmanovich.shai.model.SensitiveData
import kalmanovich.shai.utils.{Consts, PropertiesUtils}

import scala.util.matching.Regex

/**
  * Created by Shai Kalmanovich on 5/8/2018.
  */
object CreditCard extends SensitiveData {
  override val name: String = "Credit Card"
  override val contextKeyWords: List[String] = PropertiesUtils.rulesCreditCardKeywords.split(",").toList
  override val isActive: Boolean = PropertiesUtils.rulesCreditCardIsActive
  override val patternList: List[Regex] = List(
    """[\d]{16}""".r,
    """[\d]{4}-[\d]{4}-[\d]{4}-[\d]{4}""".r,
    """[\d]{4} [\d]{4} [\d]{4} [\d]{4}""".r
  )


  override def isCheckSum(input: String): Boolean =
    checkLuhn(input)


  def checkLuhn(purportedCC: String) : Boolean = {
    // need to make sure that the input is only digits with no spaces or dashes
    val correctInput = purportedCC.replaceAll(Consts.SPACE, Consts.EMPTY_STRING).replaceAll(Consts.DASH, Consts.EMPTY_STRING)

    var sum: Integer = 0
    val nDigits: Integer = correctInput.length
    val parity: Integer = nDigits % 2
    for(i <- 0 until nDigits) {
      var digit: Integer = correctInput.charAt(i).asDigit
      if (i % 2 == parity)
        digit = digit * 2
      if (digit > 9)
        digit = digit - 9
      sum = sum + digit
    }
    (sum % 10) == 0
  }

  override def isKeyWords(input: String, contextKeyWords: List[String], sensitiveData: String, distance: Int): Boolean =
    isKeyWordsDefaultImpl(input, contextKeyWords, sensitiveData, distance)
}