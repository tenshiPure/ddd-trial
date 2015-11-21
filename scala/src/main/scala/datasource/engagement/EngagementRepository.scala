package datasource.engagement

import datasource._Database
import datasource.engagement.mobile.line.LineRepository
import datasource.engagement.mobile.line.sim_card.SimCardRepository
import datasource.engagement.mobile.mnp_in.MnpInRepository
import domain.engagement.{Engagement, EngagementNumber, Fullname, _Plan}

import scala.slick.driver.SQLiteDriver.simple._

object EngagementRepository {
  def allocateEngagementNumber: EngagementNumber = new EngagementNumber(Mapper.allocate)

  def engage(engagement: Engagement) = {
    Mapper.insert(engagement)

    LineRepository.Mapper.insert(engagement)

    SimCardRepository.Mapper.insert(engagement.line)

    engagement.line.simCard.mnpIn match {
      case Some(v) => MnpInRepository.Mapper.insert(engagement.line.simCard.simCardNumber, v)
      case _ => // do nothing
    }
  }

  def find(engagementNumber: EngagementNumber): Option[Engagement] = {
    Mapper.find(engagementNumber)
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

    def find(engagementNumber: EngagementNumber): Option[Engagement] = {
      _Database.connect() withSession { implicit session =>
        val _list = TableQuery[_Engagements].filter(_.engagementNumber === engagementNumber.value).list

        if (_list.isEmpty) None
        else {
          val row = _list.head

          Some(
            Engagement(
              new EngagementNumber(row._2),
              Fullname(row._3),
              _Plan.create(row._4),
              LineRepository.Mapper.find(engagementNumber),
              null
            )
          )
        }
      }
    }
  }

}
