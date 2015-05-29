package com.openbidder.model.com.openbidder.exchange

import com.openbidder.model.bidrequest.BidRequest
import com.openbidder.model.bidresponse.BidResponse

case class BidderConnection() {
  def sendRequest(bidRequest: BidRequest): BidResponse = ???
}
