package kalmanovich.shai.model

import kalmanovich.shai.model.Impl.{CreditCard, SSN}
import org.scalatest.FunSuite

import scala.util.matching.Regex

/**
  * Created by Shai Kalmanovich on 5/6/2018.
  */
class RulesTest extends FunSuite {

  test("applyRuleOnText returns Some with message when text includes SSN sensitive data and key words are in the correct distance") {
    val text: String = "My social security number is 123-45-6789"
    val answer: Option[String] = Rules.applyRuleOnText(SSN, text)
    assert(answer.contains(s"Found sensitive data of type SSN: 123-45-6789"))
  }

  test("applyRuleOnText returns None when text doesn't include sensitive data") {
    val text: String = "My social security number is 123-45-678"
    val answer: Option[String] = Rules.applyRuleOnText(SSN, text)
    assert(answer.isEmpty)
  }

  test("applyRuleOnText returns None when text includes SSN sensitive data but keywords don't exist") {
    val text: String = "My number is 123-45-6798"
    val answer: Option[String] = Rules.applyRuleOnText(SSN, text)
    assert(answer.isEmpty)
  }

  test("applyRuleOnText returns None when text includes SSN sensitive data and keywords exist but are too far away (default is 10)") {
    val text: String = "My social security number is bla bla bla bla bla bla bla 123-45-6789"
    val answer: Option[String] = Rules.applyRuleOnText(SSN, text)
    assert(answer.isEmpty)
  }

  test("applyAllRulesOnText returns true for SSN and sensitive data in input") {
    val text: String = "My social security number is 123456789"
    val rulesList: List[SensitiveData] = List(SSN)
    val answer: Seq[String] = Rules.applyAllRulesOnText(rulesList, text)
    assert(answer.head == "Found sensitive data of type SSN: 123456789")
  }

  test("applyAllRulesOnText returns true for SSN and sensitive data in input when rule list has 2 rules") {
    val text: String = "My social security number is 123456789"
    val rulesList: List[SensitiveData] = List(SSN, SensitiveDataInner)
    val answer: Seq[String] = Rules.applyAllRulesOnText(rulesList, text)
    assert(answer.head == "Found sensitive data of type SSN: 123456789")
  }

  test("applyAllRulesOnText returns false for dummy sensitive data") {
    val text: String = "My social security number is 123456789"
    val rulesList: List[SensitiveData] = List(SensitiveDataInner)
    val answer: Seq[String] = Rules.applyAllRulesOnText(rulesList, text)
    assert(answer.isEmpty)
  }

  test("applyAllRulesOnText returns true for SSN and sensitive data in input when rule list has 2 rules and first is dummy") {
    val text: String = "My social security number is 123456789"
    val rulesList: List[SensitiveData] = List(SensitiveDataInner, SSN)
    val answer: Seq[String] = Rules.applyAllRulesOnText(rulesList, text)
    assert(answer.head == "Found sensitive data of type SSN: 123456789")
  }

  test("applyRuleOnText returns Some with message when text includes CreditCard sensitive data and key words are in the correct distance") {
    val text: String = "My credit card number is 4012 8888 8888 1881"
    val answer: Option[String] = Rules.applyRuleOnText(CreditCard, text)
    assert(answer.contains(s"Found sensitive data of type Credit Card: 4012 8888 8888 1881"))
  }

  test("applyRuleOnText returns None when text includes Credit Card sensitive data but keywords don't exist") {
    val text: String = "My number is 4012-8888-8888-1881"
    val answer: Option[String] = Rules.applyRuleOnText(CreditCard, text)
    assert(answer.isEmpty)
  }

  test("applyAllRulesOnText returns true for Credit Card and sensitive data in input") {
    val text: String = "Meu cartão de crédito é 4012-8888-8888-1881"
    val rulesList: List[SensitiveData] = List(CreditCard)
    val answer: Seq[String] = Rules.applyAllRulesOnText(rulesList, text)
    assert(answer.head == "Found sensitive data of type Credit Card: 4012-8888-8888-1881")
  }

  test("applyAllRulesOnText returns true for both SSN and Credit Card and sensitive data in input when rule list has 2 rules") {
    val text: String = "My social security number is 123456789. My credit card number is 4012 8888 8888 1881"
    val rulesList: List[SensitiveData] = List(SSN, CreditCard)
    val answer: Seq[String] = Rules.applyAllRulesOnText(rulesList, text)
    assert(answer.head == "Found sensitive data of type SSN: 123456789")
    assert(answer.tail.head == "Found sensitive data of type Credit Card: 4012 8888 8888 1881")
  }
}

object SensitiveDataInner extends SensitiveData {
  override val name: String = "SensitiveDataTest"
  override val patternList: List[Regex] = Nil
  override val contextKeyWords: List[String] = List("bla")
  override val isActive: Boolean = true

  override def isCheckSum(input: String): Boolean = true

  override def isKeyWords(input: String, contextKeyWords: List[String], sensitiveData: String, distance: Int): Boolean =
    isKeyWordsDefaultImpl(input, contextKeyWords, sensitiveData, distance)
}
