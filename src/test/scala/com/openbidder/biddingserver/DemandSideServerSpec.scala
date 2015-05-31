package com.openbidder.biddingserver

import org.specs2.Specification

class DemandSideServerSpec extends Specification { def is = s2"""

  This is a specification for DemandSideServer.

  DemandSideServer should
    return BadRequest for a request with an invalid json $e1
    return UnsupportedContentType for a stupid string $e2
    return Bid or NoBid for a valid request $e3
                                                        """
  val server = new DemandSideServer

  val validSspName = "ssp" //todo: should to be changed to real name
  val validRequest = getClass.getResourceAsStream("/examples/request.json")
  val validContentType = JsonContentType

  val invalidRequest = getClass.getResourceAsStream("/examples/invalid_request.json")
  def e1 = server.respond(validSspName, validContentType, invalidRequest) must beTheSameAs(BadRequest)

  val stupidContentType = "unreal content type"
  def e2 = server.respond(validSspName, stupidContentType, validRequest) must beTheSameAs(UnsupportedContentType)

  def e3 = server.respond(validSspName, validContentType, validRequest) must beTheSameAs(NoBid) or haveClass[Bid]
}
