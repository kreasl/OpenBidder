package com.openbidder.model.bidrequest

import play.api.libs.json.Json

case class Pmp(private_auction: Option[Int], deals: Option[Seq[Deal]], ext: Option[Ext])

object Pmp {
	implicit val pmpRead = Json.reads[Pmp] 
	implicit val pmpWrite = Json.writes[Pmp] 	
} 