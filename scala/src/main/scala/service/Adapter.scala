package service

import domain.engagement._
import domain.engagement.mobile.mnp_in.{MnpIn, Msisdn}

object Adapter {
  def engage(fullname: String, planCode: String, msisdn: String, shareMsisdns: List[String]) = {
    EngageService.engage(
      Fullname(fullname),
      Plan.create(planCode),
      createMnpIn(msisdn),
      shareMsisdns.map(createMnpIn)
    )
  }

  private def createMnpIn(msisdn: String): Option[MnpIn] = if (msisdn == "") None else Some(MnpIn(Msisdn(msisdn)))

  def find(engagementNumber: String): Option[Engagement] = {
    EngageService.find(new EngagementNumber(engagementNumber))
  }

  def planChange(engagementNumber: String, dstPlanCode: String) = {
    EngageService.planChange(new EngagementNumber(engagementNumber), Plan.create(dstPlanCode))
  }
}
