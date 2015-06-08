package com.openbidder.repository

import com.aerospike.client.{Key, Bin, Record}
import com.typesafe.config.ConfigFactory

/** DAO trait
  *
  * Implements generic CRUD methods
  *
  * @tparam T Type of objects to handle with specific DAO instance
  */
trait DAO[T] {
  val config = ConfigFactory.load("db")

  implicit protected val set: String
  
  lazy val provider = ProviderFactory(set)

  def save(entity: T)(implicit ps: Persistable[T]) = provider.save(ps.id(entity), ps.serialize(entity))

  def load(entity: T)(implicit ps: Persistable[T]): Option[T] = {
    val result = provider.load(ps.id(entity), ps.keys(entity))

    result.map {
      fields => ps.deserialize(fields)
    }
  }

  def delete(id: String)(implicit ps: Persistable[T]): Boolean = provider.delete(id)

  def delete(entity: T)(implicit ps: Persistable[T]): Boolean = delete(ps.id(entity))
}
