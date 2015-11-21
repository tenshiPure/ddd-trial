package datasource.engagement.mobile.line.sim_card

import datasource._Database
import domain.engagement.mobile.line.Line
import domain.engagement.mobile.line.sim_card.SimCardNumber

import scala.slick.driver.SQLiteDriver.simple._

object SimCardRepository {
  def allocateSimCardNumber: SimCardNumber = new SimCardNumber(Mapper.allocate)

  object Mapper {

    def name = "SimCards"

    class _SimCards(tag: Tag) extends Table[(Int, String, String)](tag, name) {
      def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

      def lineNumber = column[String]("line_number")

      def simCardNumber = column[String]("sim_card_number")

      def * = (id, lineNumber, simCardNumber)
    }

    def allocate: Int = _Database.allocate(Mapper.name)

    def insert(line: Line) = {
      _Database.connect() withSession { implicit session =>
        val _lines = TableQuery[_SimCards]
        _lines +=(0, line.lineNumber.value, line.simCard.simCardNumber.value)
      }
    }
  }

}
