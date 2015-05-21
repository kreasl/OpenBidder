package com.openbidder.model.bidrequest

import play.api.libs.json.Json

case class App(id: Option[String], name: Option[String], bundle: Option[String], domain: Option[String],
               storeurl: Option[String], cat: Option[Seq[String]], sectioncat: Option[Seq[String]],
               pagecat: Option[Seq[String]], ver: Option[String], privacypolicy: Option[Int],
               paid: Option[Int], publisher: Option[Publisher], content: Option[Content],
               keywords: Option[String], ext: Option[Ext])

object App {
	implicit val appRead = Json.reads[App]
	implicit val appWrite = Json.writes[App]
}

