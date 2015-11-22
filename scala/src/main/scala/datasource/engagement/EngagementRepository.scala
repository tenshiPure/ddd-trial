package datasource.engagement

import datasource._Database
import datasource.engagement.mobile.line.LineRepository
import datasource.engagement.mobile.line.sim_card.SimCardRepository
import datasource.engagement.mobile.line.sim_card.mnp_in.MnpInRepository
import datasource.engagement.mobile.share_line.ShareLineRepository
import datasource.engagement.mobile.share_line.share_sim_card.ShareSimCardRepository
import datasource.engagement.mobile.share_line.share_sim_card.share_mnp_in.ShareMnpInRepository
import domain.engagement.mobile.share_line.ShareLines
import domain.engagement.{Engagement, EngagementNumber, Fullname, Plan}

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

    engagement.shareLines.shareLines.foreach(
      shareLine => {
        ShareLineRepository.Mapper.insert(engagement.engagementNumber, shareLine)
        ShareSimCardRepository.Mapper.insert(shareLine)
        shareLine.shareSimCard.mnpIn match {
          case Some(v) => ShareMnpInRepository.Mapper.insert(shareLine.shareSimCard.shareSimCardNumber, v)
          case _ => // do nothing
        }
      }
    )
  }

  def find(engagementNumber: EngagementNumber): Option[Engagement] = {
    Mapper.find(engagementNumber)
  }

  def planChange(engagementNumber: EngagementNumber, dstPlan: Plan) = {
    find(engagementNumber) match {
      case Some(engagement) => Mapper.update(engagement.planChange(dstPlan))
      case _ => throw new Exception("no such Engagement found by " + engagementNumber)
    }
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
        TableQuery[_Engagements] +=(0, engagement.engagementNumber.value, engagement.fullname.value, engagement.plan.toString)
      }
    }

    def find(engagementNumber: EngagementNumber): Option[Engagement] = {
      _Database.connect() withSession { implicit session =>
        val _engagements = TableQuery[_Engagements].filter(_.engagementNumber === engagementNumber.value).list

        if (_engagements.isEmpty) None
        else {
          val row = _engagements.head

          Some(
            Engagement(
              new EngagementNumber(row._2),
              Fullname(row._3),
              Plan.create(row._4),
              LineRepository.Mapper.find(engagementNumber),
              ShareLines(
                ShareLineRepository.Mapper.find(engagementNumber)
              )
            )
          )
        }
      }
    }

    def update(engagement: Engagement) = {
      _Database.connect() withSession { implicit session =>
        val _pk = TableQuery[_Engagements].filter(_.engagementNumber === engagement.engagementNumber.value).list.head._1

        TableQuery[_Engagements].update(_pk, engagement.engagementNumber.value, engagement.fullname.value, engagement.plan.toString)
      }
    }
  }

}
