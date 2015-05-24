package com.openbidder.model.bidrequest

import play.api.libs.json.Json

case class AuctionType(id: Option[Int])

object AuctionType {
  implicit val auctionTypeRead = Json.reads[AuctionType] 
  implicit val auctionTypeWrite = Json.writes[AuctionType]   
} 