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
package au.id.tmm.bfect.effects.extra

import au.id.tmm.bfect.BMonad
import au.id.tmm.bfect.effects.Sync

trait EnvVars[F[_, _]] {
  def envVars: F[Nothing, Map[String, String]]

  def envVar(key: String): F[Nothing, Option[String]]

  def envVarOrError[E](key: String, onMissing: => E): F[E, String]
}

object EnvVars {

  def apply[F[_, _] : EnvVars]: EnvVars[F] = implicitly[EnvVars[F]]

  trait WithBMonad[F[_, _]] extends EnvVars[F] { self: BMonad[F] =>
    override def envVar(key: String): F[Nothing, Option[String]] =
      map[Nothing, Map[String, String], Option[String]](envVars)(_.get(key))

    override def envVarOrError[E](key: String, onMissing: => E): F[E, String] =
      flatMap(envVars)(_.get(key).fold[F[E, String]](leftPure(onMissing))(rightPure))
  }

  trait Live[F[_, _]] extends EnvVars.WithBMonad[F] { self: Sync[F] =>
    override def envVars: F[Nothing, Map[String, String]] = sync(sys.env)
  }

}
