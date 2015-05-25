package com.openbidder.service.json

import com.openbidder.model.bidrequest.BidRequest
import com.openbidder.model.bidresponse.BidResponse
import play.api.libs.json.Json

/**
 * Created by anton on 19.05.2015.
 */
class JsonService {
  def parseBidRequest(request: String): BidRequest = {
    val jsonObj = Json.parse(request)
    val result = jsonObj.asOpt[BidRequest]

    result match {
      case Some(x) => x
      case _ => throw new Exception("BidRequest parse error")
    }
  }

  def bidRequestToJson(request: BidRequest): String = {
    val jsRequest = Json.toJson(request)
    Json.stringify(jsRequest)
  }

  def bidResponseToJson(response: BidResponse): String = {
    val jsResponse = Json.toJson(response)
    Json.stringify(jsResponse)
  }
}
