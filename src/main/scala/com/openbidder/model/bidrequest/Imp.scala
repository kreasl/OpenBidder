package com.openbidder.model.bidrequest

case class Imp(id: String, banner: Option[Banner], video: Option[Video], native: Option[Native],
               displaymanager: Option[String], displaymanagerver: Option[String], instl: Int = 0,
               tagid: Option[String], bidfloor: Float = 0, bidfloorcur: String = "USD",
               secure: Option[Int], iframebuster: Option[Seq[String]], pmp: Option[Pmp],
               ext: Option[Any])