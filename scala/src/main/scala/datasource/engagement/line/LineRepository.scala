package datasource.engagement.line

import datasource._Database
import domain.engagement.Engagement

import scala.slick.driver.SQLiteDriver.simple._

object LineRepository {

  object Mapper {

    def name = "Lines"

    class _Lines(tag: Tag) extends Table[(Int, String, String)](tag, name) {
      def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

      def engagementNumber = column[String]("engagement_number")

      def lineNumber = column[String]("line_number")

      def * = (id, engagementNumber, lineNumber)
    }

    def insert(engagement: Engagement) = {
      _Database.connect() withSession { implicit session =>
        val _lines = TableQuery[_Lines]
        _lines +=(0, engagement.engagementNumber.value, engagement.line.lineNumber.value)
      }
    }
  }

}
