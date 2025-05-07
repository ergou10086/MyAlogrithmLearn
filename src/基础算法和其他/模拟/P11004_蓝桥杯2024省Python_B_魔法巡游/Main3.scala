package 基础算法和其他.模拟.P11004_蓝桥杯2024省Python_B_魔法巡游

object Main3 {
  def check(num: Int): Boolean = {
    if (num == 0) return true
    var n = num
    while (n > 0) {
      val digit = n % 10
      if (digit == 0 || digit == 2 || digit == 4) return true
      n /= 10
    }
    false
  }

  def main(args: Array[String]): Unit = {
    val n = scala.io.StdIn.readInt()
    val si = Array.ofDim[Int](n + 1)
    val ti = Array.ofDim[Int](n + 1)

    for (i <- 1 to n) si(i) = scala.io.StdIn.readInt()
    for (i <- 1 to n) ti(i) = scala.io.StdIn.readInt()

    var (inp, count) = (1, 0)
    for (i <- 1 to n) {
      val temp = if (inp % 2 == 1) si(i) else ti(i)
      if (check(temp)) {
        count += 1
        inp += 1
      }
    }
    println(count)
  }
}
