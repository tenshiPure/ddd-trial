import datasource.{Fixture, _Database}
import service.Adapter

object Main {
  def main(args: Array[String]): Unit = {
    println("\ntest engage")
    testEngage()
    //
    //    println("\ntest find (normal)")
    //    testFind(Fixture.allInOne)
    //    testFind(Fixture.noMnpIn)
    //
    //    println("\ntest find (irregular)")
    //    testFind(Fixture.noLine)
  }

  private def testEngage() = {
    _Database.init()

    Adapter.engage("john doe", "normal", "090-1234-5678")

    find("en1")
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
