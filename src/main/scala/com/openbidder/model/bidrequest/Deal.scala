package com.openbidder.model.bidrequest

import play.api.libs.json.Json

case class Deal(id: String, bidfloor: Float = 0, bidfloorcur: String = "USD", at: Option[AuctionType],
                wseat: Option[Seq[String]], wadomain: Option[Seq[String]], ext: Option[Ext])

object Deal {
	implicit val dealRead = Json.reads[Deal] 
	implicit val dealWrite = Json.writes[Deal] 	
} 