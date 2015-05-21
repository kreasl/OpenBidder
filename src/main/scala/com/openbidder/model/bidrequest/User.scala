package com.openbidder.model.bidrequest

import play.api.libs.json.Json

case class User(id: Option[String], buyerid: Option[String], yop: Option[Int], gender: Option[String],
                keywords: Option[String], customdata: Option[String], geo: Option[Geo], data: Option[Seq[Data]],
                ext: Option[Ext])

object User {
	implicit val userRead = Json.reads[User] 
	implicit val userWrite = Json.writes[User] 	
} 