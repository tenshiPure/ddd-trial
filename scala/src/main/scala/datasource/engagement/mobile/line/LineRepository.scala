package datasource.engagement.mobile.line

import datasource._Database
import datasource.engagement.mobile.line.sim_card.SimCardRepository
import domain.engagement.mobile.line.{Line, LineNumber}
import domain.engagement.{Engagement, EngagementNumber}

import scala.slick.driver.SQLiteDriver.simple._

object LineRepository {
  def allocateLineNumber: LineNumber = new LineNumber(Mapper.allocate)

  object Mapper {

    def name = "Lines"

    class _Lines(tag: Tag) extends Table[(Int, String, String)](tag, name) {
      def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

      def engagementNumber = column[String]("engagement_number")

      def lineNumber = column[String]("line_number")

      def * = (id, engagementNumber, lineNumber)
    }

    def allocate: Int = _Database.allocate(Mapper.name)

    def insert(engagement: Engagement) = {
      _Database.connect() withSession { implicit session =>
        TableQuery[_Lines] +=(0, engagement.engagementNumber.value, engagement.line.lineNumber.value)
      }
    }

    def find(engagementNumber: EngagementNumber): Line = {
      _Database.connect() withSession { implicit session =>
        val _lines = TableQuery[_Lines].filter(_.engagementNumber === engagementNumber.value).list

        if (_lines.isEmpty) throw new Exception("no such Line found by " + engagementNumber)
        else {
          val lineNumber = new LineNumber(_lines.head._3)

          Line(
            lineNumber,
            SimCardRepository.Mapper.find(lineNumber)
          )
        }
      }
    }
  }

}
