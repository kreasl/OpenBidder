package com.openbidder.service.biddingservice

import com.openbidder.model.bidrequest.BidRequest
import com.openbidder.model.bidresponse.BidResponse

/**
 * Created by Yury Talapila on 5.5.15.
 */
trait BiddingService {
  val defaultCurrency = "USD"
  val availableCurrencies = Set("USD", "EUR")

  val minRise: Float = 1

  def process(request: BidRequest): BidResponse
}
