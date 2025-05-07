package 动态规划.template.背包.完全背包.举例P1616疯狂的采药;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[] val = new int[n + 1];
        int[] wei = new int[n + 1];
        long[] dp = new long[m + 1];

        for (int i = 1; i <= n; i++) {
            wei[i] = scanner.nextInt();
            val[i] = scanner.nextInt();
        }

        for (int i = 1; i <= n; i++) {
            for (int j = wei[i]; j <= m; j++) {
                dp[j] = Math.max(dp[j], dp[j - wei[i]] + val[i]);
            }
        }

        System.out.println(dp[m]);

        scanner.close();
    }
}
