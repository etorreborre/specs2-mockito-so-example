package unit.services.variation

import org.mockito.Matchers._
import org.specs2.concurrent.ExecutionEnv
import org.specs2.mock.Mockito
import play.api.cache.CacheApi
import play.api.test.PlaySpecification

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.reflect.ClassTag

/**
  * Created by BrianZ on 3/7/16.
  */
class VariationAssignmentSpec extends PlaySpecification with Mockito {

  case class Variation(id: Option[Long] = None)

  val v1 = Variation(Option(1L))
  val v2 = Variation(Option(2L))

  "Cache#getOrElse" should {
    "return correct result" in { implicit ee: ExecutionEnv =>

      val mockCache = mock[CacheApi]
      val classTag = implicitly[ClassTag[Future[Seq[Variation]]]]

      mockCache.getOrElse[Future[Seq[Variation]]](anyString,anyObject[Duration])(any[Future[Seq[Variation]]])(===(classTag)) returns Future(Seq(v1,v2))

      val resultFuture = mockCache.getOrElse[Future[Seq[Variation]]]("cache.key", 10.seconds)(Future(Seq(v1,v2)))

      resultFuture must equalTo(Future(Seq(v1,v2)))
    }
  }

}
