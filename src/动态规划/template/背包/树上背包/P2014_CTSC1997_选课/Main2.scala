package 动态规划.template.背包.树上背包.P2014_CTSC1997_选课

import scala.collection.mutable

object Main2 {
  val N = 305
  var n: Int = 0
  var m: Int = 0
  val e: Array[mutable.ListBuffer[Int]] = Array.fill(N)(mutable.ListBuffer.empty[Int])
  val w: Array[Int] = new Array[Int](N)
  val f: Array[Array[Int]] = Array.ofDim[Int](N, N)
  val siz: Array[Int] = new Array[Int](N)

  def dfs(u: Int): Unit = {
    f(u)(1) = w(u)
    siz(u) = 1
    for (v <- e(u)) {
      dfs(v)
      siz(u) += siz(v)
      for (j <- Math.min(m + 1, siz(u)) to 1 by -1) {
        for (k <- 0 to Math.min(j - 1, siz(v))) {
          f(u)(j) = Math.max(f(u)(j), f(u)(j - k) + f(v)(k))
        }
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val scanner = new java.util.Scanner(System.in)
    n = scanner.nextInt()
    m = scanner.nextInt()

    for (i <- 1 to n) {
      val k = scanner.nextInt()
      w(i) = scanner.nextInt()
      e(k) += i
    }

    dfs(0)
    println(f(0)(m + 1))
    scanner.close()
  }
}