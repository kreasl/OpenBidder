package com.openbidder.log

import com.openbidder.log.LogLevel._
import com.openbidder.model.bidresponse.Bid
import com.openbidder.service.RandomAnumId

/** LogEntry for Bid objects
  *
  * @param id LogEntry unique ID
  * @param ts timestamp
  * @param level LogLevel
  * @param message String describes Bid object
  * @param objectId Bid object ID
  * @param objectType "Bid" string constant
  */
case class BidLogEntry(
  override val id: String = RandomAnumId.nextRandomAnumId,
  override val ts: String = RandomAnumId.nextRandomAnumId,
//  override val ts: String = System.currentTimeMillis().toString,
  override val level: LogLevel = LogLevel.Info,
  val message: String,
  override val objectId: Option[String] = None,
  override val objectType: Option[String] = Some("Bid")
) extends LogEntry {
  def this(bid: Bid) = this(message = BidLogEntry.composeMessage(bid))
}

/** Helper methods for BidLogEntry
  */
object BidLogEntry {
  def composeMessage(bid: Bid) = {
    val messageParts = Seq[Option[String]](
      Some("imp:" + bid.impid),
      Some("price: " + bid.price),
      bid.adid.map("ad: " + _),
      bid.nurl.map(_ => "nurl"),
      bid.adomain.map(_ => "adomain"),
      bid.bundle.map(_ => "bundle"),
      bid.iurl.map(_ => "iurl"),
      bid.cid.map(_ => "cid"),
      bid.crid.map(_ => "crid"),
      bid.cat.map(_ => "cat"),
      bid.attr.map(_ => "attr"),
      bid.dealid.map(_ => "dealid"),
      bid.w.map("size: " + _ + bid.h.map("x" + _)),
      bid.ext.map(_ => "ext")
    )

    "Bid sent: " + messageParts.flatten.reduce(_ + ", " + _)
  }
}
