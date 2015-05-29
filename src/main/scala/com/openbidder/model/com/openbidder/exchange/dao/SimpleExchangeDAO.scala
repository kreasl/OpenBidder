package com.openbidder.model.com.openbidder.exchange.dao

import com.openbidder.model.com.openbidder.exchange.{BidderConnection, PixelServiceConnection}

class SimpleExchangeDAO extends ExchangeDAO {
  override def bidders: Seq[BidderConnection] = ???

  override def pixelService(bidderId: Int): PixelServiceConnection = ???
}
