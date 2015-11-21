package datasource.engagement.line.mnp_in

import datasource._Database
import domain.engagement.Engagement

import scala.slick.driver.SQLiteDriver.simple._

object MnpInRepository {

  object _Repository {

    def name = "MnpIns"

    class _MnpIns(tag: Tag) extends Table[(Int, String)](tag, name) {
      def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

      def msisdn = column[String]("msisdn")

      def * = (id, msisdn)
    }

    def insert(allocated: Int, engagement: Engagement) = {
      _Database.connect() withSession { implicit session =>
        val _mnpIns = TableQuery[_MnpIns]
        _mnpIns +=(allocated, engagement.line.simCard.mnpIn.get.msisdn.value)
      }
    }
  }

}
