package com.openbidder.service.biddingservice

import com.openbidder.model.bidrequest.BidRequest
import com.openbidder.model.bidresponse.{Bid, SeatBid, BidResponse}
import com.openbidder.service.RandomAnumId

/**
 * Created by Yury Talapila on 5.5.15.
 */
class SimpleBiddingService extends BiddingService {
  def process(request: BidRequest): BidResponse = {
    createResponce(request)
  }

  def createResponce(request: BidRequest): BidResponse =
    BidResponse(
      id = request.id,
      seatbid = createSeats(request),
      bidid = Some(RandomAnumId.nextRandomAnumId),
      cur = chooseCurrency(request.cur)
    )

  def createSeats(request: BidRequest): Seq[SeatBid] = {
    Seq(
      SeatBid(
        bid = createBids(request)
      )
    )
  }

  def createBids(request: BidRequest): Seq[Bid] =
    Seq(
      Bid(
        id = RandomAnumId.nextRandomAnumId,
        impid = request.imp.head.id,
        price = request.imp.head.bidfloor + minRise,
        adm = Some(
          "<div>" +
            "<a href=\"https://github.com/adform/OpenBidder\">" +
            "Open refence implementation of RTB bidder in Scala in accordance with OpenRTB standard" +
            "</a>" +
            "</div>"
        )
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
