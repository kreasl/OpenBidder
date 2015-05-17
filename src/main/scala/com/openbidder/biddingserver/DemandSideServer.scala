package com.openbidder.biddingserver

import java.io.InputStream

class DemandSideServer {
  def respond(sspName: String, contentType: String, inStream: InputStream): DSSResponse = {
    if (isUnknownServer(sspName)) {
      UnknownServer
    } else if (isUnsupportedContentType(contentType)) {
      UnsupportedContentType
    } else {
      //TODO: implement
      NoBid
    }
  }

  private def isUnsupportedContentType(contentType: String): Boolean = {
    //TODO: implement
    false
  }

  private def isUnknownServer(sspName: String): Boolean = {
    //TODO: implement
    false
  }
}

sealed trait DSSResponse
case object UnknownServer extends DSSResponse
case object UnsupportedContentType extends DSSResponse
case object BadRequest extends DSSResponse
case class Bid(body: Array[Byte]) extends DSSResponse
case object NoBid extends DSSResponse