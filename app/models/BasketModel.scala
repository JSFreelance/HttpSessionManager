package models

import models.Item

case class Basket(user: String, items: List[Item])
