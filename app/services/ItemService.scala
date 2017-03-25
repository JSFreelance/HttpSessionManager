package services

import models.Item


case class ItemService(items: List[Item]) {

  def findItemById(id: Int): List[Item] =
  {
    items.filter(_.id==id)
  }

  def isLogged(name: String): Boolean =
  {
    items.contains(name)
  }

}
