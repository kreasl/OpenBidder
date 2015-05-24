package com.openbidder.model.bidrequest

import play.api.libs.json.Json

case class Data(id: Option[String], name: Option[String], segment: Option[Seq[Segment]], ext: Option[Ext])

object Data {
	implicit val dataRead = Json.reads[Data] 
	implicit val dataWrite = Json.writes[Data] 	
} 