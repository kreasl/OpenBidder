package com.openbidder.model.bidrequest

case class Device(ua: Option[String], geo: Option[Geo], dnt: Option[Int], lmt: Option[Int],
                  ip: Option[String], ipv6: Option[String], devicetype: Option[Int], make: Option[String],
                  model: Option[String], os: Option[String], osv: Option[String], hvm: Option[String], h: Option[Int],
                  w: Option[Int], ppi: Option[Int], pxratio: Option[Float], js: Option[Int],
                  flashver: Option[String], language: Option[String], carrier: Option[String],
                  connectiontype: Option[Int], ifa: Option[String], didsha1: Option[String],
                  didmd5: Option[String], dpidsha1: Option[String], dpidmd5: Option[String], macsha1: Option[String],
                  macmd5: Option[String], ext: Option[Any])
