package no.webstep.scalacheck

import org.scalatest.FunSuite
import org.scalatest.prop.Checkers
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.{Arbitrary, Gen}
import org.scalacheck.Prop._

/**
 * There is a special generator, org.scalacheck.Arbitrary.arbitrary,
 * which generates arbitrary values of any supported type
 *
 * @author Kjartan Aanestad - &lt;kjartan.aanestad@webstep.no&gt;
 */
class ArbitrarySuite extends FunSuite with Checkers {

  case class Person(name: String, age: Int)

  val personGen = for {
    n <- arbitrary[String]
    a <- Gen.choose(1, 100)
  } yield Person(n, a)

  // To get support for your own type T you need to define an
  // implicit def or val of type Arbitrary[T]
  implicit val arbitraryPerson: Arbitrary[Person] = Arbitrary(personGen)

  test("Verify person age") {
    check(forAll(personGen)((p: Person) => p.age > 0))
  }

  test("Verify person age using implicit arbitrary") {
    check(forAll((p: Person) => p.age > 0))
  }
}
