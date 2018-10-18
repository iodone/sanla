package sanla

/**
  * Created by iodone on {18-10-17}.
  */

import scala.util.parsing.combinator._
//import scala.collection.mutable.Map
/**
  * SAN BNF
  * san  ::= expr {"\n" expr}
  * expr ::= string "=" value
  * value ::= string|int|float|arr|obj|"null"|"true"|"false"
  * arr ::= "[" [values] "]"
  * obj ::= "{" [san] "}"
  * values ::= value {"," value}
  */

class SanPaser extends JavaTokenParsers {

  def san: Parser[Map[String, Any]] = repsep(expr, "") ^^ (Map() ++ _)

  def expr: Parser[(String, Any)] = string~"="~value ^^ {
    case name~"="~value => (name, value)
  }

  def value: Parser[Any] = stringLiteral | arr | obj | floatingPointNumber ^^ (_.toDouble) | "null" ^^ (_ => null) | "true" ^^ (_ => true) | "false" ^^ (_ => false)

  def arr: Parser[List[Any]] = "["~> repsep(value, ",") <~"]"

  def obj: Parser[Map[String, Any]] = "{"~> repsep(expr, "") <~"}" ^^ (Map() ++ _)

  def string: Parser[String] = """[a-zA-Z_]\w*""".r

}

object San extends SanPaser {
  def parse(content: String): Option[Map[String, Any]] = {
    parseAll(san, content) match {
      case Success(result, _) => Some(result)
      case Failure(ex, _) =>  None
    }

  }
}

