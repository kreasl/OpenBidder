package com.openbidder.model.bidrequest

import play.api.libs.json.Json

case class Content(id: Option[String], episode: Option[Int], title: Option[String], series: Option[String],
                   season: Option[String], producer: Option[Producer], url: Option[String],
                   cat: Option[Seq[String]], videoquality: Option[Int],
                   context: Option[Int], contentrating: Option[String], userrating: Option[String],
                   qagmediarating: Option[Int], keywords: Option[String], livestream: Option[Int],
                   sourcerelationship: Option[Int], len: Option[Int], language: Option[String],
                   embeddable: Option[Int], ext: Option[Ext])

object Content {
	implicit val contentRead = Json.reads[Content] 
	implicit val contentWrite = Json.writes[Content] 	
} 