/**
  *    Copyright 2019 Timothy McCarthy
  *
  *    Licensed under the Apache License, Version 2.0 (the "License");
  *    you may not use this file except in compliance with the License.
  *    You may obtain a copy of the License at
  *
  *        http://www.apache.org/licenses/LICENSE-2.0
  *
  *    Unless required by applicable law or agreed to in writing, software
  *    distributed under the License is distributed on an "AS IS" BASIS,
  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  *    See the License for the specific language governing permissions and
  *    limitations under the License.
  */
package au.id.tmm.bfect.effects

import java.time.Instant

trait Now[F[_, _]] {

  def now: F[Nothing, Instant]

}

object Now extends NowStaticOps {

  def apply[F[_, _] : Now]: Now[F] = implicitly[Now[F]]

}

trait NowStaticOps {
  def now[F[_, _] : Now]: F[Nothing, Instant] = Now[F].now
}
