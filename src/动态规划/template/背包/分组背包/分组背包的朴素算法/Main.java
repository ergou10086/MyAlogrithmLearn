package 动态规划.template.背包.分组背包.分组背包的朴素算法;

// n组物品和一个容量是V的背包
// 每组物品若干个，同一组内最多能选一个，然后求最大价值

import java.util.Scanner;

public class Main {
    static final int N = 110;
    // v[i][j] 表示第 i 组第 j 个物品的体积，w[i][j] 表示第 i 组第 j 个物品的价值，s[i] 表示第 i 组物品的个数
    static int[][] v = new int[N][N];
    static int[][] w = new int[N][N];
    static int[] s = new int[N];
    // f[i][j] 表示前 i 组物品，能放入容量为 j 的背包的最大值
    static int[][] f = new int[N][N];

    public static void main(String[] args) {
        // 最大价值应该是物品组i和背包容量j的函数
        // 用f[i][j]表示前i组物品，能放入容量为j的背包的最大价值
        // 循环物品组，循环背包容量，对第i组物品，容量为j的背包，s+1种选法

        Scanner scanner = new Scanner(System.in);
        // 物品组数 n 和背包容量 V
        int n = scanner.nextInt();
        int V = scanner.nextInt();

        // 读取每组物品的信息
        for (int i = 1; i <= n; i++) {
            // 读取第 i 组物品的个数
            s[i] = scanner.nextInt();
            for (int j = 1; j <= s[i]; j++) {
                // 读取第 i 组第 j 个物品的体积和价值
                v[i][j] = scanner.nextInt();
                w[i][j] = scanner.nextInt();
            }
        }

        for (int i = 1; i <= n; i++) { // 遍历物品组
            for (int j = 1; j <= V; j++) { // 遍历背包体积
                for(int k = 0; k <= s[i]; k++){  // 遍历同组内的物品
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - v[i][k]] + w[i][k]);
                }
            }
        }

        System.out.println(f[n][V]);
        scanner.close();
    }
}
