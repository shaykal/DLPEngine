package kalmanovich.shai.utils

import com.typesafe.config.ConfigFactory

import scala.util.Try

/**
  * Created by Shai Kalmanovich on 12/13/2017.
  */
object PropertiesUtils {

  //lazy val serverHost: String = load(Consts.SERVER_HOST)
  //lazy val serverPort: Int = load(Consts.SERVER_PORT).toInt
  //lazy val maxTimeToWait: Int = load(Consts.MAX_TIME_TO_WAIT).toInt


  /**
    * <i>load</i> - This method loads the properties from the applcation.conf file.
    * It can be controlled by VM property e.g. -Dconfig.file=src\test\resources\application-test.conf
    * @return - The string of the value of the property name.
    */
  def load(entry: String): String = {
    Try(ConfigFactory.load.getString(entry)).getOrElse(Consts.EMPTY_STRING)
  }
}