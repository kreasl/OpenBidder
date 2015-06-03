package com.openbidder.biddingserver

import org.specs2.Specification
import org.specs2.scalaz.ValidationMatchers


class DemandSideServerSpec extends Specification with ValidationMatchers { def is = s2"""

  This is a specification for DemandSideServer.

  DemandSideServer should
    return Failure for a request with an invalid json $e1
    return Failure for a stupid string $e2
    return Success for a valid request $e3
                                                        """
  val server = new DemandSideServer

  val validSspName = "ssp" //todo: should to be changed to real name
  val validRequest = getClass.getResourceAsStream("/examples/request.json")
  val validContentType = JsonContentType

  val invalidRequest = getClass.getResourceAsStream("/examples/invalid_request.json")
  def e1 = server.respond(validSspName, validContentType, invalidRequest) must beFailing

  val stupidContentType = "unreal content type"
  def e2 = server.respond(validSspName, stupidContentType, validRequest) must beFailing

  def e3 = server.respond(validSspName, validContentType, validRequest) must beSuccessful
}
