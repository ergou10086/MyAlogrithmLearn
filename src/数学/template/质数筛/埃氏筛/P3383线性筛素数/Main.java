package 数学.template.质数筛.埃氏筛.P3383线性筛素数;

import java.util.Scanner;

public class Main {
    private static final int N = 100000010;
    private static boolean[] vis = new boolean[N];   // 划掉合数
    private static int[] prim = new int[N];  // 记录质数
    private static int cnt;    // 质数个数

    private static void Eratosthenes(int n){
        for(int i = 2; i <= n; i++){
            // 如果不是质数，进入
            if(!vis[i]){
                // 标记为质数
                prim[++cnt] = i;
                // 把i从i*i开始把i的倍数都标记为质数
                for(long j = i * i; j <= n; j += i){
                    vis[(int) j] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int q = scanner.nextInt();

        // 调用埃氏筛法
        Eratosthenes(n);

        while (q-- > 0) {
            int k = scanner.nextInt();
            System.out.println(prim[k]);
        }
        scanner.close();
    }
}
