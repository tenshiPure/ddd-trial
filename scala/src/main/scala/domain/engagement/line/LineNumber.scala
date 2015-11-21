package domain.engagement.line

class LineNumber(val value: String) {
  def this(value: Int) = this("ln" + value.toString)

  override def toString: String = "LineNumber(" + value + ")"
}

