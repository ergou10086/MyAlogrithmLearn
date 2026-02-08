package 动态规划.subject.背包.P5365_SNOI2017_英雄联盟;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long[] dp = new long[1000001];
        long[] k = new long[1000001];  // k[i]：第i种皮肤的可购买数量
        long[] c = new long[1000001];  // c[i]：第i种皮肤的单价（每个需要的Q币数）
        long n, m, qb = 0;             // n：皮肤种类数，m：目标值，qb：Q币总量（所有皮肤买满的总Q币）

        n = scanner.nextLong();
        m = scanner.nextLong();

        for (int i = 1; i <= n; i++) {
            k[i] = scanner.nextLong();
        }

        for (int i = 1; i <= n; i++) {
            c[i] = scanner.nextLong();
            qb += c[i] * k[i]; // 总Q币 = 单价 * 数量 求和（所有皮肤买满的情况）
        }

        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (long j = qb; j >= 0; j--){
                for (long p = 0; p <= k[i] && p * c[i] <= j; p++) {
                    dp[(int) j] = Math.max(dp[(int) j], dp[(int) (j - p * c[i])] * p);
                }
            }
        }

        // 找到最小的Q币数ans，使得dp[ans] ≥ m
        long ans = 0;
        while (ans <= qb && dp[(int) ans] < m) {
            ans++;
        }

        System.out.println(ans);
        scanner.close();
    }
}
