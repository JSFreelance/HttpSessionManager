package services

import models.Basket
import scala.collection.concurrent.TrieMap


trait BasketService {
  def insertBasket(user: String, basket: Basket): Boolean
  def getBasket(user: String): List[Basket]
  def updateBasket(user: String, basket: Basket): Boolean
  def deleteBasket(user: String): Boolean
}

object BasketService extends BasketService
{
  private val baskets: TrieMap[String, Basket] = TrieMap()


  def insertBasket(user: String, basket: Basket): Boolean =
  {
    baskets.put(user, basket) match {
      case Some(_) => true
      case _       => false
    }
  }

  def getBasket(user: String): List[Basket] =
  {
    baskets.get(user) match {
      case Some(basket) => List(basket)
      case _            => List()
    }
  }

  def updateBasket(user: String, basket: Basket): Boolean =
  {
    baskets.replace(user, basket) match {
      case Some(_) => true
      case _       => false
    }
  }

  def deleteBasket(user: String): Boolean =
  {
    baskets.remove(user) match {
      case Some(_) => true
      case _       => false
    }
  }

}
