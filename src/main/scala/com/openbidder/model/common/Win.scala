package com.openbidder.model.common

case class Win(auctionId: String, auctionBidId: Option[String],
               auctionImpId: String, auctionSeatId: String,
               auctionAdId: Option[String], auctionPrice: Float, auctionCurrency: Option[String] = Some("USD"))