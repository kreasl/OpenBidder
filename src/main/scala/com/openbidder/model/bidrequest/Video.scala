package com.openbidder.model.bidrequest

import play.api.libs.json.Json

case class Video(mimes: Seq[String], minduration: Option[Int], maxduration: Option[Int],
                 protocols: Option[Seq[Int]], w: Option[Int], h: Option[Int],
                 startdelay: Option[Int], linearity: Option[Int], sequence: Option[Int],
                 battr: Option[Seq[Int]], maxextended: Option[Int], minbitrate: Option[Int],
                 maxbitrate: Option[Int], boxingallowed: Int = 1,
                 playbackmethod: Option[Seq[Int]], delivery: Option[Seq[Int]],
                 pos: Option[Int], companionad: Option[Seq[Banner]], api: Option[Seq[Int]],
                 companiontype: Option[Seq[Int]], ext: Option[Ext])

object Video {
	implicit val videoRead = Json.reads[Video] 
	implicit val videoWrite = Json.writes[Video] 	
} 