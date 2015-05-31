package com.openbidder.model.bidrequest

import play.api.libs.json.Json

case class Publisher(id: Option[String], name: Option[String], cat: Option[String], domain: Option[String],
                     ext: Option[Ext])

object Publisher {
	implicit val publisherRead = Json.reads[Publisher] 
	implicit val publisherWrite = Json.writes[Publisher] 	
} 