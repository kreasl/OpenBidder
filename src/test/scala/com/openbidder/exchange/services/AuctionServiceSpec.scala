package com.openbidder.exchange.services

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

  val seatBid = mockSeatBit(mockBid(10.22.toFloat))

  val seatBid2 = mockSeatBit(mockBid(1.1.toFloat))

  val seatBid3 = mockSeatBit(mockBid(25.1.toFloat))

  val response1 = mockResponse(List(seatBid))

  val response2 = mockResponse(List(seatBid2))

  val response3 = mockResponse(List(seatBid3))

  def e1 = service.winner(List(response1, response2, response3)).price must be_==(10.22.toFloat)

  def e2 = service.winner(List(response2)).price must be_==(1.1.toFloat)


  def mockResponse(seatbid: Seq[SeatBid]): BidResponse = {
    val response = mock[BidResponse]
    response.id returns "respId"
    response.seatbid returns seatbid
    response.cur returns "USD"
  }

  def mockSeatBit(bid: Bid): SeatBid = {
    val seatBid = mock[SeatBid]
    seatBid.bid returns List(bid)
    seatBid.group returns 0
  }

  def mockBid(price: Float): Bid = {
    val bid = mock[Bid]
    bid.id returns "id1"
    bid.impid returns "impid1"
    bid.price returns price
  }
}
