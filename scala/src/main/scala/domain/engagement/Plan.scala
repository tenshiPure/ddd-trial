package domain.engagement

sealed abstract class Plan

case object NormalPlan extends Plan

case object SpecialPlan extends Plan

case object UnitTestPlan extends Plan

// use only plan-change unit test

object Plan {
  def create(code: String): Plan = code match {
    case "NormalPlan" => NormalPlan
    case "SpecialPlan" => SpecialPlan
    case "UnitTestPlan" => UnitTestPlan
    case _ => throw new Exception(code + " is invalid plan code")
  }
}
