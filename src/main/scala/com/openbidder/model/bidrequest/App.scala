package com.openbidder.model.bidrequest

case class App(id: Option[String], name: Option[String], bundle: Option[String], domain: Option[String],
               storeurl: Option[String], cat: Option[Seq[String]], sectioncat: Option[Seq[String]],
               pagecat: Option[Seq[String]], ver: Option[String], privacypolicy: Option[Int],
               paid: Option[Int], publisher: Option[Publisher], content: Option[Content],
               keywords: Option[String], ext: Option[Any])

