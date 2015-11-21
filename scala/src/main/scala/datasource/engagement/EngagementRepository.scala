package datasource.engagement

import datasource._Database
import datasource.engagement.mobile.line.LineRepository
import datasource.engagement.mobile.mnp_in.MnpInRepository
import domain.engagement.{Engagement, EngagementNumber}

import scala.slick.driver.SQLiteDriver.simple._

object EngagementRepository {
  def allocateEngagementNumber: EngagementNumber = new EngagementNumber(Mapper.allocate)

  def engage(engagement: Engagement) = {
    Mapper.insert(engagement)

    LineRepository.Mapper.insert(engagement)

    MnpInRepository.Mapper.insert(engagement)
  }

  object Mapper {

    def name = "Engagements"

    class _Engagements(tag: Tag) extends Table[(Int, String, String, String)](tag, name) {
      def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

      def engagementNumber = column[String]("engagement_number")

      def fullname = column[String]("fullname")

      def plan = column[String]("plan")

      def * = (id, engagementNumber, fullname, plan)
    }

    def allocate: Int = _Database.allocate(Mapper.name)

    def insert(engagement: Engagement) = {
      _Database.connect() withSession { implicit session =>
        val _engagements = TableQuery[_Engagements]
        _engagements +=(0, engagement.engagementNumber.value, engagement.fullname.value, engagement.plan.toString)
      }
    }
  }

}
