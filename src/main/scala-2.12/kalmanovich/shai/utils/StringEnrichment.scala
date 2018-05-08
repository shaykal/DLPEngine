package kalmanovich.shai.utils

/**
  * Created by shay kalmanovich on 5/7/2018.
  */
object StringEnrichment {

  implicit class StringEnrichmentCls(input : String) {

    def getIndexOfKeywordFromInput(contextKeyWords: List[String]): Int = {
      val lowerCaseInput = input.toLowerCase
      val answer: Seq[Int] = contextKeyWords.map(keyWord => lowerCaseInput.indexOf(keyWord.toLowerCase))
                                            .filter(index => index >= 0)
      answer match {
        case Nil => -1
        case _ => answer.head
      }
    }


    def countWordsDistance(indexOfKeywords: Int, indexOfSensitiveDate: Int): Int = {
      val subString = input.substring(indexOfKeywords, indexOfSensitiveDate)
      subString.split(Consts.SPACE).length
    }

  }

}