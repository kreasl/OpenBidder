package com.openbidder.model.bidrequest

import play.api.libs.json.Json

case class Native(request: String, ver: Option[String], api: Option[Seq[Int]], battr: Option[Seq[Int]],
                  ext: Option[Ext])

object Native {
	implicit val nativeRead = Json.reads[Native] 
	implicit val nativeWrite = Json.writes[Native] 	
} 