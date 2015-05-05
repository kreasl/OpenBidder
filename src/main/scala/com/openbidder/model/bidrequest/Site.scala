package com.openbidder.model.bidrequest

case class Site(id: Option[String], name: Option[String], domain: Option[String], cat: Option[Seq[String]],
                sectioncat: Option[Seq[String]], pagecat: Option[Seq[String]], page: Option[String],
                ref: Option[String], search: Option[String], mobile: Option[Int],
                privacypolicy: Option[Int], publisher: Option[Publisher], content: Option[Content],
                keywords: Option[String], ext: Option[Any])
