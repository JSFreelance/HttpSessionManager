
import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._
import play.api.libs.json._

class UserNotloggedSpec extends PlaySpec with OneAppPerTest
{
  "ap1.v1.SessionController" should {

    // User not Logged in Test case:

    "GET the home endpoint" in {
      val endpoint = route(app, FakeRequest(GET, "/home")).get

      status(endpoint) mustBe OK
      contentType(endpoint) mustBe Some("application/json")

      val home_content: JsValue = contentAsJson(endpoint)
      val home_spec: JsValue = Json.toJson("""{"current_path": "home"}""")
      home_content mustBe home_spec
    }

    "GET a endpoint that not exists" in {
      val endpoint = route(app, FakeRequest(GET, "/fake")).get

      status(endpoint) mustBe NOT_FOUND
      contentType(endpoint) mustBe Some("text/html")
    }

    "GET logout endpoint" in {
      val endpoint = route(app, FakeRequest(GET, "/logout")).get

      status(endpoint) mustBe 303  // Redirect: https://en.wikipedia.org/wiki/HTTP_303
      contentType(endpoint) mustBe None  //We don't receive any content before Redirect
    }

    "GET index/existing_user " in {
      val endpoint = route(app, FakeRequest(GET, "/index/jairo")).get

      status(endpoint) mustBe 303  // Redirect: https://en.wikipedia.org/wiki/HTTP_303
      contentType(endpoint) mustBe None  //We don't receive any content before Redirect
    }

    "GET index/non_existing_user " in {
      val endpoint = route(app, FakeRequest(GET, "/index/jairosdsd")).get

      status(endpoint) mustBe 303  // Redirect: https://en.wikipedia.org/wiki/HTTP_303
      contentType(endpoint) mustBe None  //We don't receive any content before Redirect
    }

    "GET index/existing_basket " in {
      val endpoint = route(app, FakeRequest(GET, "/basket/jairo")).get

      status(endpoint) mustBe 303  // Redirect: https://en.wikipedia.org/wiki/HTTP_303
      contentType(endpoint) mustBe None  //We don't receive any content before Redirect
    }

    "GET index/non_existing_basket " in {
      val endpoint = route(app, FakeRequest(GET, "/basket/jsdsairo")).get

      status(endpoint) mustBe 303  // Redirect: https://en.wikipedia.org/wiki/HTTP_303
      contentType(endpoint) mustBe None  //We don't receive any content before Redirect
    }

  }
}
