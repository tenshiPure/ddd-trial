package domain.engagement

import domain.engagement.mobile.line.Line
import domain.engagement.mobile.share_line.ShareLines

case class DoubtEngagement_(engagementNumber: EngagementNumber, fullname: Fullname, plan: Plan, line: Line, shareLines: ShareLines) {
  def this() = this(null, null, null, null, null)

  def this(e: Engagement) = this(e.engagementNumber, e.fullname, e.plan, e.line, e.shareLines)

  def verify(): ValidEngagement_ =
    if (engagementNumber == null) throw new Exception("un supported")
    else ValidEngagement_(
      engagementNumber,
      fullname,
      plan,
      line,
      shareLines
    )
}

case class ValidEngagement_(engagementNumber: EngagementNumber, fullname: Fullname, plan: Plan, line: Line, shareLines: ShareLines) {
}
