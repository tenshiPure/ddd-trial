package service

import domain.engagement.mobile.mnp_in.{MnpIn, Msisdn}
import domain.engagement.{Engagement, EngagementNumber, Fullname, _Plan}

object Adapter {
  def engage(fullname: String, planCode: String, msisdn: String, shareMsisdns: List[String]) = {
    EngageService.engage(
      Fullname(fullname),
      _Plan.create(planCode),
      if (msisdn == "") None else Some(MnpIn(Msisdn(msisdn))),
      shareMsisdns.map(shareMsisdn => if (shareMsisdn == "") None else Some(MnpIn(Msisdn(shareMsisdn))))
    )
  }

  def find(engagementNumber: String): Option[Engagement] = {
    EngageService.find(new EngagementNumber(engagementNumber))
  }
}
