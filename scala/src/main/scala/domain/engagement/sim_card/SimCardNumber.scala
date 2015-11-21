package domain.engagement.sim_card

class SimCardNumber(val value: String) {
  def this(value: Int) = this("sc" + value.toString)

  override def toString: String = "SimCardNumber(" + value + ")"
}

