package com.openbidder.model.bidrequest

import scalaz.NonEmptyList

case class BidRequest(id: String, imp: NonEmptyList[Imp], site: Site, app: App,
                      device: Device, user: User, test: Int = 0, at: Int = 2,
                      tmax: Option[Int], wseat: Option[Seq[String]], allimps: Int = 0,
                      cur: Option[Seq[String]], bcat: Option[Seq[String]], badv: Option[Seq[String]],
                      regs: Option[Regs], ext: Option[Any])