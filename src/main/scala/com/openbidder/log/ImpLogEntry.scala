package com.openbidder.log

import com.openbidder.log.LogLevel._
import com.openbidder.model.bidrequest.Imp
import com.openbidder.service.RandomAnumId

/** LogEntry for Imp(impression) objects
  *
  * @param ts timestamp
  * @param id LogEntry unique ID
  * @param level LogLevel
  * @param message String describes Imp object
  * @param objectId Imp object ID
  * @param objectType "Imp" string constant
 */
case class ImpLogEntry(
  override val id: String = RandomAnumId.nextRandomAnumId,
  override val ts: String = RandomAnumId.nextRandomAnumId,
//  override val ts: String = System.currentTimeMillis().toString,
  override val level: LogLevel = LogLevel.Info,
  val message: String,
  override val objectId: Option[String] = None,
  override val objectType: Option[String] = Some("Imp")
) extends LogEntry {
  def this(imp: Imp) = this(
    message = ImpLogEntry.composeMessage(imp),
    objectId = Some(imp.id)
  )
}

/** Helper methods for ImpLogEntry
 */
object ImpLogEntry {
  def composeMessage(imp: Imp) = {
    val messageParts = Seq[Option[String]](
      imp.banner.map(_ => "banner"),
      imp.video.map(_ => "video"),
      imp.displaymanager.map(_ + imp.displaymanagerver.getOrElse("")),
      imp.instl match {
        case 1 => Some("full screen")
      },
      imp.tagid.map("tagid: " + _),
      Some("floor: " + imp.bidfloor + imp.bidfloorcur),
      imp.secure.map {
        case 0 => "insecure"
        case 1 => "secure"
      },
      imp.iframebuster.map("iframe busters: [" + _.reduce(_ + ", " + _) + "]"),
      imp.pmp.map(_ => "pmp"),
      imp.ext.map(_ => "ext")
    )

    "Impression received: " + messageParts.flatten.reduce(_ + ", " + _)
  }
}