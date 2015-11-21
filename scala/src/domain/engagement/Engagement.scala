package domain.engagement

import domain.engagement.line.Line
import domain.engagement.share_line.ShareLines

case class Engagement(
                       engagementNumber: EngagementNumber,
                       fullname: Fullname,
                       line: Line,
                       shareLines: ShareLines
                       ) {

}

