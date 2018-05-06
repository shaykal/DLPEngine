package kalmanovich.shai.model

import kalmanovich.shai.model.Impl.SSN

/**
  * Created by Shai Kalmanovich on 5/6/2018.
  */
object Rules {

  val rulesList : List[SensitiveData] = List(SSN)


  def applyRuleOnText(aRule: SensitiveData, text: String) : Option[String] = {
    if (aRule.isMatch(text)) {
      // found a matching rule. Need to check if it is indeed a violation
      Some(s"${aRule.name}")
    } else {
      None
    }
  }


  def applyAllRulesOnText(rulesList: List[SensitiveData], text: String): List[Option[String]] = {
    rulesList.filter(_.isActive)
             .map(rule => applyRuleOnText(rule, text))
  }

}