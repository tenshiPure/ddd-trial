import datasource._Database
import service.Adapter

object Main {
  def main(args: Array[String]): Unit = {
    _Database.init()

//    Adapter.engage("john doe", "normal", "090-1234-5678")

    val engagement = Adapter.find("en2")
    println(engagement)
  }
}
