package com.openbidder.model.bidrequest

import play.api.libs.json._

case class Imp(id: String, banner: Option[Banner], video: Option[Video], native: Option[Native],
               displaymanager: Option[String], displaymanagerver: Option[String], instl: Int = 0,
               tagid: Option[String], bidfloor: Float = 0, bidfloorcur: String = "USD",
               secure: Option[Int], iframebuster: Option[Seq[String]], pmp: Option[Pmp],
               ext: Option[Ext])

object Imp {
	implicit val impRead: Reads[Imp] = new Reads[Imp] {
    override def reads(json: JsValue): JsResult[Imp] = json match {
      case jsObj: JsObject => {
        JsSuccess(Imp(
          (jsObj \ "id").as[String],
          (jsObj \ "banner").asOpt[Banner],
          (jsObj \ "video").asOpt[Video],
          (jsObj \ "native").asOpt[Native],
          (jsObj \ "displaymanager").asOpt[String],
          (jsObj \ "displaymanagerver").asOpt[String],
          (jsObj \ "instl").asOpt[Int] match {
            case Some(x: Int) => x
            case None => 0
          },
          (jsObj \ "tagid").asOpt[String],
          (jsObj \ "bidfloor").asOpt[Float] match {
            case Some(x: Float) => x
            case None => 0
          },
          (jsObj \ "bidfloorcur").asOpt[String] match {
            case Some(s: String) => s
            case None => "USD"
          },
          (jsObj \ "secure").asOpt[Int],
          (jsObj \ "iframebuster").asOpt[Seq[String]],
          (jsObj \ "pmp").asOpt[Pmp],
          (jsObj \ "ext").asOpt[Ext]
        ))
      }
      case _ => JsError("Invalid Imp json")
    }
  }
	implicit val impWrite = Json.writes[Imp]
}