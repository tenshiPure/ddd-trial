import datasource._Database
import datasource.engagement.EngagementRepository
import domain.engagement._
import domain.engagement.line.mnp_in.{MnpIn, Msisdn}
import domain.engagement.line.{Line, LineNumber}
import domain.engagement.share_line.{ShareLine, ShareLines}
import domain.engagement.sim_card.{SimCard, SimCardNumber}

object Main {
  def main(args: Array[String]): Unit = {
    _Database.init()

    val engagement = create(hasMnpIn = true, hasShareLines = true, hasShareMnpIn = true)
    EngagementRepository.engage(engagement)
  }

  private def create(hasMnpIn: Boolean, hasShareLines: Boolean, hasShareMnpIn: Boolean): Engagement = {
    val mnpIn = if (hasMnpIn) Some(
      MnpIn(
        Msisdn("090-1234-5678")
      )
    )
    else None

    val shareMnpIn = if (hasShareMnpIn) Some(
      MnpIn(
        Msisdn("090-1234-5678")
      )
    )
    else None

    val shareLines = if (hasShareLines) List(
      ShareLine(
        SimCard(
          SimCardNumber("1"),
          shareMnpIn
        )
      )
    )
    else List()

    Engagement(
      EngagementNumber("1"),
      Fullname("john doe"),
      NormalPlan,
      Line(
        LineNumber("1"),
        SimCard(
          SimCardNumber("1"),
          mnpIn
        )
      ),
      ShareLines(
        shareLines
      )
    )
  }
}
