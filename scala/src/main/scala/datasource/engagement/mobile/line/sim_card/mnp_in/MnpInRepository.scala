package datasource.engagement.mobile.line.sim_card.mnp_in

import datasource._Database
import domain.engagement.mobile.line.sim_card.SimCardNumber
import domain.engagement.mobile.mnp_in.{MnpIn, Msisdn}

import scala.slick.driver.SQLiteDriver.simple._

object MnpInRepository {

  object Mapper {

    def name = "MnpIns"

    class _MnpIns(tag: Tag) extends Table[(Int, String, String)](tag, name) {
      def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

      def simCardNumber = column[String]("sum_card_number")

      def msisdn = column[String]("msisdn")

      def * = (id, simCardNumber, msisdn)
    }

    def insert(simCardNumber: SimCardNumber, mnpIn: MnpIn) = {
      _Database.connect() withSession { implicit session =>
        val _mnpIns = TableQuery[_MnpIns]
        _mnpIns +=(0, simCardNumber.value, mnpIn.msisdn.value)
      }
    }

    def find(simCardNumber: SimCardNumber): Option[MnpIn] = {
      _Database.connect() withSession { implicit session =>
        val _mnpIns = TableQuery[_MnpIns].filter(_.simCardNumber === simCardNumber.value).list

        if (_mnpIns.isEmpty) None
        else {
          Some(
            MnpIn(
              Msisdn(_mnpIns.head._3)
            )
          )
        }
      }
    }
  }

}
