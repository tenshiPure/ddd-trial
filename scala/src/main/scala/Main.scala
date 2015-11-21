import java.io.File

import domain.engagement._
import domain.engagement.line.Line
import domain.engagement.line.mnp_in.{MnpIn, Msisdn}
import domain.engagement.share_line.{ShareLine, ShareLines}
import domain.engagement.sim_card.SimCard

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

    println(
      create(hasMnpIn = false, hasShareLines = false, hasShareMnpIn = false)
    )

    println(
      create(hasMnpIn = true, hasShareLines = true, hasShareMnpIn = false)
    )

    println(
      create(hasMnpIn = true, hasShareLines = true, hasShareMnpIn = true)
    )

  }

  private def create(hasMnpIn: Boolean, hasShareLines: Boolean, hasShareMnpIn: Boolean): Engagement = {
    val mnpIn = if (hasMnpIn) Some(
      MnpIn(
        Msisdn("090-1234-5678")
      )
    )
    else None

    val shareMnpIn = if (hasShareMnpIn) Some(
      MnpIn(
        Msisdn("090-1234-5678")
      )
    )
    else None

    val shareLines = if (hasShareLines) List(
      ShareLine(
        SimCard(
          shareMnpIn
        )
      )
    )
    else List()

    Engagement(
      EngagementNumber("en1"),
      Fullname("john doe"),
      NormalPlan,
      Line(
        SimCard(
          mnpIn
        )
      ),
      ShareLines(
        shareLines
      )
    )
  }
}
