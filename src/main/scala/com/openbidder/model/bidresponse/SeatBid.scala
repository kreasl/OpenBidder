package com.openbidder.model.bidresponse

import play.api.libs.json.Json
import scalaz.NonEmptyList

case class SeatBid(
  bid: Seq[Bid],
  seat: Option[String] = None,
  group: Int = 0,
  ext: Option[Ext] = None
) {
  assert(bid.length > 0)
}

object SeatBid {
  def empty = SeatBid(bid = Seq(Bid.empty))
	implicit val seatBidRead = Json.reads[SeatBid]
	implicit val seatBidWrite = Json.writes[SeatBid] 	
} 