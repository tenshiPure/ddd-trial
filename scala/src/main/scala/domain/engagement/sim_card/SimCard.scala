package domain.engagement.sim_card

import domain.engagement.line.mnp_in.MnpIn

case class SimCard(simCardNumber: SimCardNumber, mnpIn: Option[MnpIn]) {

}

