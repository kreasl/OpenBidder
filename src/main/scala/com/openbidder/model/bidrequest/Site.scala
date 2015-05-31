package com.openbidder.model.bidrequest

import play.api.libs.json.Json

case class Site(id: Option[String], name: Option[String], domain: Option[String], cat: Option[Seq[String]],
                sectioncat: Option[Seq[String]], pagecat: Option[Seq[String]], page: Option[String],
                ref: Option[String], search: Option[String], mobile: Option[Int],
                privacypolicy: Option[Int], publisher: Option[Publisher], content: Option[Content],
                keywords: Option[String], ext: Option[Ext])

object Site {
	implicit val siteRead = Json.reads[Site] 
	implicit val siteWrite = Json.writes[Site] 	
} 