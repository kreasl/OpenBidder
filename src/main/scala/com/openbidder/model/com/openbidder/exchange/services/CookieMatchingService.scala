package com.openbidder.model.com.openbidder.exchange.services

import com.openbidder.http.{HttpRequest, HttpResponse}
import com.openbidder.model.com.openbidder.exchange.{Tags, PixelServiceConnection}

class CookieMatchingService (pixelServices: Map[Int, PixelServiceConnection]) {

  def redirect (request: HttpRequest): HttpResponse = {
    HttpResponse(Tags(""))
  }

}
