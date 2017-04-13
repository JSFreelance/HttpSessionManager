package controllers.api.v1

import security.Secured
import javax.inject._
import java.util.Base64

import play.api.mvc._
import play.api.libs.json._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import models.{Basket, Item, User}
import services.{BasketService, ItemService, UserService}
import dao.UserDAO

@Singleton
class SessionController @Inject() (userDAO: UserDAO) extends Controller
{

  val user: User = User(None, "jairo", "pwd")
  val users: List[User] = List(user)
  var item: Item = Item(1, 2.53, "awesome item!!")
  val items: List[Item] = List(item)
  val itemService = ItemService(items)
  var basket: Basket = Basket("jairo", items)
  var baskets = List(basket)

  val userService = new UserService(users)
  var basketService = BasketService

  basketService.insertBasket("jairo", basket)

  implicit val userWrites = new Writes[User]
  {
    def writes(user: User) = Json.obj(
      "id" -> user.id,
      "name" -> user.name
    )
  }

  implicit val basketWrites = new Writes[Basket]
  {
    def writes(basket: Basket) = Json.obj(
      "user" -> basket.user,
      "items" -> basket.items.length
    )
  }

  implicit val itemWrites = new Writes[Item]
  {
    def writes(item: Item) = Json.obj(
      "id" -> item.id,
      "price" -> item.price,
      "description" -> item.description
    )
  }


  def home = Action {
    Status(200)(Json.toJson("""{"current_path": "home"}"""))
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

  def getUserBasket(name: String) = Secured {
    Action{
      val basket_obj_list = baskets

      if(baskets.isEmpty){
        Ok(Json.toJson("""{}"""))
      }else{
        Ok(Json.toJson(basket_obj_list.head))
      }
    }
  }

  def getUsers = Action.async {

    userDAO.all().map { case (users) => Ok(Json.toJson(users)) }
  }

  def insertUser(name: String, password: String) = Action.async {
    val user: User = User(None, name, password)
    userDAO.insert(user).map(_ => Redirect(routes.SessionController.home))
    userDAO.all().map { case (users) => Ok(Json.toJson(users)) }
  }

  def updateUser(id: Int) = Action.async { implicit rs =>
    userDAO.update(3, User(None, "new", "pwd")).map( _ => Redirect(routes.SessionController.home))
  }


  def deleteUser(id: Int) = Action.async { implicit rs =>
    userDAO.delete(id).map(_ => Redirect(routes.SessionController.home))
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

  def logout = Action { implicit request =>
    Redirect(routes.SessionController.home) withNewSession
  }
}
