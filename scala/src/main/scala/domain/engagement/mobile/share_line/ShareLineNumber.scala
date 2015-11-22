package domain.engagement.mobile.share_line

class ShareLineNumber(val value: String) {
  def this(value: Int) = this("sl" + value.toString)

  override def toString: String = "ShareLineNumber(" + value + ")"
}

