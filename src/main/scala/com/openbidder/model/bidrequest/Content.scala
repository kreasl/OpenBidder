package com.openbidder.model.bidrequest

case class Content(id: Option[String], episode: Option[Int], title: Option[String], series: Option[String],
                   season: Option[String], producer: Option[Producer], url: Option[String],
                   cat: Option[Seq[String]], videoquality: Option[Int],
                   context: Option[Int], contentrating: Option[String], userrating: Option[String],
                   qagmediarating: Option[Int], keywords: Option[String], livestream: Option[Int],
                   sourcerelationship: Option[Int], len: Option[Int], language: Option[String],
                   embeddable: Option[Int], ext: Option[Any])
