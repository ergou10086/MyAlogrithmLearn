package 动态规划.template.背包.完全背包.决策优化加优化空间;

/*
题目描述
有种重量和价值分别为wi、vi（）的物品，每种物品可以挑选任意多件。从这些物品中挑选总重量不超过V的物品，求出挑选物品价值总和最大的挑选方案，输出任意一组方案即可。物品的编号范围是 1…N。

输入格式
第一行两个整数，N，V，用空格隔开，分别表示物品数量和背包容积。
接下来有  N 行，每行两个整数vi ，wi用空格隔开，分别表示第 i 件物品的价值和体积。

输出格式
输出第一行，包含一个整数，表示最大价值和。
接下来若干行，每行输出两个整数和i，k表示物品i挑选k件。若有多种解，输出任意一组即可。
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] val = new int[n + 1];
        int[] wei = new int[n + 1];
        // 用一维数组只记录一行数据，让j顺序循环顺序更新，滚动掉一维
        int[] dp = new int[1010];

        for (int i = 1; i <= n; i++) {
            wei[i] = scanner.nextInt();
            val[i] = scanner.nextInt();
        }

        for (int i = 1; i <= n; i++) {   // 枚举物品
            // 顺序枚举j，新数值f[j-w[i]]会先于f[j]更新，是正确的
            for (int j = wei[i]; j <= m; j++) {  // 枚举容量
                dp[j] = Math.max(dp[j], dp[j - wei[i]] + val[i]);
            }
        }

        System.out.println(dp[m]);

        scanner.close();
    }
}
