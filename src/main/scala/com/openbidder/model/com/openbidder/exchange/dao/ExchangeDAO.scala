package com.openbidder.model.com.openbidder.exchange.dao

import com.openbidder.model.com.openbidder.exchange.{BidderConnection, PixelServiceConnection}

trait ExchangeDAO {
  def bidders: Seq[BidderConnection]
  def pixelService(bidderId: Int): PixelServiceConnection
}
