package kalmanovich.shai.model.Impl

import org.scalatest.FunSuite

/**
  * Created by Shai Kalmanovich on 5/3/2018.
  */
class SSNTest extends FunSuite {

  //implicit val ssnPatternList: List[Regex] = SSN.patternList

  test("SSN matches true on valid SSN entry") {
    val validSSNInput1 = "123456789"
    val validSSNInput2 = "123-45-6789"
    val validSSNInput3 = "123 45 6789"
    val answer1 = SSN.isMatch(validSSNInput1)
    val answer2 = SSN.isMatch(validSSNInput2)
    val answer3 = SSN.isMatch(validSSNInput3)
    assert(answer1)
    assert(answer2)
    assert(answer3)
  }

  test("SSN matches false on invalid SSN entry") {
    val invalidSSNInputList = List(
      """1234567890""",
      """12345678""",
      """123-456-6789""",
      """123-456-789""",
      """1234 45 6789""",
      """123 45 67g9""",
      """12 34 45 6789"""
    )

    val answer = invalidSSNInputList.forall(invalidInput => !SSN.isMatch(invalidInput))
    assert(answer)
  }

}
