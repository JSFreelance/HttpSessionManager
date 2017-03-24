package controllers.api.v1.security

import play.api.mvc._
import controllers.api.v1.routes
import models.User
import services.UserService
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by jairo on 24/03/17.
  */
case class Secured[A](action: Action[A]) extends Action[A]{
  private val users: UserService = new UserService(List(User(1, "jairo", "pwd")))

  override def apply(request: Request[A]): Future[Result] = request.session.get("user").map(users.isLogged)  match {
    case None => Future(Results.Redirect(routes.SessionController.home()))
    case Some(r) if r => action(request)
    case Some(r) if !r => action(request)
  }
  override def parser: BodyParser[A] = action.parser
}
