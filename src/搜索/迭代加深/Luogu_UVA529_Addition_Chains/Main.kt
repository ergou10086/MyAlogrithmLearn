package 搜索.迭代加深.Luogu_UVA529_Addition_Chains

import java.util.Scanner

// 全局变量 n 表示目标值，d 表示搜索深度
var n = 0
var d = 0
// 数组 a 用于存储加成序列
val a = IntArray(10005)

// 深度优先搜索函数，搜索第 u 层
fun dfs(u: Int): Boolean {
    // 如果当前搜索深度达到最大深度 d
    if (u == d) {
        // 判断最后一个元素是否等于目标值 n
        return a[u - 1] == n
    }
    // 优化搜索顺序，从 u - 1 开始往前遍历
    for (i in u - 1 downTo 0) {
        // 计算新的可能元素
        val t = a[u - 1] + a[i]
        // 越界剪枝，如果新元素大于目标值 n，跳过
        if (t > n) {
            continue
        }
        // 将新元素存入数组 a
        a[u] = t
        // 估价未来，如果未来即使每次都翻倍也无法达到目标值 n，剪枝
        var temp = t
        for (j in u + 1..d) {
            temp *= 2
        }
        if (temp < n) {
            return false
        }
        // 递归搜索下一层
        if (dfs(u + 1)) {
            return true
        }
    }
    return false
}

fun main() {
    val scanner = Scanner(System.`in`)
    // 加成序列的第一个元素为 1
    a[0] = 1
    while (true) {
        // 读取输入的目标值 n
        n = scanner.nextInt()
        if (n == 0) {
            break
        }
        // 初始搜索深度为 1
        d = 1
        // 如果搜索失败，增加搜索深度
        while (!dfs(1)) {
            d++
        }
        // 输出加成序列
        for (i in 0 until d) {
            print("${a[i]} ")
        }
        println()
    }
}