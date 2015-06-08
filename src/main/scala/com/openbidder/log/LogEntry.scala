package com.openbidder.log

import com.openbidder.service.RandomAnumId

/** Common Log Entry type trait
  */
trait LogEntry {
  import LogLevel._

  val ts: String = RandomAnumId.nextRandomAnumId
//  val ts: String = System.currentTimeMillis().toString
  val id: String = ts + RandomAnumId.nextRandomAnumId
  val level: LogLevel = Info
  val message: String
  val objectId: Option[String] = None
  val objectType: Option[String] = None
}

/** LogLevel enumeration type
  */
object LogLevel extends Enumeration {
  type LogLevel = Value

  var Error, Warning, Info, Debug = Value
}