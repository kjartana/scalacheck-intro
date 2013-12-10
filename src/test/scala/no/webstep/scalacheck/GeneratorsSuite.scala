package no.webstep.scalacheck

import org.scalatest.FunSuite
import org.scalatest.prop.Checkers
import org.scalacheck.Prop._
import org.scalacheck.{Prop, Gen}

/**
 * @author Kjartan Aanestad - &lt;kjartan.aanestad@webstep.no&gt;
 */
class GeneratorsSuite extends FunSuite with Checkers {

  // http://www.scalatest.org/user_guide/writing_scalacheck_style_properties
//  implicit override val generatorDrivenConfig =
//    PropertyCheckConfig(minSize = 0, maxSize = 100, minSuccessful = 100, maxDiscarded = 500, workers = 1)

  test("Small integers") {
    val smallInteger = Gen.choose(0,100)

    check(Prop.forAll(smallInteger)(n => n >= 0 && n <= 100))
  }

  case class DateOfBirth(day: Int, month: Int, year: Int) {
    override def toString() = s"Born $day-$month-$year"
  }

  test("Generating case classes") {
    val birthDate = for {
      y <- Gen.choose(1920, 2013)
      m <- Gen.choose(1, 12)
      d <- Gen.choose(1, 31)
    } yield DateOfBirth(d, m, y)

    check(forAll(birthDate)(bd => bd.year >= 1920 && bd.year <= 2013))
  }

  test("Conditional Generators - Verify even integers") {
    val smallEvenInteger = Gen.choose(0,200) suchThat (_ % 2 == 0)
    check(forAll(smallEvenInteger)(i => i % 2 == 0))
  }

}
