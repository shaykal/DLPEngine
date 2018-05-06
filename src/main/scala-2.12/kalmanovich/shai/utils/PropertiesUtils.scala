package kalmanovich.shai.utils

import com.typesafe.config.ConfigFactory

import scala.util.Try

/**
  * Created by Shai Kalmanovich on 12/13/2017.
  */
object PropertiesUtils {

  lazy val rulesSsnKeywords: String = load(Consts.RULES_SSN_KEYWORDS)
  lazy val rulesSsnIsActive: Boolean = load(Consts.RULES_SSN_IS_ACTIVE, "false").toBoolean
  lazy val wordsDistance: Int = load(Consts.WORDS_DISTANCE).toInt


  /**
    * <i>load</i> - This method loads the properties from the applcation.conf file.
    * It can be controlled by VM property e.g. -Dconfig.file=src\test\resources\application-test.conf
    * @param entry - The entry as it appears in the config file.
    * @param default - the default value in case the entry doesn't exist in the config file.
    * @return - The string of the value of the property name.
    */
  def load(entry: String, default: String = ""): String = {
    Try(ConfigFactory.load.getString(entry)).getOrElse(default)
  }
}