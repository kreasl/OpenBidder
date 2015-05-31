package com.openbidder.model.bidrequest

import com.openbidder.model.bidrequest.device.Device
import play.api.libs.json._
import play.api.libs.functional._

import scalaz.NonEmptyList

case class BidRequest(id: String, imp: NonEmptyList[Imp], site: Option[Site], app: App,
                      device: Device, user: User, test: Int = 0, at: Int = 2,
                      tmax: Option[Int], wseat: Option[Seq[String]], allimps: Int = 0,
                      cur: Option[Seq[String]], bcat: Option[Seq[String]], badv: Option[Seq[String]],
                      regs: Option[Regs], ext: Option[Ext])

object BidRequest {
  implicit val bidRequestRead: Reads[BidRequest] = new Reads[BidRequest] {
    override def reads(json: JsValue): JsResult[BidRequest] = json match {
      case jsObj: JsObject => {
        JsSuccess(BidRequest(
          (jsObj \ "id").as[String],
          (jsObj \ "imp").asOpt[Seq[Imp]] match {
            case Some(seqImp: Seq[Imp]) => seqToNonEmptyList(seqImp)
            case None => throw new Exception("Invali json. Can't parse Imp")
          },
          (jsObj \ "site").asOpt[Site],
          (jsObj \ "app").as[App],
          (jsObj \ "device").as[Device],
          (jsObj \ "user").as[User],
          (jsObj \ "test").asOpt[Int] match {
            case Some(test: Int) => test
            case None => 0
          },
          (jsObj \ "at").asOpt[Int] match {
            case Some(at: Int) => at
            case None => 0
          },
          (jsObj \ "tmax").asOpt[Int],
          (jsObj \ "wseat").asOpt[Seq[String]],
          (jsObj \ "allimps").asOpt[Int] match {
            case Some(allimps: Int) => allimps
            case None => 0
          },
          (jsObj \ "cur").asOpt[Seq[String]],
          (jsObj \ "bcat").asOpt[Seq[String]],
          (jsObj \ "badv").asOpt[Seq[String]],
          (jsObj \ "regs").asOpt[Regs],
          (jsObj \ "ext").asOpt[Ext]
        ))
      }
      case _ => JsError("Invalid BidRequest json")
    }
  }

	implicit val bidRequestWrite: Writes[BidRequest] = new Writes[BidRequest] {
    override def writes(o: BidRequest): JsValue = Json.obj(
      "id" -> o.id,
      "at" -> o.at,
      "tmax" -> o.tmax,
      "imp" -> o.imp.list,
      "app" -> o.app,
      "device" -> o.device,
      "user" -> o.user,
      "site" -> o.site,
      "test" -> o.test,
      "wseat" -> o.wseat,
      "allimps" -> o.allimps,
      "cur" -> o.cur,
      "bcat" -> o.bcat,
      "badv" -> o.badv,
      "regs" -> o.regs,
      "ext" -> o.ext
    )
  }

  def seqToNonEmptyList(seqImp: Seq[Imp]) : NonEmptyList[Imp] = {
    NonEmptyList.nel(seqImp.head, seqImp.tail.toList)
  }
}

