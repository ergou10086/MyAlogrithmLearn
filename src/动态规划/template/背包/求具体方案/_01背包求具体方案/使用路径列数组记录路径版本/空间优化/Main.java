package 动态规划.template.背包.求具体方案._01背包求具体方案.使用路径列数组记录路径版本.空间优化;

import java.util.Scanner;

public class Main {
    // 定义常量 N，类似于 C++ 中的 const int N = 1010;
    private static final int N = 1010;
    // 定义物品的体积数组
    private static int[] v = new int[N];
    // 定义物品的价值数组
    private static int[] w = new int[N];
    // 定义动态规划数组，用于存储最大价值，压缩一维
    private static int[] f = new int[N];
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

        // 逆序取物，进行动态规划
        for (int i = n; i >= 1; i--) {
            // 枚举体积
            for (int j = m; j >= 0; j--) {
                // 记录路径列
                p[i][j] = j;
                // 如果当前背包容量可以放下当前物品
                if (j >= v[i]) {
                    f[j] = Math.max(f[j], f[j - v[i]] + w[i]);
                }
                // 如果当前背包容量可以放下当前物品且恰好是由该物品转移过来的，选择该物品
                if (j >= v[i] && f[j - v[i]] + w[i] == f[j]) {
                    p[i][j] = j - v[i];
                }
            }
        }

        // 初始化背包容量
        int j = m;
        // 遍历物品，输出选择的物品编号
        for (int i = 1; i <= n; i++) {
            if (p[i][j] < j) {
                System.out.print(i + " ");
                j = p[i][j];
            }
        }
        scanner.close();
    }
}