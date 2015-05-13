package com.openbidder.service.biddingservice

import com.openbidder.model.bidrequest.BidRequest
import com.openbidder.model.bidresponse.{Bid, SeatBid, BidResponse}

import scalaz.NonEmptyList

/**
 * Created by Yury Talapila on 5.5.15.
 */
class SimpleBiddingService extends BiddingService {
  def process(request: BidRequest): BidResponse = {
    createResponce(request)
  }

  def createResponce(request: BidRequest): BidResponse =
    BidResponse(
      request.id,
      createSeats(request),
      Some(nextRandomAnumId),
      chooseCurrency(request.cur),
      None,
      None,
      None
    )

  def createSeats(request: BidRequest): Seq[SeatBid] = {
    Seq(
        SeatBid(
        createBids(request),
        Some(nextRandomAnumId),
        0,
        None
      )
    )
  }

  def createBids(request: BidRequest): NonEmptyList[Bid] =
    NonEmptyList(
      Bid(
        nextRandomAnumId,
        request.imp.head.id,
        request.imp.head.bidfloor + minRise,
        None,
        None,
        Some("<div><a href=\"https://github.com/adform/OpenBidder\">Open refence implementation of RTB bidder in Scala in accordance with OpenRTB standard</a></div>"),
        None,
        None,
        None,
        None,
        None,
        None,
        None,
        None,
        None,
        None,
        None
      )
    )


  def chooseCurrency(proposedCurrencies: Option[Seq[String]]): String = proposedCurrencies match {
    case Some(proposed) => {
      if (proposed.contains(defaultCurrency)) defaultCurrency
      else {
        val available = proposed filter (availableCurrencies contains _)
        if (available.length > 0) available(0)
        else defaultCurrency
      }
    }
    case None => defaultCurrency
  }

}
