package com.openbidder.service.json

import com.openbidder.model.bidrequest.BidRequest
import play.api.libs.json.Json

/**
 * Created by anton on 19.05.2015.
 */
class JsonService {
  def parse(request: String): BidRequest = {
    val jsonObj = Json.parse(request)
    val result = jsonObj.asOpt[BidRequest]

    result match {
      case Some(x) => x
      case _ => throw new Exception("BidRequest parse error")
    }
  }

  def toJson(request: BidRequest): String = {
    val jsRequest = Json.toJson(request)
    Json.stringify(jsRequest)
  }
}
