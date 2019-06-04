package com.example.models.v1

import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{ Matchers, WordSpec }
import org.scalatest.concurrent.ScalaFutures

class UsersMappingV1Spec extends WordSpec with Matchers with ScalaFutures with ScalatestRouteTest {
  "UsersMapping" should {
    "with standard name" should {
      val versionedModel = User(firstName = "Daniel", lastName = "Battaglia", age = 39)
      val standardModel = com.example.User(name = "Daniel Battaglia", age = 39, countryOfResidence = None)

      "convert a versioned model to a standard model" in {
        UsersMapping.fromVersionedModel(versionedModel) shouldBe standardModel
      }

      "convert a standard model to a versioned model" in {
        UsersMapping.toVersionedModel(standardModel) shouldBe versionedModel
      }
    }

    "with single word name" should {
      val versionedModel = User(firstName = "Daniel", lastName = "", age = 39)
      val standardModel = com.example.User(name = "Daniel", age = 39, countryOfResidence = None)

      "convert a versioned model to a standard model" in {
        UsersMapping.fromVersionedModel(versionedModel) shouldBe standardModel
      }

      "convert a standard model to a versioned model" in {
        UsersMapping.toVersionedModel(standardModel) shouldBe versionedModel
      }
    }

    "with multi word name" should {
      val versionedModel = User(firstName = "Daniel Richard", lastName = "Battaglia", age = 39)
      val standardModel = com.example.User(name = "Daniel Richard Battaglia", age = 39, countryOfResidence = None)

      "convert a versioned model to a standard model" in {
        UsersMapping.fromVersionedModel(versionedModel) shouldBe standardModel
      }

      "convert a standard model to a versioned model" in {
        UsersMapping.toVersionedModel(standardModel) shouldBe versionedModel
      }
    }

    "with empty name" should {
      val versionedModel = User(firstName = "", lastName = "", age = 39)
      val standardModel = com.example.User(name = "", age = 39, countryOfResidence = None)

      "convert a versioned model to a standard model" in {
        UsersMapping.fromVersionedModel(versionedModel) shouldBe standardModel
      }

      "convert a standard model to a versioned model" in {
        UsersMapping.toVersionedModel(standardModel) shouldBe versionedModel
      }
    }
  }
}
