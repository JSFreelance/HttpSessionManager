
import org.scalatestplus.play._

import play.api.test._
import play.api.test.Helpers._
import play.api.libs.json._

class UserNotloggedSpec extends PlaySpec with OneAppPerTest
{
  "ap1.v1.SessionController" should {
    "load the home endpoint" in {
      val home = route(app, FakeRequest(GET, "/home")).get

      status(home) mustBe OK
      contentType(home) mustBe Some("application/json")

      val home_content: JsValue = contentAsJson(home)
      val home_spec: JsValue = Json.toJson("""{"current_path": "home"}""")
      home_content mustBe home_spec
    }
  }
}
