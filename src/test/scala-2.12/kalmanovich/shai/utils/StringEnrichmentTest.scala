package kalmanovich.shai.utils

import org.scalatest.FunSuite

/**
  * Created by shay kalmanovich on 5/7/2018.
  */
class StringEnrichmentTest extends FunSuite {


   test("getIndexOfKeywordFromInput returns positive number when keyword exists in the text") {
    val inputText: String = "This is an input text"
    val keyWords: List[String] = List("input","test","blabla")
    val answer = StringEnrichment.StringEnrichmentCls(inputText).getIndexOfKeywordFromInput(keyWords)
    assert(answer >= 0)
  }

  test("getIndexOfKeywordFromInput returns positive number when keyword that is not the first in the list exists in the text") {
    val inputText: String = "This is an input text"
    val keyWords: List[String] = List("input1","text","blabla")
    val answer = StringEnrichment.StringEnrichmentCls(inputText).getIndexOfKeywordFromInput(keyWords)
    assert(answer >= 0)
  }

  test("getIndexOfKeywordFromInput returns positive number when 2 or more keywords exist in the text") {
    val inputText: String = "This is an input text"
    val keyWords: List[String] = List("text","input", "blabla")
    val answer = StringEnrichment.StringEnrichmentCls(inputText).getIndexOfKeywordFromInput(keyWords)
    println(answer)
    assert(answer >= 0)
  }

  test("getIndexOfKeywordFromInput returns positive number when keyword exists in the text and is case insensitive") {
    val inputText: String = "This is an input text"
    val keyWords: List[String] = List("Text","Input", "Blabla")
    val answer = StringEnrichment.StringEnrichmentCls(inputText).getIndexOfKeywordFromInput(keyWords)
    println(answer)
    assert(answer >= 0)
  }

  test("getIndexOfKeywordFromInput returns positive number when keyword is composed of 2 words and exists in the text") {
    val inputText: String = "This is an input text"
    val keyWords: List[String] = List("an input","test","blabla")
    val answer = StringEnrichment.StringEnrichmentCls(inputText).getIndexOfKeywordFromInput(keyWords)
    assert(answer >= 0)
  }

  test("getIndexOfKeywordFromInput returns -1 when keyword doesnt exist in the text") {
    val inputText: String = "This is an input text"
    val keyWords: List[String] = List("input1","test1","blabla1")
    val answer = StringEnrichment.StringEnrichmentCls(inputText).getIndexOfKeywordFromInput(keyWords)
    assert(answer == -1)
  }


  test("countWordsDistance returns correct distance of whole sentence") {
    val inputText: String = "This is an input text"
    val answer = StringEnrichment.StringEnrichmentCls(inputText).countWordsDistance(0, inputText.length)
    assert(answer == 5)
  }

  test("countWordsDistance returns correct distance substring of a sentence") {
    val inputText: String = "This is an input text"
    val answer = StringEnrichment.StringEnrichmentCls(inputText).countWordsDistance(5, inputText.length-4)
    assert(answer == 3)
  }

}
