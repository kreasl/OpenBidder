package com.openbidder.model.bidresponse

import play.api.libs.json.Json
import com.openbidder.service.RandomAnumId

case class Bid(
  id: String,
  impid: String,
  price: Float,
  adid: Option[String] = None,
  nurl: Option[String] = None,
  adm: Option[String] = None,
  adomain: Option[Seq[String]] = None,
  bundle: Option[String] = None,
  iurl: Option[String] = None,
  cid: Option[String] = None,
  crid: Option[String] = None,
  cat: Option[Seq[String]] = None,
  attr: Option[Seq[Int]] = None,
  dealid: Option[String] = None,
  h: Option[Int] = None,
  w: Option[Int] = None,
  ext: Option[Ext] = None
)

object Bid {
  def empty = Bid(id = RandomAnumId.nextRandomAnumId, impid = "", price = 0)
	implicit val bidRead = Json.reads[Bid]
	implicit val bidWrite = Json.writes[Bid]
}