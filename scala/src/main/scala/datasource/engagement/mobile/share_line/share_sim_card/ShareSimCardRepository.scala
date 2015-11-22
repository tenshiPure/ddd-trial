package datasource.engagement.mobile.share_line.share_sim_card

import datasource._Database
import datasource.engagement.mobile.share_line.share_sim_card.share_mnp_in.ShareMnpInRepository
import domain.engagement.mobile.share_line.share_sim_card.{ShareSimCard, ShareSimCardNumber}
import domain.engagement.mobile.share_line.{ShareLine, ShareLineNumber}

import scala.slick.driver.SQLiteDriver.simple._

object ShareSimCardRepository {
  def allocateShareSimCardNumber: ShareSimCardNumber = new ShareSimCardNumber(Mapper.allocate)

  object Mapper {

    def name = "ShareSimCards"

    class _ShareSimCards(tag: Tag) extends Table[(Int, String, String)](tag, name) {
      def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

      def shareLineNumber = column[String]("share_line_number")

      def shareSimCardNumber = column[String]("share_sim_card_number")

      def * = (id, shareLineNumber, shareSimCardNumber)
    }

    def allocate: Int = _Database.allocate(Mapper.name)

    def insert(shareLine: ShareLine) = {
      _Database.connect() withSession { implicit session =>
        TableQuery[_ShareSimCards] +=(0, shareLine.shareLineNumber.value, shareLine.shareSimCard.shareSimCardNumber.value)
      }
    }

    def find(shareLineNumber: ShareLineNumber): ShareSimCard = {
      _Database.connect() withSession { implicit session =>
        val _shareSimCards = TableQuery[_ShareSimCards].filter(_.shareLineNumber === shareLineNumber.value).list

        if (_shareSimCards.isEmpty) throw new Exception("no such ShareSimCard found by " + shareLineNumber)
        else {
          val shareSimCardNumber = new ShareSimCardNumber(_shareSimCards.head._3)

          ShareSimCard(
            shareSimCardNumber,
            ShareMnpInRepository.Mapper.find(shareSimCardNumber)
          )
        }
      }
    }
  }

}
