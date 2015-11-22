package datasource

import java.io.File

import datasource.engagement.EngagementRepository
import datasource.engagement.mobile.line.LineRepository
import datasource.engagement.mobile.line.sim_card.SimCardRepository
import datasource.engagement.mobile.line.sim_card.mnp_in.MnpInRepository
import datasource.engagement.mobile.share_line.ShareLineRepository
import datasource.engagement.mobile.share_line.share_sim_card.ShareSimCardRepository
import datasource.engagement.mobile.share_line.share_sim_card.share_mnp_in.ShareMnpInRepository

import scala.slick.driver.SQLiteDriver.simple._

object _Database {
  val path = "src/main/resources/db/test.sqlite3"

  def exists(): Boolean = new File(path).exists()

  def connect() = Database.forURL("jdbc:sqlite:" + path, driver = "org.sqlite.JDBC")

  def init() = {
    drop()
    create()
  }

  private def drop() = {
    if (exists()) new File(path).delete()
  }

  private def create() = {
    _Database.connect() withSession { implicit session =>
      if (!_Database.exists()) {
        TableQuery[_Sequences].ddl.create

        TableQuery[EngagementRepository.Mapper._Engagements].ddl.create

        TableQuery[LineRepository.Mapper._Lines].ddl.create
        TableQuery[SimCardRepository.Mapper._SimCards].ddl.create
        TableQuery[MnpInRepository.Mapper._MnpIns].ddl.create

        TableQuery[ShareLineRepository.Mapper._ShareLines].ddl.create
        TableQuery[ShareSimCardRepository.Mapper._ShareSimCards].ddl.create
        TableQuery[ShareMnpInRepository.Mapper._ShareMnpIns].ddl.create
      }
    }
  }

  def insertFixture(fixture: Fixture) = {
    _Database.connect() withSession { implicit session =>
      TableQuery[EngagementRepository.Mapper._Engagements] +=(fixture.n, fixture.en, fixture.name, fixture.plan)

      fixture.ln match {
        case Some(ln) => TableQuery[LineRepository.Mapper._Lines] +=(fixture.n, fixture.en, ln)
        case _ => //do nothing
      }

      fixture.cn match {
        case Some(cn) => TableQuery[SimCardRepository.Mapper._SimCards] +=(fixture.n, fixture.ln.get, cn)
        case _ => //do nothing
      }

      fixture.msisdn match {
        case Some(msisdn) => TableQuery[MnpInRepository.Mapper._MnpIns] +=(fixture.n, fixture.cn.get, msisdn)
        case _ => //do nothing
      }
    }
  }

  private class _Sequences(tag: Tag) extends Table[(String, Int)](tag, "Sequences") {
    def name = column[String]("name")

    def seq = column[Int]("seq")

    def * = (name, seq)
  }

  def allocate(name: String): Int = {
    _Database.connect() withSession { implicit session =>
      val _sequences = TableQuery[_Sequences].filter(_.name === name).list

      if (_sequences.isEmpty) {
        TableQuery[_Sequences] +=(name, 1)
        1
      } else {
        val nextval = _sequences.head._2 + 1
        TableQuery[_Sequences].filter(_.name === name).update(name, nextval)
        nextval
      }
    }
  }
}
