package au.id.tmm.bifunctorio.instances

import au.id.tmm.bifunctorio.IO
import au.id.tmm.bifunctorio.typeclasses.BiFunctor

class BiFunctorInstance private[instances] () extends BiFunctor[IO] {
  override def biMap[L1, R1, L2, R2](f: IO[L1, R1])(leftF: L1 => L2, rightF: R1 => R2): IO[L2, R2] =
    f.biMap(leftF, rightF)

  override def rightMap[L, R1, R2](f: IO[L, R1])(rightF: R1 => R2): IO[L, R2] =
    f.map(rightF)

  override def leftMap[L1, R, L2](f: IO[L1, R])(leftF: L1 => L2): IO[L2, R] =
    f.leftMap(leftF)
}