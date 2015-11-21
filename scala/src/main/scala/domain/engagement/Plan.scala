package domain.engagement

sealed abstract class Plan

case object NormalPlan extends Plan

case object SpecialPlan extends Plan

