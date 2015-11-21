package service

import datasource.engagement.EngagementRepository
import datasource.engagement.line.LineRepository
import datasource.engagement.sim_card.SimCardRepository
import domain.engagement.line.Line
import domain.engagement.line.mnp_in.MnpIn
import domain.engagement.sim_card.SimCard
import domain.engagement.{Engagement, Fullname, Plan}

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

    println(engagement)
    //    EngagementRepository.engage(engagement)
  }
}
