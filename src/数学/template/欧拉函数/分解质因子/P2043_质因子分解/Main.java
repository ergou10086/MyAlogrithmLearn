package 数学.template.欧拉函数.分解质因子.P2043_质因子分解;

// 算术基本定理（唯一分解定理）
// 每个正整数都能够唯一的表示成它的质因数的乘积
// n = p1^a1 * p2^a2....ps^as

// n中最多只含有一个大于根号n的因子（有两个那相乘不直接爆了？）

// 分解质因子
// 在2到sqrt(n)范围内枚举
// 遇到质因子就除净然后记录质因子个数
// 如果最后n>1，说明这个就是那个大于sqrt(n)的质因子

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions{
    private int n;
    private int[] a = new int[10001];   // 质因子个数

    private void decompose(int x){
        // 分解质因子,在2到sqrt(n)范围内枚举
        for(int i = 2; i * i <= x; i++){
            // 遇到质因子就除净
            while(x % i == 0) {
                a[i]++;
                x /= i;
            }
        }
        // 最后还大于1，那么这个也是质因子
        if(x > 1) a[x]++;
    }

    public Solutions() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for(int i = 2; i <= n; i++) decompose(i);
        for(int i = 1; i <= n; i++){
            if(a[i] != 0){
                System.out.println(i + " " + a[i]);
            }
        }
    }
}