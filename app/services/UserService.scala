package services

import models.User

case class UserService(users: List[User]) {

  def findUserByName(name: String): List[User] =
  {
    users.filter(_.name==name)
  }

  def isLogged(name: String): Boolean =
  {
    users.contains(name)
  }

}
