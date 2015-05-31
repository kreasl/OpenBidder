package com.openbidder.biddingserver

import java.io.InputStream
import java.nio.charset.StandardCharsets

import com.openbidder.model.bidrequest.BidRequest
import com.openbidder.model.bidresponse.BidResponse
import com.openbidder.service.biddingservice.{SimpleBiddingService, BiddingService}
import com.openbidder.service.json.JsonService
import org.openrtb.validator.{OpenRtbInputType, OpenRtbValidatorFactory}
import scala.io.Source
import scalaz._

class DemandSideServer() {
  private val requestValidator = OpenRtbValidatorFactory.getValidator(OpenRtbInputType.BID_REQUEST, ValidatorOpenRtbVersion)
  private val responseValidator = OpenRtbValidatorFactory.getValidator(OpenRtbInputType.BID_RESPONSE, ValidatorOpenRtbVersion)
  private val jsonService = new JsonService
  private val biddingService: BiddingService = new SimpleBiddingService

  import Scalaz._

  type Response = Option[Array[Byte]]

  def respond(sspName: String, contentType: String, is: InputStream): Validation[String, Response] = {
    if (isUnknownServer(sspName)) "Unknown server".failure
    else if (isUnsupportedContentType(contentType)) "Unsupported content type".failure
    else readRequest(contentType, is) map { bidRequest =>
        val bidResponse = biddingService.process(bidRequest)
        writeResponse(contentType, bidResponse)
    }
  }

  //todo: match contentType with exchange
  private def isUnsupportedContentType(contentType: String) = JsonContentType != contentType

  //TODO: implement
  private def isUnknownServer(sspName: String) = false

  private def readRequest(contentType: String, is: InputStream):  Validation[String, BidRequest] = {
    val json = Source.fromInputStream(is).mkString
    if (requestValidator.isValid(json)) jsonService.parseBidRequest(json).success else "Bad request".failure
  }

  private def writeResponse(contentType: String, bidResponse: BidResponse): Response = {
    val json = jsonService.bidResponseToJson(bidResponse)
    if (responseValidator.isValid(json)) {
      val bytes = json.getBytes(StandardCharsets.UTF_8)
      Some(bytes)
    } else {
      //TODO: log invalid response
      None
    }
  }
}