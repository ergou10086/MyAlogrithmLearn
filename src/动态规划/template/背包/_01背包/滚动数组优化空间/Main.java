package 动态规划.template.背包._01背包.滚动数组优化空间;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] val = new int[n + 1];
        int[] wei = new int[n + 1];
        // 滚动数组优化
        // dp[i & 1][j] 表示当前行的状态。
        // dp[i-1 & 1][j] 表示上一行的状态。
        int[][] dp = new int[2][m + 1];

        for (int i = 1; i <= n; i++) {
            wei[i] = sc.nextInt();
            val[i] = sc.nextInt();
        }

        for (int i = 1; i <= n; i++) {
            // 逆序循环，用旧值f[j-w[i]]更新f[j]，相当于用上一行的取更新
            for (int j = 1; j <= m; j++) {   // 背包容量逆序循环，避免顺序循环时候f[j-w[i]]先于f[j]更新
                if (j < wei[i]) {
                    dp[i & 1][j] = dp[i - 1 & 1][j];
                } else {
                    dp[i & 1][j] = Math.max(dp[i - 1 & 1][j], dp[i - 1 & 1][j - wei[i]] + val[i]);
                }
            }
        }

        System.out.println(dp[n & 1][m]);

        sc.close();
    }
}
