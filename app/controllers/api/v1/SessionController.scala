package controllers.api.v1

import javax.inject._
import java.util.Base64
import play.api._
import play.api.mvc._
import play.api.mvc.Security
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import models.User
import services.UserService

//case class Secured[A](action: Action[A]) extends Action[A]{
//  private val users: UserService = new UserService()
//
//  override def apply(request: Request[A]): Future[Result] = request.session.get("user").map(users.isLogged)  match {
//    case None => Future(Results.Redirect(routes.SessionController.index("jairo")))
//    case Some(r) if r => action(request)
//    case Some(r) if !r => Future(Results.Redirect("/login"))
//  }
//  override def parser: BodyParser[A] = action.parser
//}



@Singleton
class SessionController  @Inject() extends Controller
{

  val user: User = User(1, "jairo", "pwd")
  val users: List[User] =  List(user)
  val userService = new UserService(users)

  def index(name: String) = Action{
    Ok("sd")
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
