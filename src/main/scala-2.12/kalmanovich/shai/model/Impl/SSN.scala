package kalmanovich.shai.model.Impl

import kalmanovich.shai.model.SensitiveData

import scala.util.matching.Regex

/**
  * Created by Shai Kalmanovich on 5/3/2018.
  */
object SSN extends SensitiveData {

  override def isCheckSum(input: String) =
    true // SSN doesn't have checksum, always return true

  override implicit val patternList: List[Regex] = List(
    """^[\d]{9}$""".r,
    """^[\d]{3}-[\d]{2}-[\d]{4}$""".r,
    """^[\d]{3} [\d]{2} [\d]{4}$""".r
  )

  override val contextKeyWords: List[String] = Nil


  def isMatch(input: String) : Boolean = {
    super.isMatch(input)
  }

}
