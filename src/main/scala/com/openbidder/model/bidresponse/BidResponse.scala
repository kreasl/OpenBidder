package com.openbidder.model.bidresponse

case class BidResponse(id: String, seatbid: Seq[SeatBid], bidid: Option[String], cur: String = "USD",
                       customdata: Option[String], nbr: Option[Int], ext: Option[Any])