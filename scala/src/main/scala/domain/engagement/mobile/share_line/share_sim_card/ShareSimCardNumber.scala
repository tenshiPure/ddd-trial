package domain.engagement.mobile.share_line.share_sim_card

class ShareSimCardNumber(val value: String) {
  def this(value: Int) = this("sc" + value.toString)

  override def toString: String = "ShareSimCardNumber(" + value + ")"
}

