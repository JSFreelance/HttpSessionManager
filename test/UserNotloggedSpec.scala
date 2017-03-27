
import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._

class UserNotloggedSpec extends PlaySpec with OneAppPerTest
{
  "ap1.v1.SessionController" should {
    "load the home endpoint" in {
      val home = route(app, FakeRequest(GET, "/home")).get

      status(home) mustBe OK
      contentType(home) mustBe Some("application/json")
    }
  }
}
