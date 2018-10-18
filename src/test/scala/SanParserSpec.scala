package sanla
/**
  * Created by iodone on {18-10-17}.
  */

import org.scalatest._
import scala.io.Source
import java.io.FileReader

class SanParserSpec extends FlatSpec with Matchers {

  "Sanla paser" should "parse san format file to Map" in {
    val sanFileContent = Source.fromFile("/home/work/app/github/sanla/src/test/resources/san-test.conf").getLines.mkString("\n")
    val ret = San.parse(sanFileContent)
    println(ret.get)
    assert(ret.isInstanceOf[Option[Map[String, Any]]])
    assert(ret != None)
  }


}
