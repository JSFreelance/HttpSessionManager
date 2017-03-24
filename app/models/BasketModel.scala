package models

import models.Item

case class Basket(userId: Int, items: List[Item])
