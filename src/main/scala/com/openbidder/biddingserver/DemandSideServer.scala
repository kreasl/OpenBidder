package com.openbidder.biddingserver

import java.io.InputStream
import java.nio.charset.StandardCharsets

import com.openbidder.model.bidrequest.BidRequest
import com.openbidder.model.bidresponse.BidResponse
import com.openbidder.service.biddingservice.{SimpleBiddingService, BiddingService}
import com.openbidder.service.json.JsonService
import org.openrtb.validator.{OpenRtbInputType, OpenRtbValidatorFactory}
import scala.io.Source

class DemandSideServer() {
  private val requestValidator = OpenRtbValidatorFactory.getValidator(OpenRtbInputType.BID_REQUEST, ValidatorOpenRtbVersion)
  private val responseValidator = OpenRtbValidatorFactory.getValidator(OpenRtbInputType.BID_RESPONSE, ValidatorOpenRtbVersion)
  private val jsonService = new JsonService
  private val biddingService: BiddingService = new SimpleBiddingService

  def respond(sspName: String, contentType: String, is: InputStream): DSSResponse = {
    if (isUnknownServer(sspName)) UnknownServer
    else if (isUnsupportedContentType(contentType)) UnsupportedContentType
    else readRequest(contentType, is) match {
      case Some(bidRequest) =>
        val bidResponse = biddingService.process(bidRequest)
        writeResponse(contentType, bidResponse) map Bid getOrElse NoBid
      case None => BadRequest
    }
  }

  //todo: match contentType with exchange
  private def isUnsupportedContentType(contentType: String) = JsonContentType != contentType

  //TODO: implement
  private def isUnknownServer(sspName: String) = false

  private def readRequest(contentType: String, is: InputStream): Option[BidRequest] = {
    if (JsonContentType == contentType) readRequestFromJson(is) else None
  }

  private def readRequestFromJson(is: InputStream): Option[BidRequest] = {
    val json = Source.fromInputStream(is).mkString
    if (requestValidator.isValid(json)) {
      val bidRequest = jsonService.parseBidRequest(json)
      Some(bidRequest)
    } else {
      None
    }
  }

  private def writeResponse(contentType: String, bidResponse: BidResponse): Option[Array[Byte]] = {
    if (JsonContentType == contentType) writeResponseAsJson(bidResponse) else None
  }

  private def writeResponseAsJson(bidResponse: BidResponse): Option[Array[Byte]] = {
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

sealed trait DSSResponse

case object UnknownServer extends DSSResponse

case object UnsupportedContentType extends DSSResponse

case object BadRequest extends DSSResponse

case class Bid(body: Array[Byte]) extends DSSResponse

case object NoBid extends DSSResponse