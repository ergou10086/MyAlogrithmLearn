package 动态规划.template.背包.多重背包.朴素多重背包;

// 多重背包
// 第i种物品可以取0到si件
// 转换为01背包求解

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] val = new int[n + 1];
        int[] wei = new int[n + 1];
        int[] s = new int[n + 1];  // 数量
        // 最大价值是dp[i][j]前i件物品放入容量为j的背包的最大价值的函数关系
        int[][] dp = new int[210][210];

        for (int i = 1; i <= n; i++) {
            // 费用,价值,数量
            val[i] = scanner.nextInt();
            wei[i] = scanner.nextInt();
            s[i] = scanner.nextInt();
        }

        // 物品
        for(int i = 1; i <= n; i++) {
            // 体积
            for(int j = 0; j <= m; j++) {
                // 数量
                for(int k = 0; k <= s[i] && k * wei[i] <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * wei[i]] + k * val[i]);
                }
            }
        }

        System.out.println(dp[n][m]);

        scanner.close();
    }
}
