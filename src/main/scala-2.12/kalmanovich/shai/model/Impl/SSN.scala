package kalmanovich.shai.model.Impl

import kalmanovich.shai.model.SensitiveData
import kalmanovich.shai.utils.PropertiesUtils

import scala.util.matching.Regex

/**
  * Created by Shai Kalmanovich on 5/3/2018.
  */
object SSN extends SensitiveData {

  override val name: String = "SSN"
  override val contextKeyWords: List[String] = PropertiesUtils.rulesSsnKeywords.split(",").toList
  override val isActive: Boolean = PropertiesUtils.rulesSsnIsActive
  override val patternList: List[Regex] = List(
    """[\d]{9}""".r,
    """[\d]{3}-[\d]{2}-[\d]{4}""".r,
    """[\d]{3} [\d]{2} [\d]{4}""".r
  )

  override def isCheckSum(input: String) =
    true // SSN doesn't have checksum, always return true


}