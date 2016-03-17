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
class VariationAssignmentSpec(implicit ee: ExecutionEnv) extends PlaySpecification with Mockito {

  case class Variation(id: Option[Long] = None)

  lazy val v1 = Variation(Option(1L))
  lazy val v2 = Variation(Option(2L))

  "Cache#getOrElse" should {
    "return correct result" in {

      val mockCache = mock[CacheApi]

      mockCache.getOrElse[Future[Seq[Variation]]](anyString, any[Duration])(any)(any) returns
        Future(Seq(v1, v2))

      val resultFuture: Future[Seq[Variation]] =
        mockCache.getOrElse("cache.key", 10.seconds)(Future(Seq(v1,v2)))

      resultFuture must equalTo(Seq(v1,v2)).await
    }
  }

}
