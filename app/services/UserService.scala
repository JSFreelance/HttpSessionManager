package services

import models.User

class UserService() {

  def findUserByName(name: String, userList: List[User]): List[User] =
  {
    userList.filter(_.name==name)
  }

//  def isLogged(name: String): Boolean =
//  {
//    users.contains(name)
//  }

}
