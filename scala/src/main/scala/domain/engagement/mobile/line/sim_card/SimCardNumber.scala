package domain.engagement.mobile.line.sim_card

class SimCardNumber(val value: String) {
  def this(value: Int) = this("cn" + value.toString)

  override def toString: String = "SimCardNumber(" + value + ")"
}

