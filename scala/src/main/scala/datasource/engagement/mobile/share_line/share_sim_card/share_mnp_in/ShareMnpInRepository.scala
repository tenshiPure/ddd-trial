package datasource.engagement.mobile.share_line.share_sim_card.share_mnp_in

import datasource._Database
import domain.engagement.mobile.mnp_in.{MnpIn, Msisdn}
import domain.engagement.mobile.share_line.share_sim_card.ShareSimCardNumber

import scala.slick.driver.SQLiteDriver.simple._

object ShareMnpInRepository {

  object Mapper {

    def name = "ShareMnpIns"

    class _ShareMnpIns(tag: Tag) extends Table[(Int, String, String)](tag, name) {
      def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

      def shareSimCardNumber = column[String]("share_sim_card_number")

      def msisdn = column[String]("msisdn")

      def * = (id, shareSimCardNumber, msisdn)
    }

    def insert(shareSimCardNumber: ShareSimCardNumber, mnpIn: MnpIn) = {
      _Database.connect() withSession { implicit session =>
        TableQuery[_ShareMnpIns] +=(0, shareSimCardNumber.value, mnpIn.msisdn.value)
      }
    }

    def find(shareSimCardNumber: ShareSimCardNumber): Option[MnpIn] = {
      _Database.connect() withSession { implicit session =>
        val _shareMnpIns = TableQuery[_ShareMnpIns].filter(_.shareSimCardNumber === shareSimCardNumber.value).list

        if (_shareMnpIns.isEmpty) None
        else {
          Some(
            MnpIn(
              Msisdn(_shareMnpIns.head._3)
            )
          )
        }
      }
    }
  }

}
