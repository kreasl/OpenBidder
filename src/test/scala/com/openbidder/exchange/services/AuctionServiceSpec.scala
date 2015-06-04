package com.openbidder.exchange.services

import com.openbidder.model.bidrequest.Imp
import com.openbidder.model.bidresponse.{Bid, BidResponse, SeatBid}
import com.openbidder.model.com.openbidder.exchange.BidderConnection
import com.openbidder.model.com.openbidder.exchange.services.AuctionService
import org.specs2.Specification
import org.specs2.mock.Mockito

class AuctionServiceSpec extends Specification with Mockito {
  def is = s2"""
    This is a specification for AuctionService

    AuctionService should
      finding winner bidder from 3 requests using second price $e1
      |finding winner bidder from 1 request $e2
                                                          """

  val service = new AuctionService(List(new BidderConnection()))

  val imp = mock[Imp]
  imp.id returns "impid"

  val bid1 = mock[Bid]
  bid1.id returns "bidId1"
  bid1.impid returns "imp_id"
  bid1.price returns 10.22.toFloat

  val bid2 = mock[Bid]
  bid2.id returns "bidId2"
  bid2.impid returns "imp_id2"
  bid2.price returns 1.1.toFloat

  val bid3 = mock[Bid]
  bid3.id returns "bidId3"
  bid3.impid returns "imp_id3"
  bid3.price returns 25.1.toFloat

  val seatBid = mock[SeatBid]
  seatBid.bid returns List(bid1)
  seatBid.group returns 0

  val seatBid2 = mock[SeatBid]
  seatBid2.bid returns List(bid2)
  seatBid2.group returns 0

  val seatBid3 = mock[SeatBid]
  seatBid3.bid returns List(bid3)
  seatBid3.group returns 0

  val response1 = mock[BidResponse]
  response1.id returns "responseId"
  response1.seatbid returns List(seatBid)
  response1.cur returns "USD"

  val response2 = mock[BidResponse]
  response2.id returns "responseId2"
  response2.seatbid returns List(seatBid2)
  response2.cur returns "USD"

  val response3 = mock[BidResponse]
  response3.id returns "responseId3"
  response3.seatbid returns List(seatBid3)
  response3.cur returns "USD"

  def e1 = service.winner(List(response1, response2, response3)).price must be_==(10.22.toFloat)

  def e2 = service.winner(List(response2)).price must be_==(1.1.toFloat)

}
