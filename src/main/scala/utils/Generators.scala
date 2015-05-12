package utils

import com.nicta.rng.Rng
import com.nicta.rng.Rng._
import com.openbidder.model.common.Win

object Generators {

  /**
   * The random generator for Win is constructed from generators for its parts.
   * @param idg an id random generator, by default is used identifierstring(32)
   * @param pg a price random generator, by default is used positivefloat
   * @param currencies a list of currencies
   * @return a random generated Win
   */
  def randomWin(idg: Rng[String] = identifierstring(32),
           pg: Rng[Float] = positivefloat,
           currencies: scalaz.NonEmptyList[String]): Rng[Win] =
    for {
      aucId <- idg
      aucBidId <- idg.option
      aucImpId <- idg
      aucSeatId <- idg
      aucAdId <- idg.option
      aucPrice <- pg
      aucCur <- oneofL(currencies).option
    } yield Win(auctionId = aucId, auctionBidId = aucBidId, auctionImpId = aucImpId, auctionSeatId = aucSeatId,
      auctionAdId = aucAdId, auctionPrice = aucPrice, auctionCurrency = aucCur)

}
