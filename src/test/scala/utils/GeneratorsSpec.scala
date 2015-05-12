package utils

import com.nicta.rng.Rng
import com.nicta.rng.Rng._
import com.openbidder.model.common.Win
import org.specs2.Specification
import org.specs2.matcher.{AlwaysMatcher, Matcher}

import scala.util.Try
import scalaz.NonEmptyList

class GeneratorsSpec extends Specification { def is = s2"""

  This is a specification for Generators.

  We can generate:
    random Win value with custom random id & price   $e1
                                                        """

  val currs = NonEmptyList("USD", "EUR", "BLR")

  val randomUUIDString: Rng[String] = oneof(java.util.UUID.randomUUID().toString)

  def isCorrectUUID(uuid: String) =
    Try(java.util.UUID.fromString(uuid)).isSuccess

  def beUUID: Matcher[String] = { uuid: String =>
    (isCorrectUUID(uuid), s"$uuid is UUID", s"$uuid is not UUID")
  }

  def beSomeCurrency: Matcher[Option[String]] = { currency: Option[String] =>
    (isCorrectOption(currency, currs.list.contains(currency.get)), s"$currency is right", s"$currency is wrong")
  }

  def beSomeId(f: String => Boolean): Matcher[Option[String]] = { id: Option[String] =>
    (isCorrectOption(id, f(id.get)),
      s"id is Some($id)",
      s"id is None")
  }

  def isCorrectOption(value: Option[String], isCorrect: => Boolean): Boolean =
    value.isDefined && isCorrect || !value.isDefined

  def beAWinWith(auctionId: Matcher[String] = AlwaysMatcher(),
                 auctionBidId: Matcher[Option[String]] = AlwaysMatcher(),
                 auctionImpId: Matcher[String] = AlwaysMatcher(),
                 auctionSeatId: Matcher[String] = AlwaysMatcher(),
                 auctionAdId: Matcher[Option[String]] = AlwaysMatcher(),
                 auctionPrice: Matcher[Float] = AlwaysMatcher(),
                 auctionCurrency: Matcher[Option[String]] = AlwaysMatcher()) =
    auctionId ^^ { (w: Win) => w.auctionId} and
      auctionBidId ^^ { (w: Win) => w.auctionBidId} and
      auctionImpId ^^ { (w: Win) => w.auctionImpId} and
      auctionSeatId ^^ { (w: Win) => w.auctionSeatId} and
      auctionAdId ^^ { (w: Win) => w.auctionAdId} and
      auctionPrice ^^ { (w: Win) => w.auctionPrice} and
      auctionCurrency ^^ { (w: Win) => w.auctionCurrency}

  def beWin: Matcher[Rng[Win]] = { generator: Rng[Win] =>
    val wins = generator.fill(100).run.unsafePerformIO()
    wins must forall(beAWinWith(auctionId = beUUID, auctionBidId = beSomeId(isCorrectUUID), auctionImpId = beUUID,
      auctionSeatId = beUUID, auctionAdId = beSomeId(isCorrectUUID),
      auctionPrice = between(1f, 5f), auctionCurrency = beSomeCurrency))
  }

  def e1 = Generators.randomWin(idg = randomUUIDString, pg = Rng.oneof(1, 2, 3, 4, 5), currs) must beWin

}
