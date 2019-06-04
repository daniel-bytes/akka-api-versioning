package com.example.models.v2

import com.example.Versioning.{ Mapping, Version }
import com.example.{ User => UserModel }
import io.scalaland.chimney.dsl._

object UsersMapping extends Mapping[User, UserModel] {
  val version: Version.Value = Version.v2

  def fromVersionedModel(versionedModel: User): UserModel =
    versionedModel
      .into[UserModel]
      .withFieldComputed(_.countryOfResidence, u => Option(u.countryOfResidence) match {
        case Some("") | None => None
        case Some(x) => Some(x)
      })
      .transform

  def toVersionedModel(standardModel: UserModel): User =
    standardModel
      .into[User]
      .withFieldComputed(_.countryOfResidence, u => u.countryOfResidence.getOrElse(""))
      .transform
}
