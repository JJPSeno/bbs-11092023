package models.repos

import models.domains.Post
import javax.inject._
import play.api.db.slick.HasDatabaseConfigProvider
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class PostRepo @Inject() 
(protected val dbConfigProvider: DatabaseConfigProvider)
(implicit executionContext: ExecutionContext) 
extends HasDatabaseConfigProvider[JdbcProfile]{
  import profile.api._

  protected class PostTable(tag: Tag) extends Table[Post](tag, "POSTS"){
    def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
    def message = column[String]("MESSAGE", O.Length(255))
    def password = column[Option[String]]("PASSWORD", O.Length(255))

    def * = (id, message, password).mapTo[Post]
  }

  lazy val PostTable = TableQuery[PostTable]
  def createPostSchema = PostTable.schema.createIfNotExists

  def all() = 
    db.run(PostTable.result)

  def getPost(targetPostId: Long) = 
    db.run(PostTable.filter(_.id === targetPostId).result)

  def insert(newPost: Post) = 
    db.run((PostTable returning PostTable.map(_.id)) += newPost)
  
  def delete(targetPostId: Long) = 
    db.run(PostTable.filter(_.id === targetPostId).delete)

}