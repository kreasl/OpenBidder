package com.openbidder.service

import scala.util.Random

/**
 * Created by Yury Talapila on 7.5.15.
 */
object RandomAnumId {
  def nextRandomAnumId = Random.alphanumeric.take(32).mkString
}
