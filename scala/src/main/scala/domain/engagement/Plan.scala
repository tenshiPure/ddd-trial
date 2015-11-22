package domain.engagement

sealed abstract class Plan

case object NormalPlan extends Plan

case object SpecialPlan extends Plan

object Plan {
  def create(code: String): Plan = if (code == "NormalPlan") NormalPlan else SpecialPlan
}
