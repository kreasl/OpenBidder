package com.openbidder.server

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.{IncomingConnection, ServerBinding}
import akka.http.scaladsl.model.HttpMethods.POST
import akka.http.scaladsl.model._
import akka.kernel.Bootable
import akka.stream.ActorFlowMaterializer
import akka.stream.scaladsl.{Sink, Source}
import akka.util.Timeout
import com.openbidder.model.bidresponse.BidResponse
import com.openbidder.service.biddingservice.{BiddingService, SimpleBiddingService}
import com.typesafe.config.ConfigFactory
import utils.Generators._

import scala.concurrent.Future
import scala.concurrent.duration._


trait BiddingServer {
  val bidder: BiddingService

  def accept(httpRequest: HttpRequest): HttpResponse = {
    val requestEntity: RequestEntity = httpRequest.entity
    val bidRequest = randomBidRequest.run.unsafePerformIO() // TODO fromJson(requestEntity)
    val bidResponse: BidResponse = bidder.process(bidRequest)
    HttpResponse(
      entity = "TODO" // TODO: toJson(bidResponse)
    )
  }

  def syncRequestHandler(request: HttpRequest): HttpResponse = {
    request match {
      case HttpRequest(POST, Uri.Path("/"), _, _, _) =>
        accept(request)
      case _: HttpRequest =>
        HttpResponse(404, entity = "Unknown resource!")
    }
  }
}

class BiddingServerRunner extends Bootable with BiddingServer {
  implicit val askTimeout: Timeout = 100.millis
  implicit val system = ActorSystem()
  implicit val materializer = ActorFlowMaterializer()
  implicit val executor = system.dispatcher

  val bidder: BiddingService = new SimpleBiddingService()

  val config = ConfigFactory.load()
  lazy val interface = config.getString("bidder.http.interface")
  lazy val port = config.getInt("bidder.http.port")

  override def startup(): Unit = {
    val serverSource: Source[IncomingConnection, Future[ServerBinding]] =
      Http().bind(interface, port)

    serverSource.to(Sink.foreach {
      connection =>
        connection.handleWithSyncHandler(syncRequestHandler)
    }).run()
  }

  override def shutdown(): Unit = {
    system.shutdown()
  }
}