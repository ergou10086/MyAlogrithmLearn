package 数学.template.最大公约数.欧几里得算法.P1029_NOIP2001普及组_最大公约数和最小公倍数问题;

// 一组整数的公约数，是指每个整数的约数的交集
// 最大公约数就是里面最大的公约数
// 欧几里得算法也叫辗转相除
// 从两个数字入手，(a,b)和(b,a%b)的最大公约数（公约数）是相同的
// 递归，直到a%b==0，此时b为最大公约数

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long x = scanner.nextInt();   // gcd(p,q) = x
        long y = scanner.nextInt();  // lcm(p,q) = y
        // p * q = gcd(p,q) * lcm(p,q) = x * y

        // p*q积是一定的，枚举p就行
        long t = x * y;
        long ans = 0;
        for(long p = 1; p * p <= x * y; p++){
            if((t % p) == 0 && gcd(p, t / p) == x){
                ans += 2;
            }
        }
        if(x == y) ans--;
        System.out.println(ans);
    }

    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
