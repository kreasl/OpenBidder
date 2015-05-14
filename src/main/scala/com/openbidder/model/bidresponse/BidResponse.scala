package com.openbidder.model.bidresponse

import com.openbidder.service.RandomAnumId

case class BidResponse(
  id: String,
  seatbid: Seq[SeatBid],
  bidid: Option[String] = None,
  cur: String = "USD",
  customdata: Option[String] = None,
  nbr: Option[Int] = None,
  ext: Option[Any] = None
)

object BidResponse extends RandomAnumId {
  def empty = BidResponse(id = nextRandomAnumId, seatbid = Nil)
}