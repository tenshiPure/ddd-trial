package service

import java.time.LocalDate

import domain.member._

object MemberService {
  def create(year: Int, zipCode: String) = {
    Member(
      MemberName(
        FamilyName("苗字"),
        GivenName("名前")
      ),
      BirthDate(LocalDate.of(year, 1, 1)),
      Male,
      Address(
        ZipCode(zipCode),
        Prefecture("神奈川県"),
        City("川崎"),
        Street("仲見世通り")
      ),
      TelephoneNumber("090-1234-5678"),
      new EngagementDateTime(),
      None
    )
  }
}
