package service

import datasource.engagement.EngagementRepository
import datasource.engagement.mobile.line.LineRepository
import datasource.engagement.mobile.line.sim_card.SimCardRepository
import datasource.engagement.mobile.share_line.ShareLineRepository
import datasource.engagement.mobile.share_line.share_sim_card.ShareSimCardRepository
import domain.engagement.mobile.line.Line
import domain.engagement.mobile.line.sim_card.SimCard
import domain.engagement.mobile.mnp_in.MnpIn
import domain.engagement.mobile.share_line.share_sim_card.ShareSimCard
import domain.engagement.mobile.share_line.{ShareLine, ShareLines}
import domain.engagement.{Engagement, EngagementNumber, Fullname, Plan}

object EngageService {
  def engage(fullname: Fullname, plan: Plan, mnpIn: Option[MnpIn], shareMnpIns: List[Option[MnpIn]]) = {
    val engagement = Engagement(
      EngagementRepository.allocateEngagementNumber,
      fullname,
      plan,
      Line(
        LineRepository.allocateLineNumber,
        SimCard(
          SimCardRepository.allocateSimCardNumber,
          mnpIn
        )
      ),
      ShareLines(
        shareMnpIns.map(
          shareMnpIn => ShareLine(
            ShareLineRepository.allocateShareLineNumber,
            ShareSimCard(
              ShareSimCardRepository.allocateShareSimCardNumber,
              shareMnpIn
            )
          )
        )
      )
    )

    EngagementRepository.engage(engagement)
  }

  def find(engagementNumber: EngagementNumber): Option[Engagement] = {
    EngagementRepository.find(engagementNumber)
  }

  def planChange(engagementNumber: EngagementNumber, dstPlan: Plan) = {
    EngagementRepository.planChange(engagementNumber, dstPlan)
  }
}
