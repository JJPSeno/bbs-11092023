package models.services

import models.domains.Post
import models.repos._
import javax.inject._
import play.api.db.slick.HasDatabaseConfigProvider
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import scala.concurrent.{ExecutionContext, Future}


@Singleton
class HomeService @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider, 
  val postRepo: PostRepo
)
(implicit executionContext: ExecutionContext)
extends HasDatabaseConfigProvider[JdbcProfile]{
  import profile.api._

  def createSchemas ={ 
    db.run(
      postRepo.createPostSchema 
    )

  }

  def getAllPosts() = {
    postRepo.all()
  }

  def addPost(newPost: Post) = {
    postRepo.insert(newPost)
  }

  def deletePost(targetPostId: Long) = {
    postRepo.delete(targetPostId)
  }
  
}
