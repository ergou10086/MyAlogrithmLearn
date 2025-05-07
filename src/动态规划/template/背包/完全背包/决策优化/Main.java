package 动态规划.template.背包.完全背包.决策优化;

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

        for (int i = 1; i <= n; i++)       //枚举物品
            for (int j = 1; j <= m; j++)     //枚举体积
                // 第i件物品不能放入背包
                if (j < wei[i]) dp[i][j] = dp[i - 1][j];
                // 第i件物品能放入背包，需要比较放入和不放入的代价
                // 对于前i件物品，由于背包容量j-w[i]的时候可能已经放入了第i件物品，容量为j的时候还可以放入，所以用f[i][j-w[i]]更新f[i][j]
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - wei[i]] + val[i]);

        System.out.println(dp[n][m]);

        scanner.close();
    }
}
