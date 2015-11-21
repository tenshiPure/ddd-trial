package domain.engagement.mobile.share_line.share_sim_card

import domain.engagement.mobile.mnp_in.MnpIn

case class ShareSimCard(shareSimCardNumber: ShareSimCardNumber, mnpIn: Option[MnpIn]) {

}

