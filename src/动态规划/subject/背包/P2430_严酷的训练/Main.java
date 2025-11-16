package 动态规划.subject.背包.P2430_严酷的训练;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 读取WKY和老王的水平值
        int a = sc.nextInt();
        int b = sc.nextInt();

        // 读取题目总数和知识点总数
        int m = sc.nextInt();
        int n = sc.nextInt();

        // 存储每个题目的知识点和奖励值
        int[] p = new int[m + 1];  // 知识点
        int[] q = new int[m + 1];  // 奖励值

        // 存储老王和WKY做各知识点题目的时间
        int[] t_lw = new int[n + 1];  // 老王的时间
        int[] t_wky = new int[n + 1];  // WKY的时间（计算得出）
        int ratio = b / a;  // 时间比例系数

        for (int i = 1; i <= n; i++) {
            t_lw[i] = sc.nextInt();
            t_wky[i] = t_lw[i] * ratio;  // 计算WKY的耗时
        }

        for (int i = 1; i <= m; i++) {
            p[i] = sc.nextInt();
            q[i] = sc.nextInt();
        }

        // 读取规定的总时间
        int t = sc.nextInt();

        // dp数组：dp[j]表示时间不超过j时的最大奖励值
        int[] dp = new int[t + 1];

        // 01背包核心逻辑
        for(int i = 1; i <= m; i++){
            // 逆序遍历时间，避免重复选择同一题目
            for(int j = t; j >= t_wky[p[i]]; j--){
                dp[j] = Math.max(dp[j], dp[j - t_wky[p[i]]] + q[i]);
            }
        }

        // 输出最大奖励值
        System.out.println(dp[t]);

        sc.close();
    }
}
