package com.openbidder.repository

import com.typesafe.config.ConfigFactory
import com.aerospike.client.{AerospikeClient, Key, Bin}
import com.aerospike.client.policy.{RecordExistsAction, WritePolicy}

/** Provider trait
  */
trait Provider {
  def save(id: String, values: Map[String, String])(implicit set: String)

  def load(id: String, keys: Seq[String])(implicit set: String): Option[Map[String, String]]

  def delete(id: String)(implicit set: String): Boolean
}

/** Aerospike DB provider
  *
  * @param host String server address
  * @param port Int
  */
class AerospikeProvider(host: String, port: Int, namespace: String) extends Provider {
  val writePolicy = new WritePolicy
  writePolicy.recordExistsAction = RecordExistsAction.UPDATE

  lazy val client = createClient

  def createClient = new AerospikeClient(null, host, port)

  def save(id: String, values: Map[String, String])(implicit set: String) = {
    val key = new Key(namespace, set, id)

    val bins: Seq[Bin] = values.toSeq.map(t => new Bin(t._1, t._2))

    bins.foreach {client.put(writePolicy, key, _)}
  }

  def load(id: String, keys: Seq[String])(implicit set: String): Option[Map[String, String]] = {
    val key = new Key(namespace, set, id)

    val result = Option(client.get(null, key))

    result.map {
      record => keys.map(key => (key, record.getString(key))).toMap
    }
  }

  def delete(id: String)(implicit set: String): Boolean = {
    val key = new Key(namespace, set, id)

    client.delete(null, key)
  }
}

/** Aerospike connections factory
  *
  * Implements Memoization pattern to reduce resource utilization
  */
object AerospikeProvider {
  val config = ConfigFactory.load("db")

  private val configHierarchy = List("db", "aerospike")

  private var providers = Map[(String, Int, String), AerospikeProvider]()

  private def getHierarchyPath(hierarchy: List[String], key: String): String = {
    val path =  hierarchy :+ key mkString "."

    if (hierarchy.length == 0)
        throw new NoSuchElementException
    if (config.hasPath(path))
      path
    else
      getHierarchyPath(hierarchy.dropRight(1), key)
  }

  def apply(dataSetName: String): AerospikeProvider = {
    val hierarchy = configHierarchy :+ dataSetName

    val host = config.getString(getHierarchyPath(hierarchy, "host"))
    val port = config.getInt(getHierarchyPath(hierarchy, "port"))
    val namespace = config.getString("db.aerospike.namespace")

    if (!providers.contains((host, port, namespace))) {
      providers += ((host, port, namespace) -> new AerospikeProvider(host, port, namespace))
    }

    providers((host, port, namespace))
  }
}
