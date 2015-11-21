package service

import domain.engagement.mobile.mnp_in.{MnpIn, Msisdn}
import domain.engagement.{Fullname, _Plan}

object Adapter {
  def engage(fullname: String, planCode: String, msisdn: String) = {
    EngageService.engage(
      Fullname(fullname),
      _Plan.create(planCode),
      if (msisdn == "") None else Some(MnpIn(Msisdn(msisdn)))
    )
  }
}
