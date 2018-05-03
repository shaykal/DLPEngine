package kalmanovich.shai.model

import org.scalatest.FunSuite

import scala.util.matching.Regex

/**
  * Created by Shai Kalmanovich on 5/3/2018.
  */
class SensitiveDataTest extends FunSuite {

  test("isMatch returns true when string matches regex") {
    val input: String = "1-2-3"
    val patternList: List[Regex] = List("""\d-\d-\d""".r)

    val answer: Boolean = SensitiveDataObj.isMatch(input)(patternList)
    assert(answer)
  }

  test("isMatch returns true when string matches regex with more than 1 regex expressions") {
    val input: String = "1-2-3"
    val patternList: List[Regex] = List("""[A-Z]""".r, """\d-\d-\d""".r)

    val answer: Boolean = SensitiveDataObj.isMatch(input)(patternList)
    assert(answer)
  }

  test("isMatch returns false when string doesn't match regex with more than 1 regex expressions") {
    val input: String = "1-2-3"
    val patternList: List[Regex] = List("""[a-z]""".r, """[A-Z]""".r)

    val answer: Boolean = SensitiveDataObj.isMatch(input)(patternList)
    assert(!answer)
  }
}


object SensitiveDataObj extends SensitiveData {
  override val patternList: List[Regex] = Nil
  override val contextKeyWords: List[String] = Nil

  override def isCheckSum(input: String): Boolean = true


}
