import org.openrtb.validator._
import org.specs2.mutable.Specification

import scala.io.Source

class OpenRtbValidatorSpec extends Specification {
  "openrtb-validator simple lib test" >> {
    "example request validation test" >> {
      val request = Source.fromURL(
        getClass.getResource("/examples/request.json")
      ).mkString
      val validator = OpenRtbValidatorFactory.getValidator(
        OpenRtbInputType.BID_REQUEST,
        OpenRtbVersion.V2_3
      )
      validator.isValid(request) must beTrue
    }
    "example response validation test" >> {
      val response = Source.fromURL(
        getClass.getResource("/examples/response.json")
      ).mkString
      val validator = OpenRtbValidatorFactory.getValidator(
        OpenRtbInputType.BID_RESPONSE,
        OpenRtbVersion.V2_3
      )
      validator.isValid(response) must beTrue
    }
  }
}