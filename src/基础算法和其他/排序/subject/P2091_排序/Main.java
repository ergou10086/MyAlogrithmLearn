package 基础算法和其他.排序.subject.P2091_排序;

import java.util.*;

// 有 n 个物品，每一个物品有一个体积 V 和一个质量 M，在以物品体积为基础排序的时候，每次交换两个物品并且代价为两物品质量之和。求排好序之后的最小代价。

public class Main {
    static final int N = 200005;
    static long[] a = new long[N];
    static long[] vol = new long[N];
    static long[] wei = new long[N];
    static boolean[] book = new boolean[200005];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long n = scanner.nextLong();
        long minm = Long.MAX_VALUE;
        long ans = 0;

        for (int i = 1; i <= n; i++) {
            a[i] = scanner.nextLong();
            // 建立映射，体积i的索引为j
            vol[(int) a[i]] = i;
        }

        for (int i = 1; i <= n; i++) {
            // 在这里找出最小质量
            wei[i] = scanner.nextLong();
            minm = Math.min(minm, wei[i]);
        }

        for (int i = 1; i <= n; i++) {
            if (!book[(int) a[i]]) {
                int x = i;                    // 前正在处理的物品的索引
                long cnt = 0;                 // 当前循环中涉及的物品的数量。
                long sum = 0;                  // 当前循环中所有物品的质量之和。
                long minn = Long.MAX_VALUE;   // 当前循环中物品质量的最小值。

                while(!book[(int) a[x]]) {
                    book[(int) a[x]] = true;
                    sum += wei[(int) a[x]];
                    cnt++;
                    minn = Math.min(minn, wei[x]);
                    x = (int)a[x];
                }
                ans += Math.min(minn * (cnt - 2) + sum, minm * (cnt + 1) + sum + minn);
            }
        }
        System.out.println(ans);
        scanner.close();
    }
}
