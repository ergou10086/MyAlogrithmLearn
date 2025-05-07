package 动态规划.template.背包.完全背包.朴素算法;

// 完全背包一般是物品有无限多件，然后求最大价值
// 01背包中第i件物品可以放入0个或1个
// 而完全背包可以放入任意数个

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] val = new int[n + 1];
        int[] wei = new int[n + 1];
        // 最大价值是dp[i][j]前i件物品放入容量为j的背包的最大价值的函数关系
        int[][] dp = new int[1010][1010];

        for (int i = 1; i <= n; i++) {
            wei[i] = scanner.nextInt();
            val[i] = scanner.nextInt();
        }

        for (int i = 1; i <= n; i++)       //阶段：枚举物品
            for (int j = 0; j <= m; j++)       //状态：枚举体积
                for (int k = 0; k <= j / wei[i]; k++)  //决策：枚举个数
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - wei[i] * k] + val[i] * k);

        System.out.println(dp[n][m]);

        scanner.close();
    }
}
