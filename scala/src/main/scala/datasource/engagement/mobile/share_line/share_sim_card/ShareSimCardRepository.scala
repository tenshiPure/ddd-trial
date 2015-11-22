package datasource.engagement.mobile.share_line.share_sim_card

import datasource._Database
import datasource.engagement.mobile.line.sim_card.mnp_in.MnpInRepository
import domain.engagement.mobile.line.LineNumber
import domain.engagement.mobile.line.sim_card.{SimCard, SimCardNumber}
import domain.engagement.mobile.share_line.ShareLine
import domain.engagement.mobile.share_line.share_sim_card.ShareSimCardNumber

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
        val _shareSimCards = TableQuery[_ShareSimCards]
        _shareSimCards +=(0, shareLine.shareLineNumber.value, shareLine.shareSimCard.shareSimCardNumber.value)
      }
    }

    def find(lineNumber: LineNumber): SimCard = {
      _Database.connect() withSession { implicit session =>
        val _shareSimCards = TableQuery[_ShareSimCards].filter(_.shareLineNumber === lineNumber.value).list

        if (_shareSimCards.isEmpty) throw new Exception("no such SimCard found by " + lineNumber)
        else {
          val simCardNumber = new SimCardNumber(_shareSimCards.head._3)

          SimCard(
            simCardNumber,
            MnpInRepository.Mapper.find(simCardNumber)
          )
        }
      }
    }
  }

}
