package domain.engagement

import domain.engagement.mobile.line.Line
import domain.engagement.mobile.share_line.ShareLines

case class Engagement(engagementNumber: EngagementNumber, fullname: Fullname, plan: Plan, line: Line, shareLines: ShareLines) {
  def planChange(dstPlan: Plan): Engagement = Engagement(
    engagementNumber,
    fullname,
    dstPlan,
    line,
    shareLines
  )
}

