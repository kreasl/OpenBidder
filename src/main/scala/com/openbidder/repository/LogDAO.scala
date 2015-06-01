package com.openbidder.repository

import com.aerospike.client.{Record, Bin, Key}
import com.openbidder.log.{ImpLogEntry, BidLogEntry, LogEntry, LogLevel}

/** DAO for LogEntry objects
  *
  * Implements implicit converters to fie LogEntry objects with DAO methods
  *
  * @tparam T Specific LogEntry subclass
  */
class LogDAO[T <: LogEntry] extends DAO[T] {
  import LogDAOImplicits._

  implicit protected val set = config.getString("db.aerospike.log.name")
}

object LogDAOImplicits {
  implicit object PersistableImpLogEntry extends Persistable[ImpLogEntry] {
    def id(entry: ImpLogEntry) = entry.id

    def keys(entry: ImpLogEntry) = Seq("id", "ts", "level", "message", "objectId", "objectType")

    def serialize(entry: ImpLogEntry) = Map(
      "id" -> entry.id,
      "ts" -> entry.ts,
      "level" -> entry.level.toString,
      "message" -> entry.message,
      "objectId" -> entry.objectId.getOrElse(""),
      "objectType" -> entry.objectType.getOrElse("Imp")
    )

    def deserialize(values: Map[String, String]) = new ImpLogEntry(
      values("id"),
      values("ts"),
      LogLevel(values("level").toInt),
      values("message"),
      Some(values("objectId")),
      Some(values("objectType"))
    )
  }

  implicit object PersistableBidLogEntry extends Persistable[BidLogEntry] {
    def id(entry: BidLogEntry) = entry.id

    def keys(entry: BidLogEntry) = Seq("id", "ts", "level", "message", "objectId", "objectType")

    def serialize(entry: BidLogEntry) = Map(
      "id" -> entry.id,
      "ts" -> entry.ts,
      "level" -> entry.level.toString,
      "message" -> entry.message,
      "objectId" -> entry.objectId.getOrElse(""),
      "objectType" -> entry.objectType.getOrElse("Imp")
    )

    def deserialize(values: Map[String, String]) = new BidLogEntry(
      values("id"),
      values("ts"),
      LogLevel(values("level").toInt),
      values("message"),
      Some(values("objectId")),
      Some(values("objectType"))
    )
  }
}