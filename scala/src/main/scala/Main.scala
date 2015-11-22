import datasource.{Fixture, _Database}
import service.Adapter

object Main {
  def main(args: Array[String]): Unit = {
    println("\ntest engage")

    // mnp-in with no share lines
    testEngage("en1", "Solid Snake", "NormalPlan", "090-1111-1111", List())

    // no mnp-in with no share lines
    testEngage("en1", "Solid Snake", "SpecialPlan", "", List())

    // one share-line with mnp-in
    testEngage("en1", "Solid Snake", "NormalPlan", "090-1111-1111", List("090-2222-2222"))

    // two share-lines with mnp-ins
    testEngage("en1", "Solid Snake", "NormalPlan", "090-1111-1111", List("090-2222-2222", "090-3333-3333"))

    // two share-lines with no mnp-in and mnp-in
    testEngage("en1", "Solid Snake", "NormalPlan", "090-1111-1111", List("", "090-3333-3333"))


    println("\ntest engage (irregular)")

    // invalid plan
    testEngage("en1", "Raiden", "TimeParadox", "090-1111-1111", List())


    println("\ntest find (normal)")

    // mnp-in with no share lines
    testFind(Fixture.mnpInWithNoShareLines)

    // no mnp-in with no share lines
    testFind(Fixture.noMnpInWithNoShareLines)

    // one share-line with mnp-in
    testFind(Fixture.oneShareLineWithMnpIn)

    // two share-lines with mnp-ins
    testFind(Fixture.twoShareLinesWithMnpIns)

    // two share-lines with no mnp-in and mnp-in
    testFind(Fixture.twoShareLinesWithNoMnpAndMnpIn)


    println("\ntest find (irregular)")

    // invalid foreign key
    testFind(Fixture.noLine)
  }

  private def testEngage(en: String, name: String, plan: String, msisdn: String, msisdns: List[String]) = {
    _Database.init()

    try {
      Adapter.engage(name, plan, msisdn, msisdns)
      find(en)
    } catch {
      case e: Exception => println(e.getMessage)
    }

  }

  private def testFind(fixture: Fixture) = {
    _Database.init()

    _Database.insertFixture(fixture)

    find(fixture.en)
  }

  private def find(key: String) = {
    try {
      println(
        Adapter.find(key)
      )
    } catch {
      case e: Exception => println(e.getMessage)
    }
  }
}
