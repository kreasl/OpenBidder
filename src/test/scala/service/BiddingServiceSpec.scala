package service

import com.openbidder.model.bidrequest.{Imp, BidRequest}
import com.openbidder.service.biddingservice.SimpleBiddingService
import scalaz.NonEmptyList
import org.specs2.Specification
import org.specs2.mock.Mockito

/**
 * Created by Yury Talapila on 13.5.15.
 */
class BiddingServiceSpec extends Specification with Mockito { def is = s2"""

    This is specification for Bidding Services

      Successfully responds with BidResponse to BidRequest  ${BidService.verifyResponse}
                                                            """


  object BidService {
    val imp = mock[Imp]
    imp.id returns "impid"

    val request = mock[BidRequest]
    request.id returns "requestid"
    request.imp returns NonEmptyList(imp)
    request.cur returns None

    def verifyResponse = {
      val sb = new SimpleBiddingService

      val response = sb.process(request)

      response.id must be equalTo "requestid"
    }
  }
}
