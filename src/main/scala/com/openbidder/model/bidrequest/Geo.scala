package com.openbidder.model.bidrequest

import play.api.libs.json.Json

case class Geo(lat: Option[Float], lon: Option[Float], `type`: Option[Int], country: Option[String],
               region: Option[String], regionfips: Option[String], metro: Option[String], city: Option[String],
               zip: Option[String], utcoffset: Option[Int], ext: Option[Ext])

object Geo {
	implicit val geoRead = Json.reads[Geo] 
	implicit val geoWrite = Json.writes[Geo] 	
} 