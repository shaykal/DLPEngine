package kalmanovich.shai.model

import scala.util.matching.Regex

/**
  * Created by Shai Kalmanovich on 5/3/2018.
  */
trait SensitiveData {

  val patternList : List[Regex]
  val contextKeyWords : List[String]

  def isCheckSum(input: String) : Boolean

  def isMatch(input: String)(implicit patternList: List[Regex]) : Boolean = {
    patternList.exists((regex: Regex) => {
      val found: Option[String] = regex.findFirstIn(input)
      found match {
        case Some(_) => true
        case None => false
      }
    })
  }
}
