package com.openbidder.model.bidrequest

import play.api.libs.json.Json

case class Regs(coppa: Option[Int], ext: Option[Ext])

object Regs {
	implicit val regsRead = Json.reads[Regs] 
	implicit val regsWrite = Json.writes[Regs] 	
} 