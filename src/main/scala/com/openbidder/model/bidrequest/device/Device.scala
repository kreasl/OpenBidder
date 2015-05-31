package com.openbidder.model.bidrequest.device

import com.openbidder.model.bidrequest.{Ext, Geo}
import play.api.libs.json._

case class Device(ua: Option[String], geo: Option[Geo], dnt: Option[Int], lmt: Option[Int],
                  ip: Option[IP], deviceInfo: Option[DeviceInfo], os: Option[OS],
                  hvm: Option[String], size: Option[Size],
                  ppi: Option[Int], pxratio: Option[Float], js: Option[Int],
                  flashver: Option[String], language: Option[String], carrier: Option[String],
                  connectiontype: Option[Int], ifa: Option[String], did: Option[DID],
                  dpid: Option[DPID],mac: Option[MAC], ext: Option[Ext])

object Device {
	implicit val deviceFormat: Format[Device] = new Format[Device] {
    override def writes(device: Device): JsValue = {
      Json.obj(
        "make" -> device.deviceInfo.get.make,
        "model" -> device.deviceInfo.get.model,
        "os" -> device.os.get.os,
        "osv" -> device.os.get.osv,
        "ua" -> device.ua.get,
        "ip" -> device.ip.get.ip,
        "language" -> device.language,
        "devicetype" -> device.deviceInfo.get.devicetype,
        "js" -> device.js,
        "connectiontype" -> device.connectiontype,
        "dpidsha1" -> device.dpid.get.dpidsha1,
        "carrier" -> device.carrier,
        "geo" -> device.geo
      )
    }

    override def reads(json: JsValue): JsResult[Device] = json match {
      case j: JsObject => {
        JsSuccess(Device(
          (j \ "ua").asOpt[String],
          (j \ "geo").asOpt[Geo],
          (j \ "dnt").asOpt[Int],
          (j \ "lmt").asOpt[Int],
          Some(IP((j \ "ip").asOpt[String], (j \ "ipv6").asOpt[String])),
          Some(DeviceInfo((j \ "devicetype").asOpt[Int], (j \ "make").asOpt[String], (j \ "model").asOpt[String])),
          Some(OS((j \ "os").asOpt[String], (j \ "osv").asOpt[String])),
          (j \ "hvm").asOpt[String],
          Some(Size((j \ "h").asOpt[Int], (j \ "w").asOpt[Int])),
          (j \ "ppi").asOpt[Int],
          (j \ "pxratio").asOpt[Float],
          (j \ "js").asOpt[Int],
          (j \ "flashver").asOpt[String],
          (j \ "language").asOpt[String],
          (j \ "carrier").asOpt[String],
          (j \ "connectiontype").asOpt[Int],
          (j \ "ifa").asOpt[String],
          Some(DID((j \ "didsha1").asOpt[String], (j \ "didmd5").asOpt[String])),
          Some(DPID((j \ "dpidsha1").asOpt[String], (j \ "dpidmd5").asOpt[String])),
          Some(MAC((j \ "macsha1").asOpt[String], (j \ "macmd5").asOpt[String])),
          (j \ "ext").asOpt[Ext]
        ))
      }
      case _  => JsError("Invalid Device")
    }

  }
}