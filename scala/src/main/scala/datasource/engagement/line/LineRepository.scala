package datasource.engagement.line

import datasource._Database
import domain.engagement.Engagement

import scala.slick.driver.SQLiteDriver.simple._

object LineRepository {

  object _Repository {

    def name = "Lines"

    class _Lines(tag: Tag) extends Table[(Int, String)](tag, name) {
      def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

      def engagementNumber = column[String]("engagement_number")

      def * = (id, engagementNumber)
    }

    def insert(allocated: Int, engagement: Engagement) = {
      _Database.connect() withSession { implicit session =>
        val _lines = TableQuery[_Lines]
        _lines +=(allocated, engagement.engagementNumber.value)
      }
    }
  }

}
