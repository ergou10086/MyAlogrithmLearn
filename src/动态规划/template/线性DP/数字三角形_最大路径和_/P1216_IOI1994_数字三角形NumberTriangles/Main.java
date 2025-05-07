package 动态规划.template.线性DP.数字三角形_最大路径和_.P1216_IOI1994_数字三角形NumberTriangles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] a = new int[1005][1005];
        int[][] dp = new int[1005][1005];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                a[i][j] = scanner.nextInt();
            }
        }

        // 动态规划：从底部向上计算
        for (int i = n; i >= 1; i--) {  // 从最后一行开始
            for (int j = 1; j <= i; j++) {   // 遍历当前行的每一列。
                // 状态转移方程：
                // dp[i][j] 表示从第 i 行第 j 列出发到底部的最大路径和
                // 选择下一行的左下方或右下方中较大的值，加上当前值
                dp[i][j] = Math.max(dp[i + 1][j], dp[i + 1][j + 1]) + a[i][j];
            }
        }

        // dp[1][1] 表示从顶部到底部的最大路径和
        System.out.println(dp[1][1]);
    }
}
