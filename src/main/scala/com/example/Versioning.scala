package com.example

object Versioning {
  object Version extends Enumeration {
    type Version = Value
    val v1, v2 = Value
  }

  trait Mapping[VersionedModel, StandardModel] {
    def version: Version.Value
    def fromVersionedModel(versionedModel: VersionedModel): StandardModel
    def toVersionedModel(standardModel: StandardModel): VersionedModel
  }
}
