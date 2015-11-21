import domain.engagement.line.mnp_in.Msisdn

object Main {
  def main(args: Array[String]): Unit = {
    val msisdn1 = new Msisdn("090-1234-5678")
    println(msisdn1)
    println(msisdn1.value)
    // msisdn1.value = "invalid" // var定義なら出来る

    val msisdn2 = new Msisdn("090-1234-5678")
    val msisdn3 = new Msisdn("invalid")

    println(msisdn1 == msisdn2)
    println(msisdn1 == msisdn3)
  }
}
