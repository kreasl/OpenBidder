package com.openbidder.model.bidresponse

case class SeatBid(
  bid: Seq[Bid],
  seat: Option[String] = None,
  group: Int = 0,
  ext: Option[Any] = None
) {
  assert(bid.length > 0)
}

object SeatBid {
  def empty = SeatBid(bid = Seq(Bid.empty))
}