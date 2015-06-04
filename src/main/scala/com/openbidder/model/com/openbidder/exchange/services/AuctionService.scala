package com.openbidder.model.com.openbidder.exchange.services

import com.openbidder.model.bidrequest._
import com.openbidder.model.bidrequest.device.Device
import com.openbidder.model.bidresponse.{Bid, BidResponse}
import com.openbidder.model.com.openbidder.exchange.{Tags, BidderConnection}

import scalaz.NonEmptyList

class AuctionService(bidders: Seq[BidderConnection]) {

  def runAction(impression: Imp): Tags = {
    val responses = for (bidder <- bidders) yield bidder.sendRequest(makeRequest(impression))

    val bid = winner(responses)

    Tags(bid.adm.get)
  }

  def winner(responses: Seq[BidResponse]): Bid = {
    val res = responses.sortWith((left, right) => left.seatbid.head.bid.head.price > right.seatbid.head.bid.head.price)

    if (res.size >= 2) {
      return res(1).seatbid.head.bid.head
    }

    res.head.seatbid.head.bid.head

  }


  def makeRequest(imp: Imp): BidRequest = {
    BidRequest(
      "123",
      NonEmptyList(imp),
      None,
      makeApp(),
      makeDevice(),
      makeUser(),
      0,
      2,
      None,
      None,
      0,
      None,
      None,
      None,
      None,
      None
    )

  }

  def makeSite(): Site = {
    Site(
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None
    )
  }

  def makeApp(): App = {
    App(
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None
    )
  }

  def makeDevice(): Device = {
    Device(
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None
    )
  }

  def makeUser(): User = {
    User(
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None
    )
  }

  def makeBanner(): Banner = {
    Banner(
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None,
      None
    )
  }

}
