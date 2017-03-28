
import org.scalatestplus.play._
import play.api.libs.json._
import play.api.test.Helpers._
import play.api.test._
import auth.Security

class UserloggedSpec extends PlaySpec with OneAppPerTest
{
  "ap1.v1.SessionController" should {

    // User Logged Test case:
    // We get the Redirect request before the resulting request, example: first a 303 request and after a 200 request

    "POST /login" in {
      val endpoint = route(app, FakeRequest(POST, "/login").withHeaders(("Authorization", Security.EncodeAuthHeaders("jairo","pwd")))).get

      status(endpoint) mustBe 303
      contentType(endpoint) mustBe None //Redirects to home page
    }

    "GET /home" in {
      val endpoint = route(app, FakeRequest(GET, "/home").withHeaders(("Authorization", Security.EncodeAuthHeaders("jairo","pwd")))).get

      status(endpoint) mustBe OK
      contentType(endpoint) mustBe Some("application/json") //Redirects to home page
    }

    "GET /index/jairo" in {
      val endpoint = route(app, FakeRequest(GET, "/index/jairo").withHeaders(("Authorization", Security.EncodeAuthHeaders("jairo","pwd")))).get

      status(endpoint) mustBe 303
      contentType(endpoint) mustBe None //Redirects to home page
    }

    "GET /index/ssd" in {
      val endpoint = route(app, FakeRequest(GET, "/index/ssd").withHeaders(("Authorization", Security.EncodeAuthHeaders("jairo","pwd")))).get

      status(endpoint) mustBe 303
      contentType(endpoint) mustBe None //Redirects to home page
    }

    "GET /basket/jairo" in {
      val endpoint = route(app, FakeRequest(GET, "/basket/jairo").withHeaders(("Authorization", Security.EncodeAuthHeaders("jairo","pwd")))).get

      status(endpoint) mustBe 303
      contentType(endpoint) mustBe None //Redirects to home page
    }

    "GET /basket/ssd" in {
      val endpoint = route(app, FakeRequest(GET, "/basket/ssd").withHeaders(("Authorization", Security.EncodeAuthHeaders("jairo","pwd")))).get

      status(endpoint) mustBe 303
      contentType(endpoint) mustBe None //Redirects to home page
    }


  }
}
