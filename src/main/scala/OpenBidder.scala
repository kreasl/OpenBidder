/**
 * Created by Yury Talapila on 28.4.15.
 */

object Doubler {
  def apply(x: Int) = x * 2
}

object OpenBidder extends App {
  println("Hello, OpenBider fans!")

  if (args.length > 0) {
    val doubled = Doubler(args(0).toInt)
    println(s"Your first argument doubled is $doubled")
  }
}
