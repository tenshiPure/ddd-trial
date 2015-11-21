package datasource

object Fixture {
  def allInOne: Fixture = new Fixture(1, toEn(1), "Solid Snake", "normal", toLn(1), toCn(1), "090-1111-1111")

  def noMnpIn: Fixture = new Fixture(1, toEn(1), "Solid Snake", "normal", toLn(1), toCn(1))

  def noLine: Fixture = new Fixture(1, toEn(1), "Solid Snake", "normal")

  private def toEn(n: Int): String = "en" + n.toString

  private def toLn(n: Int): String = "ln" + n.toString

  private def toCn(n: Int): String = "cn" + n.toString
}

case class Fixture(n: Int, en: String, name: String, plan: String, ln: Option[String], cn: Option[String], msisdn: Option[String]) {
  // all in one
  def this(n: Int, en: String, name: String, plan: String, ln: String, cn: String, msisdn: String) = this(n, en, name, plan, Some(ln), Some(cn), Some(msisdn))

  // no mnp-in
  def this(n: Int, en: String, name: String, plan: String, ln: String, cn: String) = this(n, en, name, plan, Some(ln), Some(cn), None)

  // no line (invalid)
  def this(n: Int, en: String, name: String, plan: String) = this(n, en, name, plan, None, None, None)
}
