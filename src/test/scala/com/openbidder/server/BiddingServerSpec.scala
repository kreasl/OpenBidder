package com.openbidder.server

import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpMethods.POST
import akka.http.scaladsl.model.{HttpEntity, HttpRequest, HttpResponse}
import akka.stream.ActorFlowMaterializer
import akka.stream.scaladsl.{Sink, Source}
import com.openbidder.model.bidrequest.BidRequest
import org.specs2._
import org.specs2.concurrent.ExecutionEnv
import org.specs2.control.NoLanguageFeatures
import org.specs2.matcher.FutureMatchers
import org.specs2.specification.{AfterEach, BeforeEach}
import utils.Generators

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.language.postfixOps

class BiddingServerSpec extends Specification with FutureMatchers with NoLanguageFeatures with BeforeEach with AfterEach {
  def is = s2"""

    This is a specification for bidding server

    BiddingServer should
      accept HttpRequest and respond with HttpResponse    $e1
                                                          """

  implicit val testSystem = akka.actor.ActorSystem("test-system")
  implicit val materializer = ActorFlowMaterializer()
  implicit val executor = testSystem.dispatcher

  val testInterface = "localhost"
  val testPort = 9000

  val server = new BiddingServerRunner {
    override lazy val interface = testInterface
    override lazy val port = testPort
  }

  def before = {
    server.startup()
  }

  def after = {
    server.shutdown()
  }

  def sendRequest(request: HttpRequest): Future[HttpResponse] = {

    val connection =
      Http().outgoingConnection(testInterface, testPort)

    Source.single(request) via connection runWith Sink.head
  }

  def e1 = {
    implicit ee: ExecutionEnv =>
      val bidRequest: BidRequest = Generators.randomBidRequest.run.unsafePerformIO()
      val responseFuture: Future[HttpResponse] = sendRequest(
        HttpRequest(POST, "/", entity = HttpEntity.Empty) // TODO: toJson(bidRequest)
      )

      val stringBodyFuture: Future[String] =
        responseFuture
          .flatMap(_.entity.toStrict(1 second))
          .map(_.data.utf8String)

      stringBodyFuture must be_==("TODO").await // TODO:
  }
}
