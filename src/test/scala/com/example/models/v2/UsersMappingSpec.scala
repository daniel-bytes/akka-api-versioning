package com.example.models.v2

import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{ Matchers, WordSpec }

class UsersMappingV2Spec extends WordSpec with Matchers with ScalaFutures with ScalatestRouteTest {
  "UsersMapping" should {
    "with country of residence" should {
      val versionedModel = User(name = "Daniel Battaglia", age = 39, countryOfResidence = "USA")
      val standardModel = com.example.User(name = "Daniel Battaglia", age = 39, countryOfResidence = Some("USA"))

      "convert a versioned model to a standard model" in {
        UsersMapping.fromVersionedModel(versionedModel) shouldBe standardModel
      }

      "convert a standard model to a versioned model" in {
        UsersMapping.toVersionedModel(standardModel) shouldBe versionedModel
      }
    }

    "with empty country of residence" should {
      val versionedModel = User(name = "Daniel Battaglia", age = 39, countryOfResidence = "")
      val standardModel = com.example.User(name = "Daniel Battaglia", age = 39, countryOfResidence = None)

      "convert a versioned model to a standard model" in {
        UsersMapping.fromVersionedModel(versionedModel) shouldBe standardModel
      }

      "convert a standard model to a versioned model" in {
        UsersMapping.toVersionedModel(standardModel) shouldBe versionedModel
      }
    }

    "with null country of residence" should {
      val versionedModel = User(name = "Daniel Battaglia", age = 39, countryOfResidence = null)
      val standardModel = com.example.User(name = "Daniel Battaglia", age = 39, countryOfResidence = None)

      "convert a versioned model to a standard model" in {
        UsersMapping.fromVersionedModel(versionedModel) shouldBe standardModel
      }

      "convert a standard model to a versioned model" in {
        UsersMapping.toVersionedModel(standardModel) shouldBe versionedModel.copy(countryOfResidence = "")
      }
    }
  }
}
