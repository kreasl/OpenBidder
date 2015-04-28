import org.scalacheck.Properties
import org.scalacheck.Prop.forAll


object Doubler extends Properties("Demo") {

  property("myprop") = forAll { l: List[Int] =>
    l.reverse.reverse == l
  }

}