package domain.engagement

sealed abstract class Plan

case object NormalPlan extends Plan

case object SpecialPlan extends Plan

object Plan {
  def create(code: String): Plan = code match {
    case "NormalPlan" => NormalPlan
    case "SpecialPlan" => SpecialPlan
    case _ => throw new Exception(code + " is invalid plan code")
  }
}
