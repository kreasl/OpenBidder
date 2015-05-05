package com.openbidder.model.bidrequest

case class Geo(lat: Option[Float], lon: Option[Float], `type`: Option[Int], country: Option[String],
               region: Option[String], regionfips: Option[String], metro: Option[String], city: Option[String],
               zip: Option[String], utcoffset: Option[Integer], ext: Option[Any])
