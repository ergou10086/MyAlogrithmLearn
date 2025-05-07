package 动态规划.template.背包.求具体方案._01背包求具体方案;

// 背景还是01背包
// 输出用所选物品的编号序列，且字典序最小

// 题目要求输出字典序最小的方案
// 假设存在一个包含第一个物品的最优解，为了确保字典序最小那么我们必然要选第一个
// 然后问题就变成了从2-N这些物品中找到最优解
// 从后向前遍历物品，让最优解落在f[1][m]中
// 然后从f[1][m]开始搜索字典序最小的路径方案

// f[i][j]表示从第i歌舞片到最后一个物品装入容量为j的背包的最优解
// f[i][j] = max(f[i + 1][j], f[i + 1][j - w] + v)  因为是逆序，所以-变+

import java.util.Scanner;

public class Main {
    private static final int N = 1010;
    // 定义物品的体积数组
    private static int[] v = new int[N];
    // 定义物品的价值数组
    private static int[] w = new int[N];
    // 定义动态规划数组，用于存储最大价值
    private static int[][] f = new int[N][N];
    // 定义路径数组，用于记录状态转移的路径
    private static int[][] p = new int[N][N];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取物品的数量 n 和背包的容量 m
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // 读取每个物品的体积和价值
        for (int i = 1; i <= n; i++) {
            v[i] = scanner.nextInt();
            w[i] = scanner.nextInt();
        }

        // 逆序取物
        for (int i = n; i >= 1; i--) {
            // 枚举体积
            for (int j = 0; j <= m; j++) {
                f[i][j] = f[i + 1][j];
                if(j >= v[i]){
                    f[i][j] = Math.max(f[i + 1][j], f[i + 1][j - v[i]] + w[i]);
                }
            }
        }

        // 计算完状态后，f[1][m]就是最大价值，从f[1][m]开始搜索
        // f[i][j]的正常两种选不选的情况都确定
        // f[i][j] = f[i + 1][j] = f[i + 1][j - v[i]] + w[i]时候，为了确保字典序最小，所以要选该物品，（逆序枚举）
        // 如果选了第i个物品，那么f[i][j]一定能通过f[i + 1][j - v[i]] + w[i] 转移得到

        // 剩余容量为m
        int j = m;
        for(int i = 1; i <= n; i++){
            if(j >= v[i] && f[i][j] == f[i + 1][j - v[i]] + w[i]){
                System.out.print(i + " ");
                j -= v[i];     // 选了这个物品，剩余容量要减少
            }
        }
    }
}
