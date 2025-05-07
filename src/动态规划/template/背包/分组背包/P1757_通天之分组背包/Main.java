package 动态规划.template.背包.分组背包.P1757_通天之分组背包;

import java.util.Scanner;

public class Main {
    static int N = 1010;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // v[i,j]:第i组第j个物品的体积 w价值 s[i]:第i组物品的个数
        int[][] v = new int[N][N];
        int[][] w = new int[N][N];
        int[] s = new int[N];
        // f[i,j]:前i组物品，能放入容量为j的背包的最大值，滚动一维
        int[] f = new int[N];
        int n = scanner.nextInt();
        int V = scanner.nextInt();
        // 记录组数
        int maxx = 0;
        // 修正：将 i < n 改为 i <= n
        for (int i = 1; i <= n; i++) {
            // 重量，利用价值，所属组数
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            v[c][++s[c]] = a;
            w[c][s[c]] = b;
            maxx = Math.max(maxx, c);
        }

        for (int i = 1; i <= maxx; i++) {   // 组
            for (int j = V; j >= 1; j--) {   // 体积
                for (int k = 1; k <= s[i]; k++) {  // 个数
                    if (j >= v[i][k]) {
                        f[j] = Math.max(f[j], f[j - v[i][k]] + w[i][k]);
                    }
                }
            }
        }

        System.out.println(f[V]);
        scanner.close();
    }
}