package com.openbidder.model.bidresponse

import play.api.libs.json.Json

case class Ext(sessiondepth: Option[Int])

object Ext {
  implicit val extRead = Json.reads[Ext] 
  implicit val extWrite = Json.writes[Ext]   
} 