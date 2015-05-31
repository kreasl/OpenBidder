package utils

import com.nicta.rng.Rng
import com.nicta.rng.Rng._
import com.openbidder.model.bidrequest.device.Device
import com.openbidder.model.common.Win
import com.openbidder.model.bidrequest._
import org.specs2.Specification
import org.specs2.matcher.{AlwaysMatcher, Matcher}

import scala.util.Try
import scalaz.NonEmptyList

class GeneratorsSpec extends Specification { def is = s2"""

  This is a specification for Generators.

  We can generate:
    random Win value with custom random id & price   $e1
    random object generation
    for all com.openbidder.model.bidrequest._ case classes $e2
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

  def e2 =
    (Generators.randomApp.run.unsafePerformIO() should beAnInstanceOf[App]) and
      (Generators.randomAuctionType.run.unsafePerformIO() should beAnInstanceOf[AuctionType]) and
      (Generators.randomBanner.run.unsafePerformIO() should beAnInstanceOf[Banner]) and
      (Generators.randomBidRequest.run.unsafePerformIO() should beAnInstanceOf[BidRequest]) and
      (Generators.randomContent.run.unsafePerformIO() should beAnInstanceOf[Content]) and
      (Generators.randomData.run.unsafePerformIO() should beAnInstanceOf[Data]) and
      (Generators.randomDeal.run.unsafePerformIO() should beAnInstanceOf[Deal]) and
      (Generators.randomDevice.run.unsafePerformIO() should beAnInstanceOf[Device]) and
      (Generators.randomGeo.run.unsafePerformIO() should beAnInstanceOf[Geo]) and
      (Generators.randomImp.run.unsafePerformIO() should beAnInstanceOf[Imp]) and
      (Generators.randomNative.run.unsafePerformIO() should beAnInstanceOf[Native]) and
      (Generators.randomPmp.run.unsafePerformIO() should beAnInstanceOf[Pmp]) and
      (Generators.randomProducer.run.unsafePerformIO() should beAnInstanceOf[Producer]) and
      (Generators.randomPublisher.run.unsafePerformIO() should beAnInstanceOf[Publisher]) and
      (Generators.randomRegs.run.unsafePerformIO() should beAnInstanceOf[Regs]) and
      (Generators.randomSegment.run.unsafePerformIO() should beAnInstanceOf[Segment]) and
      (Generators.randomSite.run.unsafePerformIO() should beAnInstanceOf[Site]) and
      (Generators.randomUser.run.unsafePerformIO() should beAnInstanceOf[User]) and
      (Generators.randomVideo.run.unsafePerformIO() should beAnInstanceOf[Video])
}
