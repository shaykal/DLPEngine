package kalmanovich.shai.model

import kalmanovich.shai.model.Impl.SSN
import org.scalatest.FunSuite

/**
  * Created by Shai Kalmanovich on 5/6/2018.
  */
class RulesTest extends FunSuite {

  test("applyRuleOnText returns true when text includes sensitive data") {
    val text: String = "My social security number is 123-45-6789"
    val answer: Option[String] = Rules.applyRuleOnText(SSN, text)

    assert(answer.contains("SSN"))
  }


}
