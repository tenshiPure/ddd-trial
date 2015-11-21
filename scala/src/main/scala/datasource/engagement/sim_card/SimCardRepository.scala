package datasource.engagement.sim_card

import datasource._Database
import domain.engagement.sim_card.SimCardNumber

object SimCardRepository {
  def allocateSimCardNumber: SimCardNumber = new SimCardNumber(Mapper.allocate)

  object Mapper {

    def name = "SimCards"

    def allocate: Int = _Database.allocate(Mapper.name)
  }

}
