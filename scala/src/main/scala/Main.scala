import datasource._Database
import service.Adapter

object Main {
  def main(args: Array[String]): Unit = {
    _Database.init()

//    Adapter.engage("john doe", "normal", "090-1234-5678")

    try {
      println(
        Adapter.find("en1")
      )
    } catch {
      case e: Exception => println(e.getMessage)
    }
  }
}
