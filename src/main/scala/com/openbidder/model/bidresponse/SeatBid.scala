package com.openbidder.model.bidresponse

import scalaz.NonEmptyList

case class SeatBid(bid: NonEmptyList[Bid], seat: Option[String], group: Int = 0, ext: Option[Any])