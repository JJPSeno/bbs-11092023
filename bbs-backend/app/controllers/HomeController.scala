package controllers

import models.services.HomeService
import models.domains.Post
import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import scala.concurrent.{ExecutionContext, Future}


final case class PostFormClass(
  postMessage: String,
  postPassword: Option[String]
) 

val postForm: Form[PostFormClass] = Form(mapping(
  "postMessage"-> nonEmptyText, 
  "postPassword" -> optional(text)
  )
(PostFormClass.apply)((postForm: PostFormClass) => Some(postForm.postMessage, postForm.postPassword)))

case class PostToJson(
  id: Long,
  message: String,
)

implicit val postWrites: Writes[Post] = 
  new Writes[Post]{
    def writes(post: Post) = Json.obj(
      "id" -> post.id,
      "message"  -> post.message,
    )
  }

implicit val postReads: Reads[Post] = (
(JsPath \ "id").read[Long] and
  (JsPath \ "message").read[String] and
  (JsPath \ "password").readNullable[String]
)(Post.apply _)

@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents, val homeService: HomeService) 
(implicit executionContext: ExecutionContext) extends BaseController {

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def init() = Action.async{ implicit request: Request[AnyContent] =>
    homeService.createSchemas.map{_=>Ok("db initialied")}
  }
  
  def getAllPosts() = Action.async { implicit request: Request[AnyContent] =>
    homeService.getAllPosts().map{posts => Ok(Json.toJson(posts))}
  }

  def addPost() = 
    println("hit add post")
    Action.async { implicit request =>
    println(request.body.asJson)
    postForm.bindFromRequest().fold(
      formWithErrors => {
        println("form with errors")
        Future.successful(BadRequest("Please try again. Errors: " + formWithErrors.errors.mkString("\n")))
      },
      post => {
        println("post: " + post )
          val newPost = Post(
            message = post.postMessage,
            password = post.postPassword,
          )
          homeService.addPost(newPost).map(_ => Ok("Post Saved"))
      })
    }

}
