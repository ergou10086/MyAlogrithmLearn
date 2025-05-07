package 基础算法和其他.模拟.P11004_蓝桥杯2024省Python_B_魔法巡游

fun main() {
    val n = readLine()!!.toInt()

    val si = IntArray(n + 1)
    val ti = IntArray(n + 1)

    readLine()!!.split(" ").map { it.toInt() }.forEachIndexed { index, value ->
        si[index + 1] = value
    }

    readLine()!!.split(" ").map { it.toInt() }.forEachIndexed { index, value ->
        ti[index + 1] = value
    }

    var inp = 1
    var count = 0

    for (i in 1..n) {
        val temp = if (inp % 2 == 1) si[i] else ti[i]

        var flag = false
        if (temp == 0) {
            flag = true
        } else {
            var num = temp
            while (num > 0) {
                val digit = num % 10
                if (digit == 0 || digit == 2 || digit == 4) {
                    flag = true
                    break
                }
                num /= 10
            }
        }

        if (flag) {
            count++
            inp++
        }
    }

    println(count)
}