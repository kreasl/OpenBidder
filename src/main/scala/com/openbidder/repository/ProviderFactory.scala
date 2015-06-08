package com.openbidder.repository

import com.typesafe.config.ConfigFactory

/** DB Provider Factory
  */
object ProviderFactory {
  lazy val config = ConfigFactory.load("db")

  def apply(providerName: String, dataSetName: String): Provider =
    providerName match {
      case "aerospike" => AerospikeProvider(dataSetName)
    }

  def apply(dataSetName: String): Provider = {
    val providerName = config.getString("db.provider")

    this(providerName, dataSetName)
  }
}