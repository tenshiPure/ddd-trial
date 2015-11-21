import java.io.File

import scala.slick.driver.SQLiteDriver.simple._

object Main {
  def main(args: Array[String]): Unit = {
    val path = "src/main/resources/db/test.sqlite3"

    val isNoFile = !new File(path).exists()

    class Suppliers(tag: Tag) extends Table[(Int, String)](tag, "SUPPLIERS") {
      def id = column[Int]("ID", O.PrimaryKey)

      def name = column[String]("name")

      def * = (id, name)
    }

    val suppliers = TableQuery[Suppliers]

    def initialize()(implicit session: Session): Unit = {
      suppliers.ddl.create
    }

    val con = Database.forURL("jdbc:sqlite:" + path, driver = "org.sqlite.JDBC")
    con withSession { implicit session =>
      if (isNoFile) initialize()

      val ls = suppliers.list
      println(ls)
    }
  }
}
