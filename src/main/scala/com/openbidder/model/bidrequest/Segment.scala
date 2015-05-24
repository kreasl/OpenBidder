package com.openbidder.model.bidrequest

import play.api.libs.json.Json

case class Segment(id: Option[String], name: Option[String], value: Option[String], ext: Option[Ext])

object Segment {
	implicit val segmentRead = Json.reads[Segment] 
	implicit val segmentWrite = Json.writes[Segment] 	
} 