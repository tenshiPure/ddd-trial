package datasource

object Fixture {
  def mnpInWithNoShareLines: Fixture =
    new Fixture(1, toEn(1), "Solid Snake", "NormalPlan", toLn(1), toCn(1), Some("090-1111-1111"), List())

  def noMnpInWithNoShareLines: Fixture =
    new Fixture(1, toEn(1), "Solid Snake", "SpecialPlan", toLn(1), toCn(1), None, List())

  def oneShareLineWithMnpIn: Fixture =
    new Fixture(1, toEn(1), "Solid Snake", "NormalPlan", toLn(1), toCn(1), Some("090-1111-1111"), List((toSl(1), toSc(1), Some("090-2222-2222"))))

  def twoShareLinesWithMnpIns: Fixture =
    new Fixture(1, toEn(1), "Solid Snake", "NormalPlan", toLn(1), toCn(1), Some("090-1111-1111"), List((toSl(1), toSc(1), Some("090-2222-2222")), (toSl(2), toSc(2), Some("090-3333-3333"))))

  def twoShareLinesWithNoMnpAndMnpIn: Fixture =
    new Fixture(1, toEn(1), "Solid Snake", "NormalPlan", toLn(1), toCn(1), Some("090-1111-1111"), List((toSl(1), toSc(1), None), (toSl(2), toSc(2), Some("090-3333-3333"))))

  def noLine: Fixture =
    new Fixture(1, toEn(1), "Raiden", "NormalPlan")

  private def toEn(n: Int): String = "en" + n.toString

  private def toLn(n: Int): String = "ln" + n.toString

  private def toCn(n: Int): String = "cn" + n.toString

  private def toSl(n: Int): String = "sl" + n.toString

  private def toSc(n: Int): String = "sc" + n.toString
}

case class Fixture(n: Int, en: String, name: String, plan: String, ln: Option[String], cn: Option[String], msisdn: Option[String], shares: List[(String, String, Option[String])]) {
  def this(n: Int, en: String, name: String, plan: String, ln: String, cn: String, msisdn: Option[String], shares: List[(String, String, Option[String])]) =
    this(n, en, name, plan, Some(ln), Some(cn), msisdn, shares)

  // no line (invalid)
  def this(n: Int, en: String, name: String, plan: String) =
    this(n, en, name, plan, None, None, None, List())
}
