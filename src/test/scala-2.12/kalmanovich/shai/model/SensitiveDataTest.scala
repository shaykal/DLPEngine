package kalmanovich.shai.model

import org.scalatest.FunSuite

import scala.util.matching.Regex

/**
  * Created by Shai Kalmanovich on 5/3/2018.
  */
class SensitiveDataTest extends FunSuite {

  test("getSensitiveData returns Some of input when string matches regex") {
    val input: String = "1-2-3"
    val patternList: List[Regex] = List("""\d-\d-\d""".r)

    val answer: Option[String] = SensitiveDataObj.getSensitiveData(input, patternList)
    assert(answer.get == "1-2-3")
  }

  test("getSensitiveData returns Some of input when string matches regex with more than 1 regex expressions") {
    val input: String = "1-2-3"
    val patternList: List[Regex] = List("""[A-Z]""".r, """\d-\d-\d""".r)

    val answer = SensitiveDataObj.getSensitiveData(input, patternList)
    assert(answer.get == "1-2-3")
  }

  test("getSensitiveData returns None when string doesn't match regex with more than 1 regex expressions") {
    val input: String = "1-2-3"
    val patternList: List[Regex] = List("""[a-z]""".r, """[A-Z]""".r)

    val answer = SensitiveDataObj.getSensitiveData(input, patternList)
    assert(answer.isEmpty)
  }

  test("isKeyWords returns true when keywords exist in the correct distance of words") {
    val input: String = "My social security number is 123-45-6789"
    val answer = SensitiveDataObj.isKeyWords(input, input.length-11, 10)
    assert(answer)
  }

  test("isKeyWords returns false when keywords exist but distance is too far") {
    val input: String = "My social security number should be 123-45-6789"
    val answer = SensitiveDataObj.isKeyWords(input, input.length-11, 2)
    assert(!answer)
  }
}


object SensitiveDataObj extends SensitiveData {
  override val name: String = "SensitiveDataObj"
  override val patternList: List[Regex] = Nil
  override val contextKeyWords: List[String] = List("My social")

  override def isCheckSum(input: String): Boolean = true

  override val isActive: Boolean = true

}
