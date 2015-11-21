package domain.engagement

class EngagementNumber(val value: String) {
  def this(value: Int) = this("en" + value.toString)

  override def toString: String = "EngagementNumber(" + value + ")"
}

