
import org.scalatestplus.play._
import play.api.libs.json._
import play.api.test.Helpers._
import play.api.test._
import auth.Security

class UserloggedSpec extends PlaySpec with OneAppPerTest
{
  "ap1.v1.SessionController" should {

    // User Logged Test case:

    "POST /login" in {
      val endpoint = route(app, FakeRequest(POST, "/login").withHeaders(("Authorization", Security.EncodeAuthHeaders("jairo","pwd")))).get

      status(endpoint) mustBe 303
      contentType(endpoint) mustBe None //Redirects to home page
    }



  }
}
