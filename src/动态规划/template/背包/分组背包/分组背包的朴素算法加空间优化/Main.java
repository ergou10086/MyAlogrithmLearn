package 动态规划.template.背包.分组背包.分组背包的朴素算法加空间优化;

import java.util.Scanner;

public class Main {
    static final int N = 110;
    // f[j] 表示容量为 j 的背包能装下物品的最大价值
    static int[] f = new int[N];
    // v[j] 表示物品的体积，w[j] 表示物品的价值
    static int[] v = new int[N];
    static int[] w = new int[N];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取物品组数 n 和背包容量 V
        int n = scanner.nextInt();
        int V = scanner.nextInt();

        // 遍历物品组
        for (int i = 1; i <= n; i++) {
            // 读取当前组物品的数量 s
            int s = scanner.nextInt();
            // 读取当前组每个物品的体积和价值
            for (int j = 1; j <= s; j++) {
                v[j] = scanner.nextInt();
                w[j] = scanner.nextInt();
            }

            // 逆序遍历背包容量，参考01，后更新
            for (int j = V; j >= 1; j--) {
                // 遍历当前组的每个物品
                for (int k = 0; k <= s; k++) {
                    // 若当前背包容量能装下该物品
                    if (k > 0 && j >= w[k]) {
                        f[j] = Math.max(f[j], f[j - v[k]] + w[k]);
                    }
                }
            }
        }

        System.out.println(f[V]);
        scanner.close();
    }
}
