package 动态规划.template.背包._01背包.P2871_USACO07DEC_CharmBraceletS;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] val = new int[n + 1];
        int[] wei = new int[n + 1];
        // dp[i][j]表示前i件物品放入容量为j的最大价值
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            wei[i] = sc.nextInt();
            val[i] = sc.nextInt();
        }

        for (int i = 1; i <= n; i++) {    // 枚举物品
            for (int j = 1; j <= m; j++) {    // 枚举体积
                // 放不进去
                if (j < wei[i]) dp[i][j] = dp[i - 1][j];
                // 放就要减去体积加上价值，同时比较不放入时候是否价值更大
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - wei[i]] + val[i]);
            }
        }

        System.out.println(dp[n][m]);

        sc.close();
    }
}
