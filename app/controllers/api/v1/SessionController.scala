package controllers.api.v1

import security.Secured
import javax.inject._
import java.util.Base64
import play.api.mvc._
import play.api.libs.json._
import models.User
import services.UserService


@Singleton
class SessionController @Inject() extends Controller
{

  val user: User = User(1, "jairo", "pwd")
  val users: List[User] =  List(user)
  val userService = new UserService(users)


  implicit val userWrites = new Writes[User] {
    def writes(user: User) = Json.obj(
      "id" -> user.id,
      "name" -> user.name
    )
  }

  def index(name: String) = Secured {
    Action{
      val user_obj_list = userService.findUserByName(name)
      if (user_obj_list.isEmpty){
        Ok(Json.toJson("""{}"""))
      }else{
        Ok(Json.toJson(user_obj_list.head))
      }
    }
  }

  def home = Action {
    Ok("home")
  }

  def login = Action {  request =>
    request.headers.get("authorization") match{
      case Some(headers) => {
        val auth = headers.substring(6)
        val decodeAuth = Base64.getDecoder().decode(auth)
        val credentials = new String(decodeAuth, "UTF-8").split(":")

        if (!userService.findUserByName(credentials(0)).isEmpty){
          Redirect(routes.SessionController.index(credentials(0))) withSession("user" -> credentials(0))
        }else{
          Redirect(routes.SessionController.home)
        }
      }
      case _ => {
        Redirect(routes.SessionController.home)
      }
    }
  }
}
