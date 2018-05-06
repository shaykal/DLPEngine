package kalmanovich.shai.utils

import org.scalatest.FunSuite

/**
  * Created by Shai Kalmanovich on 5/6/2018.
  */
class PropertiesUtilsTest extends FunSuite {

  test("loading properties file is successful") {
    val rulesSsnKeywords: String = PropertiesUtils.rulesSsnKeywords
    val rulesSsnIsActive: Boolean = PropertiesUtils.rulesSsnIsActive
    val wordsDistance: Int = PropertiesUtils.wordsDistance

    assert(rulesSsnKeywords == "Social Security,Social Security#,Soc Sec,SSN,SSNS,SSN#,SS,SSID")
    assert(rulesSsnIsActive)
    assert(wordsDistance == 10)
  }
}