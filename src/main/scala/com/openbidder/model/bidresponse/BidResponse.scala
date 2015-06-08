package com.openbidder.model.bidresponse

import play.api.libs.json.Json
import com.openbidder.service.RandomAnumId

case class BidResponse(id: String, seatbid: Seq[SeatBid], bidid: Option[String] = None, cur: String = "USD",
                       customdata: Option[String] = None, nbr: Option[Int] = None, ext: Option[Ext] = None)


object BidResponse {
  def empty = BidResponse(id = RandomAnumId.nextRandomAnumId, seatbid = Nil)
	implicit val bidResponseRead = Json.reads[BidResponse]
  implicit val bidResponseWrite = Json.writes[BidResponse]
}