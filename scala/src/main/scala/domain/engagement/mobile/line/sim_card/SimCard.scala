package domain.engagement.mobile.line.sim_card

import domain.engagement.mobile.mnp_in.MnpIn

case class SimCard(simCardNumber: SimCardNumber, mnpIn: Option[MnpIn]) {

}

