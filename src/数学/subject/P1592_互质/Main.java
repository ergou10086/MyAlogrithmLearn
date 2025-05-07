package 数学.subject.P1592_互质;

import javax.security.auth.Subject;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    // 与 n 互质的第 k 个正整数。
    // 比如和10互质的数
    // 1,3,7,9,11,13,17,19,21,23,27,29
    // 我们可以把它们分成好几部分
    // 1,3,7,9,   11,13,17,19,   21,23,27,29
    // 他们的个位数都是一样的

    // 欧拉函数 φ(n) 表示一个周期的长度
    // nx 到 n(x+1) 区间内，与n互质的树的个数等于 小于n的数中与 φ(n) 互质的个数，x为n的任意倍数
    // 设 gcd(a, b) = c，那么存在互质的 m 和 n，使得 a = mc，b = nc
    // 此时 a + b = (m + n)c。
    // 因为 m 和 n 互质，意味着 m 和 n 没有大于 1 的共同因子
    // 根据质因数分解的原理，m * n 的因子是 m 的因子与 n 的因子的并集，所以 m * n 同样和 m、n 没有大于 1 的共同因子，进而可以推出 m + n 和 m 也是互质的。
    // 由此可得 gcd(a, a + b) = c = gcd(a, b)，进一步推广可以得到 gcd(a, ax + b) = c = gcd(a, b)（这里 x 表示 a 的倍数，在求最大公约数时 mod 运算会把 x 这个倍数的影响消掉）。
    // 基于这个性质，就可以得出在 ax ~ a(x + 1) 这个区间内
    // 与 a 互质的个数等于小于 a 的数中与 a 互质的个数 φ(a)，也就有了周期性的规律
    // 即在每个这样的区间内都有 φ(a) 个与 a 互质的数，存在第 φ(a) * x ~ φ(a) * (x + 1) 个与 a 互质的数。
    static int gcd(int x, int y) {
        return y == 0? x : gcd(y, x % y);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] a = new int[1000007];   // 一个周期内（也就是小于 n）与 n 互质的数
        int res = 0, cnt = 0;    // 有效长度就是φ(n)，cnt记录

        // 判断每个小于 n 的数 i 是否与 n 互质，如果互质就将其存储到数组 a 中，同时记录下与 n 互质且小于 n 的数的个数 cnt
        for(int i = 1; i <= n; i++){
            if(gcd(i, n) == 1){
                a[++cnt] = i;
            }
        }
        // (k - 1) / cnt，周期长度除前k个数，下标减一，乘n定位到第 k 个数所在的完整周期的起始位置
        // a[(k - 1) % cnt + 1]确定在这个周期内具体是哪个数，(k - 1)%cnt 得到的是在当前周期内的偏移量
        res = (k - 1) / cnt * n + a[(k - 1) % cnt + 1];
        System.out.println(res);
        scanner.close();
    }
}
