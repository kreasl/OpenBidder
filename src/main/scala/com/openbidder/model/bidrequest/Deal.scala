package com.openbidder.model.bidrequest

case class Deal(id: String, bidfloor: Float = 0, bidfloorcur: String = "USD", at: Option[AuctionType],
                wseat: Option[Seq[String]], wadomain: Option[Seq[String]], ext: Option[Any])
