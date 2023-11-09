package models.domains

import play.api.libs.json._

final case class Post(
  id: Long = 0L,
  message: String,
  password: Option[String],
)