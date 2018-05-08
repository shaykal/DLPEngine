package kalmanovich.shai.model.Impl

import org.scalatest.FunSuite

/**
  * Created by Shai Kalmanovich on 5/8/2018.
  */
class CreditCardTest extends FunSuite {

  test("Credit Card matches true on valid Credit Card entry") {
    val validCreditCardInput1 = "1234567890123456"
    val validCreditCardInput2 = "1234-5678-9012-3456"
    val validCreditCardInput3 = "1234 5678 9012 3456"
    val answer1 = CreditCard.getSensitiveData(validCreditCardInput1)
    val answer2 = CreditCard.getSensitiveData(validCreditCardInput2)
    val answer3 = CreditCard.getSensitiveData(validCreditCardInput3)
    assert(answer1.get == "1234567890123456")
    assert(answer2.get == "1234-5678-9012-3456")
    assert(answer3.get == "1234 5678 9012 3456")
  }

  test("Credit Card matches false on invalid Credit Card entry") {
    val invalidCreditCardInputList = List(
      """1234567890""",
      """1234-4566-6789-123""",
      """123-456-789""",
      """1234 4567 67g9 1234""",
      """12 34 45 6789"""
    )

    val answer = invalidCreditCardInputList.forall(invalidInput => CreditCard.getSensitiveData(invalidInput).isEmpty)
    assert(answer)
  }


  test("checkLuhn returns true on valid credit card numbers") {
    val validCreditCardNumber1 = "79927398713"
    val answer1 = CreditCard.checkLuhn(validCreditCardNumber1)
    assert(answer1)

    val validCreditCardNumber2 = "4556737586899855"
    val answer2 = CreditCard.checkLuhn(validCreditCardNumber2)
    assert(answer2)

    val validCreditCardNumber3 = "4012 8888 8888 1881"
    val answer3 = CreditCard.checkLuhn(validCreditCardNumber3)
    assert(answer3)

    val validCreditCardNumber4 = "4556-7375-8689-9855"
    val answer4 = CreditCard.checkLuhn(validCreditCardNumber4)
    assert(answer4)


  }

  test("checkLuhn returns false on invalid credit card number") {
    val validCreditCardNumber = "79927398714"
    val answer = CreditCard.checkLuhn(validCreditCardNumber)
    assert(!answer)
  }
}
