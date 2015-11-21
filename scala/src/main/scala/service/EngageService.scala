package service

import datasource.engagement.EngagementRepository
import datasource.engagement.mobile.line.LineRepository
import datasource.engagement.mobile.line.sim_card.SimCardRepository
import domain.engagement.mobile.line.Line
import domain.engagement.mobile.line.sim_card.SimCard
import domain.engagement.mobile.mnp_in.MnpIn
import domain.engagement.{Engagement, EngagementNumber, Fullname, Plan}

object EngageService {
  def engage(fullname: Fullname, plan: Plan, mnpIn: Option[MnpIn]) = {
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
      null
    )

    EngagementRepository.engage(engagement)
  }

  def find(engagementNumber: EngagementNumber): Option[Engagement] = {
    EngagementRepository.find(engagementNumber)
  }
}
