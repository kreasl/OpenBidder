package com.openbidder.repository

/**
 * Created by Yury Talapila on 2.6.15.
 */
trait Persistable[T] {
  def id(entry: T): String

  def keys(entry: T): Seq[String]

  def serialize(entry: T): Map[String, String]

  def deserialize(values: Map[String, String]): T
}
