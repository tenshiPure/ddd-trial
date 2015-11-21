package datasource.engagement.mobile.line.sim_card

import datasource._Database
import datasource.engagement.mobile.mnp_in.MnpInRepository
import domain.engagement.mobile.line.sim_card.{SimCard, SimCardNumber}
import domain.engagement.mobile.line.{Line, LineNumber}

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

    def find(lineNumber: LineNumber): SimCard = {
      _Database.connect() withSession { implicit session =>
        val _list = TableQuery[_SimCards].filter(_.lineNumber === lineNumber.value).list

        if (_list.isEmpty) throw new Exception("no such SimCard found by " + lineNumber)
        else {
          val simCardNumber = new SimCardNumber(_list.head._3)

          SimCard(
            simCardNumber,
            MnpInRepository.Mapper.find(simCardNumber)
          )
        }
      }
    }
  }

}
