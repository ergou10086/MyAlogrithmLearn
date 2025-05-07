package 动态规划.template.DP类.树形dp.P1122_最大子树和

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

class Solutions {
    companion object {
        private const val N = 16010
    }

    private val tree = MutableList(N + 1) { mutableListOf<Int>() }
    private val w = IntArray(N + 1)
    private val f = IntArray(N + 1)

    private fun dfs(u: Int, fa: Int) {
        f[u] = w[u]
        for (sp in tree[u]) {
            if (sp != fa) {
                dfs(sp, u)
                if (f[sp] > 0) {
                    f[u] += f[sp]
                }
            }
        }
    }

    init {
        val sc = FastReader()
        val n = sc.nextInt()

        for (i in 0..n) {
            tree.add(mutableListOf())
        }

        for (i in 1..n) {
            w[i] = sc.nextInt()
        }

        for (i in 0 until n - 1) {
            val u = sc.nextInt()
            val v = sc.nextInt()
            tree[u].add(v)
            tree[v].add(u)
        }

        dfs(1, 0)

        var ans = Int.MIN_VALUE
        for (i in 1..n) {
            ans = maxOf(ans, f[i])
        }

        println(ans)
    }

    private class FastReader {
        private val br = BufferedReader(InputStreamReader(System.`in`))
        private var st: StringTokenizer? = null

        fun next(): String {
            while (st == null || !st!!.hasMoreTokens()) {
                st = StringTokenizer(br.readLine())
            }
            return st!!.nextToken()
        }

        fun nextInt(): Int {
            return next().toInt()
        }
    }
}

fun main() {
    Solutions()
}