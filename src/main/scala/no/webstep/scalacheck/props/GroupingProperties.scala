package no.webstep.scalacheck.props

import org.scalacheck.{Gen, Properties}
import org.scalacheck.Prop._

/**
 * @author Kjartan Aanestad - &lt;kjartan.aanestad@webstep.no&gt;
 */
class GroupingProperties extends Properties("String") {

  property("startsWith") = forAll { (a: String, b: String) =>
    (a+b).startsWith(a)
  }

  property("endsWith") = forAll { (a: String, b: String) =>
    (a+b).endsWith(b)
  }

  property("substring") = forAll { (a: String, b: String) =>
    (a+b).substring(a.length) == b
  }

  property("substring") = forAll { (a: String, b: String, c: String) =>
    (a+b+c).substring(a.length, a.length+b.length) == b
  }

}
