package com.openbidder

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.HttpMethods._
import akka.stream.ActorFlowMaterializer
import akka.stream.scaladsl.{Sink, Source}

import scala.concurrent.Future

object BidServer extends App {

  implicit val system = ActorSystem()
  implicit val materializer = ActorFlowMaterializer()

  val requestHandler: HttpRequest => HttpResponse = {
    // TODO: BidRequest => BidResponse

    case _: HttpRequest =>
      HttpResponse(404, entity = "Unknown resource!")
  }

  val serverSource: Source[Http.IncomingConnection, Future[Http.ServerBinding]] =
    Http(system).bind(interface = "localhost", port = 8080)
  val bindingFuture: Future[Http.ServerBinding] = serverSource.to(Sink.foreach { connection =>
    println("Accepted new connection from " + connection.remoteAddress)
    connection handleWithSyncHandler requestHandler
  }).run()

  println("Server started")
}
