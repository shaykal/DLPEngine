package kalmanovich.shai.model

import kalmanovich.shai.model.Impl.SSN

/**
  * Created by Shai Kalmanovich on 5/6/2018.
  */
object Rules {

  val rulesList : List[SensitiveData] = List(SSN)


  def applyRuleOnText(aRule: SensitiveData, text: String) : Option[String] = {
    val sensitiveDataOpt: Option[String] = aRule.getSensitiveData(text)

    sensitiveDataOpt.flatMap(sensitiveDataIn => {
      // found a matching rule. Need to check if it is indeed a violation
      val indexOfSensitiveData: Int = text.indexOf(sensitiveDataIn)
      if(aRule.verifySensitiveData(text,indexOfSensitiveData)) {
        Some(s"Found sensitive data of type ${aRule.name}: $sensitiveDataIn")
      } else {
        None
      }
    })
  }


  def applyAllRulesOnText(rulesList: List[SensitiveData], text: String): List[String] = {
    rulesList.filter(_.isActive)
             .flatMap(rule => applyRuleOnText(rule, text))
  }

}