import com.openbidder.model.bidrequest.{Imp, BidRequest}
import com.openbidder.service.json.JsonService
import org.specs2.Specification
import scala.io.Source
import play.api.libs.json._

import scalaz.NonEmptyList

class Json extends Specification {
  def is = s2"""
    This is specification for JsonSerializerDeserializer
      Parsed result should have type BidRequest ${JsonParser.verifyResultType}
      Successfully parsed request ${JsonParser.verifyJsonObj}
      Parsed default value at ${JsonParser.verifyJsonObjAt}
      Parsed Imp should have type NonEmptyList[IMP] ${JsonParser.verifyImpSeq}
    """

  object JsonParser {
    val request = Source.fromURL(
      getClass().getResource("/examples/request.json")
    ).mkString

    val jsonService = new JsonService
    val jsonRequest = jsonService.parseBidRequest(request)

    val jsonWrite = jsonService.bidRequestToJson(jsonRequest)

    def verifyResultType = {
      jsonRequest must haveClass[BidRequest]
    }

    def verifyJsonObj = {
      jsonRequest.test must be equalTo 0
    }

    def verifyJsonObjAt = {
      jsonRequest.at must be equalTo 2
    }

    def verifyImpSeq = {
      jsonRequest.imp must haveClass[NonEmptyList[Imp]]
    }
  }

}