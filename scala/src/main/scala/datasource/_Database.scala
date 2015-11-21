package datasource

import java.io.File

import scala.slick.driver.SQLiteDriver.simple._

import datasource.engagement.EngagementRepository

object _Database {
  val path = "src/main/resources/db/test.sqlite3"

  def exists(): Boolean = new File(path).exists()

  def connect() = Database.forURL("jdbc:sqlite:" + path, driver = "org.sqlite.JDBC")

  def init() = {
    _Database.connect() withSession { implicit session =>
      if (!_Database.exists()) {
        TableQuery[EngagementRepository._Repository._Engagements].ddl.create
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
