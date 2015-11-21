package datasource.engagement

import datasource._Database
import domain.engagement.Engagement

import scala.slick.driver.SQLiteDriver.simple._

object EngagementRepository {
  def engage(engagement: Engagement) = {
    val allocated = _Database.allocate(_Repository.name)
    _Repository.insert(allocated, engagement)
  }

  object _Repository {

    def name = "Engagements"

    class _Engagements(tag: Tag) extends Table[(Int, String, String, String)](tag, name) {
      def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

      def engagementNumber = column[String]("engagement_number")

      def fullname = column[String]("fullname")

      def plan = column[String]("plan")

      def * = (id, engagementNumber, fullname, plan)
    }

    def insert(allocated: Int, engagement: Engagement) = {
      _Database.connect() withSession { implicit session =>
        val _engagements = TableQuery[_Engagements]
        _engagements +=(allocated, engagement.engagementNumber.value, engagement.fullname.value, engagement.plan.toString)
      }
    }
  }

}
