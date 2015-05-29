package com.openbidder.model.com.openbidder.exchange.services

import com.openbidder.http.{HttpRequest, HttpBidRequest, HttpMatchRequest, HttpResponse}
import com.openbidder.model.bidrequest.Imp
import com.openbidder.model.com.openbidder.exchange.Tags
import com.openbidder.model.com.openbidder.exchange.dao.SimpleExchangeDAO

class ExchangeService(exchangeDAO: SimpleExchangeDAO, auctionService: AuctionService, cookieMatchingService: CookieMatchingService) {
  def accept(httpRequest: HttpRequest): HttpResponse = {

    httpRequest match {
      case HttpBidRequest() =>
        val impression = extractImpression(httpRequest)

        val tags = auctionService.runAction(impression)

        HttpResponse(tags)

      case HttpMatchRequest() =>
        cookieMatchingService.redirect(httpRequest)

      case _ => HttpResponse(Tags(""))
    }

  }

  private def extractImpression(httpRequest: HttpRequest): Imp = {
    Imp(
      "123",
      None,
      None,
      None,
      None,
      None,
      0,
      None,
      0,
      "USD",
      None,
      None,
      None,
      None
    )
  }

}

