package 动态规划.subject.线性dp.P10914_蓝桥杯2024国B_跳石头

import scala.collection.mutable.BitSet

class Main3 {
  // 定义常量 MAXN
  val MAXN = 40001

  def main(args: Array[String]): Unit = {
    import java.util.Scanner
    val sc = new Scanner(System.in)
    // 存储每个石头的权值
    val c = new Array[Int](MAXN)
    // 用于标识每个石头能否跳到的 BitSet 数组
    val bs = Array.fill(MAXN)(BitSet(MAXN))

    // 读取石头的数量
    val n = sc.nextInt()

    // 读取每个石头的权值
    for (i <- 1 to n) {
      c(i) = sc.nextInt()
    }

    var ans = 0
    // 从后往前遍历每个位置
    for (i <- n to 1 by -1) {
      // 标记当前石头的状态
      bs(i) += c(i)

      // 跳向第 i + c(i) 块石头，合并状态
      if (i + c(i) <= n) {
        bs(i) |= bs(i + c(i))
      }

      // 跳向第 2 * i 块石头，合并状态
      if (2 * i <= n) {
        bs(i) |= bs(2 * i)
      }

      // 更新最大能跳到的石头数量
      ans = Math.max(ans, bs(i).size)
    }

    // 输出结果
    println(ans)
    sc.close()
  }
}
