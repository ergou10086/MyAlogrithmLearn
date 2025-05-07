package 动态规划.template.背包.多重背包.滚动数组与决策优化;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] val = new int[n + 1];
        int[] wei = new int[n + 1];
        int[] s = new int[n + 1];  // 数量

        int[] dp = new int[210];

        for (int i = 1; i <= n; i++) {
            // 费用,价值,数量
            val[i] = scanner.nextInt();
            wei[i] = scanner.nextInt();
            s[i] = scanner.nextInt();
        }

        // 物品
        for(int i = 1; i <= n; i++) {
            // 体积，这里需要逆序枚举，因为是后更新
            for(int j = m; j >= wei[i]; j--) {
                // 数量，同时注意体积不能超
                for(int k = 0; k <= s[i] && k * wei[i] <= j; k++) {
                    // 滚动数组优化
                   dp[j] = Math.max(dp[j], dp[j - k * wei[i]] + k * val[i]);
                }
            }
        }

        System.out.println(dp[m]);

        scanner.close();
    }
}
