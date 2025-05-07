package 数学.template.欧拉函数.试除法求欧拉函数;

// 欧拉函数
// 1-n中与n互质的数的个数称为欧拉函数，记录为phi(n)
// 例如 phi(5) = 4

// 欧拉函数的性质
// 若p是质数，则phi(p) = p - 1
// 若p是质数,则phi(p^k) = (p - 1)p^(k-1)

// 欧拉函数的计算公式，由唯一分解定理推到
// 唯一分解定理指出，任何大于1的整数 n 都可以唯一地表示为若干素数的幂的乘积
// n = p1^k1*p2^k2*⋯*pm^km    (p1,p2为不同的质数)

// 欧拉函数的计算公式
// ϕ(n) = n(1−1/p1)(1−1/p2)⋯(1−1/pm)
// 基本性质
//  若 n 是素数 p，则 ϕ(p)=p−1，因为除了 p 本身，所有小于 p 的正整数都与 p 互质。
//  若 n=p^k，其中 p 是素数，则 ϕ(p^k)=p^k−p^(k−1)，因为只有 p 的倍数不与 p^k 互质。
//  欧拉函数是积性函数，即若 a 和 b 互质（gcd(m,n)=1），则 ϕ(ab)=ϕ(a)ϕ(b)

// 唯一分解定理应用
// 将 n 分解为素数幂的乘积：n = p1^k1*p2^k2*⋯*pm^km
// 根据积性函数性质：ϕ(n)=ϕ(p1^k1)ϕ(p2^k2)⋯ϕ(pm^km)
// 带入ϕ(pi^ki) = pi^ki - pi^(ki-1)
// 有 ϕ(n)= i=1 ∏ m (pi^ki−pi^(ki−1)) = i=1 ∏ m pi^ki * (1 - 1 / pi)
// n = i=1 ∏ m pi^ki 最终得到
// ϕ(n) = n * i=1 ∏ m（1-1/pi）

// 例如计算 ϕ(12)
// 分解 12 为素数幂：12=2^2×3^1
// ϕ(12) = 12 * (1 - 1/2) * (1 - 1/3)

// 也就是说，求欧拉函数
// 需要先用唯一分解定理求出分解质数
// 然后n*求连乘积的一减去倒数

import java.util.Scanner;

public class 筛法求欧拉函数 {
    public static void main(String[] args) {

    }
}

class Solutions {
    // 试除法求欧拉函数
    private int phi(int n) {
        int res = n;
        // 逐个尝试是否能整除 n。如果 i 是 n 的质因数，则更新 res 并去掉 n 中的所有 i 因子。
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                res = res / i * (i - 1);   // res = n，所以 n(1-1/pi) = n((pi-1)/pi) = n*(pi-1)/pi，对应欧拉函数连乘积那一步
                while (n % i == 0) n /= i;   // 确保 i 的所有幂次都被去掉，避免重复计算
            }
        }
        if (n > 1) res = res / n * (n - 1);   // 如果循环结束后 n>1，说明 n 本身是一个质数，对应欧拉函数公式中的最后一个质因数
        return res;
    }

    public Solutions() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int res = phi(n);
        System.out.println(res);
    }
}