package au.id.tmm.bfect

import au.id.tmm.bfect.syntax.bifunctor._
import com.github.ghik.silencer.silent

@silent("not used")
class BifunctorSyntaxSpec[F[_, _] : Bifunctor] {

  {
    val testValue: F[Int, String] = ???

    testValue.biMap(i => i + 1, s => s.tail): F[Int, String]
    testValue.rightMap(s => s.tail): F[Int, String]
    testValue.map(s => s.tail): F[Int, String]
    testValue.leftMap(i => i + 1): F[Int, String]
    testValue.mapError(i => i + 1): F[Int, String]
    testValue.biAs(0x00, 'x'): F[Byte, Char]
    testValue.rightAs('x'): F[Int, Char]
    testValue.as('x'): F[Int, Char]
    testValue.unit: F[Int, Unit]
    testValue.leftAs(0x00): F[Byte, String]
    testValue.biWiden[Any, Any]: F[Any, Any]
    testValue.rightWiden[CharSequence]: F[Int, CharSequence]
    testValue.widen[CharSequence]: F[Int, CharSequence]
    testValue.leftWiden[Any]: F[Any, String]
  }

  {
    val testValue: F[Nothing, String] = ???

    testValue.rightMap(s => s.tail): F[Nothing, String]
    testValue.map(s => s.tail): F[Nothing, String]
    testValue.biWiden[Any, Any]: F[Any, Any]
    testValue.rightWiden[CharSequence]: F[Nothing, CharSequence]
    testValue.widen[CharSequence]: F[Nothing, CharSequence]
    testValue.leftWiden[Any]: F[Any, String]
    testValue.biAs(0x00, 'x'): F[Byte, Char]
    testValue.rightAs('x'): F[Nothing, Char]
    testValue.as('x'): F[Nothing, Char]
    testValue.unit: F[Nothing, Unit]
    testValue.leftAs(0x00): F[Byte, String]

    testValue.asThrowableFallible: F[Throwable, String]
    testValue.asExceptionFallible: F[Exception, String]
  }

  {
    val testValue: F[Int, Nothing] = ???

    testValue.leftMap(i => i + 1): F[Int, Nothing]
    testValue.mapError(i => i + 1): F[Int, Nothing]
    testValue.biAs(0x00, 'x'): F[Byte, Char]
    testValue.rightAs('x'): F[Int, Char]
    testValue.as('x'): F[Int, Char]
    testValue.unit: F[Int, Unit]
    testValue.leftAs(0x00): F[Byte, Nothing]
    testValue.biWiden[Any, Any]: F[Any, Any]
    testValue.rightWiden[CharSequence]: F[Int, CharSequence]
    testValue.widen[CharSequence]: F[Int, CharSequence]
    testValue.leftWiden[Any]: F[Any, Nothing]
  }

  {
    val testValue: F[Nothing, Nothing] = ???

    testValue.biWiden[Any, Any]: F[Any, Any]
    testValue.rightWiden[CharSequence]: F[Nothing, CharSequence]
    testValue.widen[CharSequence]: F[Nothing, CharSequence]
    testValue.leftWiden[Any]: F[Any, Nothing]
    testValue.biAs(0x00, 'x'): F[Byte, Char]
    testValue.rightAs('x'): F[Nothing, Char]
    testValue.as('x'): F[Nothing, Char]
    testValue.unit: F[Nothing, Unit]
    testValue.leftAs(0x00): F[Byte, Nothing]

    testValue.asThrowableFallible: F[Throwable, Nothing]
    testValue.asExceptionFallible: F[Exception, Nothing]
  }

}
