package domain.member

import java.time.{LocalDate, LocalDateTime}


case class Member(
                   memberName: MemberName,
                   birthDate: BirthDate,
                   gender: Gender,
                   address: Address,
                   telephoneNumber: TelephoneNumber,
                   engagementDateTime: EngagementDateTime,
                   disengagementDateTime: Option[DisengagementDateTime]
                   ) {
  def isAdult: Boolean = birthDate.isAdult
}


case class MemberName(familyName: FamilyName, givenName: GivenName) {}

case class FamilyName(value: String) {}

case class GivenName(value: String) {}


case class BirthDate(value: LocalDate) {
  def isAdult: Boolean = LocalDate.now.getYear - value.getYear >= 20
}


sealed abstract class Gender

case object Male extends Gender

case object Female extends Gender


case class Address(zipCode: ZipCode, prefecture: Prefecture, city: City, street: Street) {}

case class ZipCode(value: String) {
  if (value.length != 8) throw new Exception(value + " is invalid ZipCode")
}

case class Prefecture(value: String) {}

case class City(value: String) {}

case class Street(value: String) {}


case class TelephoneNumber(value: String) {}

case class EngagementDateTime(value: LocalDateTime) {
  def this() = this(LocalDateTime.now())
}

case class DisengagementDateTime(value: LocalDateTime) {}