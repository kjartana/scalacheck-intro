package no.webstep.scalacheck

import org.scalatest.FunSuite
import org.scalatest.prop.Checkers
import no.webstep.scalacheck.props.GroupingProperties
import org.scalacheck.Prop._
import org.scalacheck.Gen

/**
 * A property is the testable unit in ScalaCheck, and is represented by the org.scalacheck.Prop class
 *
 * @author Kjartan Aanestad - &lt;kjartan.aanestad@webstep.no&gt;
 */
class PropertiesSuite extends FunSuite with Checkers {

  // http://www.scalatest.org/user_guide/writing_scalacheck_style_properties
//  implicit override val generatorDrivenConfig =
//    PropertyCheckConfig(minSize = 0, maxSize = 100, minSuccessful = 100, maxDiscarded = 500, workers = 1)

  test("Verify concat and size") {
    val propConcatLists = forAll { (l1: List[Int], l2: List[Int]) =>
      l1.size + l2.size == (l1 ::: l2).size
    }
    check(propConcatLists)
//    check((a: List[Int], b: List[Int]) => a.size + b.size == (a ::: b).size)
  }

  test("Verify reverse") {
    val propReverseList = forAll { l: List[String] => l.reverse.reverse == l }
    check(propReverseList)
  }

  test("Verify concat string") {
    val propConcatString = forAll { (s1: String, s2: String) =>
      (s1 + s2).endsWith(s2)
    }
    check(propConcatString)
  }

  test("Verify grouped properties") {
    check(new GroupingProperties)
  }

  test("Verify string properties collecting") {
    val ages = forAll(Gen.choose(1, 10)) { n =>
      classify(n <= 5, "large", "small") {
        n <= 10
      }
    }
//    ages.check
    check(ages)
  }
}
