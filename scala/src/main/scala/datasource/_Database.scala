package datasource

import java.io.File

import datasource.engagement.EngagementRepository
import datasource.engagement.line.LineRepository
import datasource.engagement.line.mnp_in.MnpInRepository

import scala.slick.driver.SQLiteDriver.simple._

object _Database {
  val path = "src/main/resources/db/test.sqlite3"

  def exists(): Boolean = new File(path).exists()

  def connect() = Database.forURL("jdbc:sqlite:" + path, driver = "org.sqlite.JDBC")

  def init() = {
    _Database.connect() withSession { implicit session =>
      if (!_Database.exists()) {
        TableQuery[EngagementRepository._Repository._Engagements].ddl.create
        TableQuery[LineRepository._Repository._Lines].ddl.create
        TableQuery[MnpInRepository._Repository._MnpIns].ddl.create
      }
    }
  }

  private class _Sequences(tag: Tag) extends Table[(String, Int)](tag, "sqlite_sequence") {
    def name = column[String]("name")

    def seq = column[Int]("seq")

    def * = (name, seq)
  }

  def allocate(name: String): Int = {
    _Database.connect() withSession { implicit session =>
      val _list = TableQuery[_Sequences].filter(_.name === name).list
      if (_list.isEmpty) 1 else _list.head._2
    }
  }
}
