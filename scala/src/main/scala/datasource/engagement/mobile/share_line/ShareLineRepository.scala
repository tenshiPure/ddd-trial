package datasource.engagement.mobile.share_line

import datasource._Database
import datasource.engagement.mobile.share_line.share_sim_card.ShareSimCardRepository
import domain.engagement.EngagementNumber
import domain.engagement.mobile.share_line.{ShareLine, ShareLineNumber}

import scala.slick.driver.SQLiteDriver.simple._

object ShareLineRepository {
  def allocateShareLineNumber: ShareLineNumber = new ShareLineNumber(Mapper.allocate)

  object Mapper {

    def name = "ShareLines"

    class _ShareLines(tag: Tag) extends Table[(Int, String, String)](tag, name) {
      def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

      def engagementNumber = column[String]("engagement_number")

      def shareLineNumber = column[String]("share_line_number")

      def * = (id, engagementNumber, shareLineNumber)
    }

    def allocate: Int = _Database.allocate(Mapper.name)

    def insert(engagementNumber: EngagementNumber, shareLine: ShareLine) = {
      _Database.connect() withSession { implicit session =>
        TableQuery[_ShareLines] +=(0, engagementNumber.value, shareLine.shareLineNumber.value)
      }
    }

    def find(engagementNumber: EngagementNumber): List[ShareLine] = {
      _Database.connect() withSession { implicit session =>
        val _shareLines = TableQuery[_ShareLines].filter(_.engagementNumber === engagementNumber.value).list

        _shareLines.map(
          _shareLine => {
            val shareLineNumber = new ShareLineNumber(_shareLine._3)

            ShareLine(
              shareLineNumber,
              ShareSimCardRepository.Mapper.find(shareLineNumber)
            )
          }
        )
      }
    }
  }

}
