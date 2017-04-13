package dao

import scala.concurrent.Future
import javax.inject.Inject

import akka.actor.Status.Success
import models.User
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.jdbc.JdbcProfile
import scala.concurrent.duration._

class UserDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile]
{
  import profile.api._

  private val Users = TableQuery[UsersTable]
  def all(): Future[Seq[User]] = db.run(Users.result)
  def findById(id: Int): Future[Option[User]] =
    db.run(Users.filter(_.id === id).result.headOption)

  def insert(user: User): Future[Unit] = db.run(Users += user).map { _ => ()}

  def delete(id: Int): Future[Unit] =
    db.run(Users.filter(_.id === id).delete).map(_ => ())

  def update(id: Int, user: User): Future[Unit] = {
    val userToUpdate: User = user.copy(Some(id))
    db.run(Users.filter(_.id === id).update(userToUpdate)).map(_ => ())
  }

  private class UsersTable(tag: Tag) extends Table[User](tag, "USER"){
    def id   = column[Int]("ID", O.PrimaryKey, O.AutoInc)
    def name = column[String]("NAME")
    def password = column[String]("PASSWORD")

    def * = (id.?, name, password) <> (User.tupled, User.unapply _)
  }

}
