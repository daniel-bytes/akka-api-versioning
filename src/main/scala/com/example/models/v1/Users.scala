package com.example.models.v1

import com.example.Versioning.{ Mapping, Version }
import com.example.{ User => UserModel }
import io.scalaland.chimney.dsl._

object UsersMapping extends Mapping[User, UserModel] {
  val version: Version.Value = Version.v1

  def fromVersionedModel(versionedModel: User): UserModel =
    versionedModel
      .into[UserModel]
      .withFieldComputed(_.countryOfResidence, _ => None)
      .withFieldComputed(_.name, u => (u.firstName + " " + u.lastName).trim)
      .transform

  def toVersionedModel(standardModel: UserModel): User =
    standardModel
      .into[User]
      .withFieldComputed(_.firstName, u => u.name.split(" ") match {
        case arr if arr.length > 1 => arr.dropRight(1).mkString(" ")
        case arr => arr.head
      })
      .withFieldComputed(_.lastName, u => u.name.split(" ") match {
        case arr if arr.length > 1 => arr.last
        case _ => ""
      })
      .transform
}
