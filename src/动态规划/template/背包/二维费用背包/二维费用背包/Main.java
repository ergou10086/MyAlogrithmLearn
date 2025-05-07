package 动态规划.template.背包.二维费用背包.二维费用背包;

// n件物品，容量为V，背包可承受的最大重量M
// 这种两个约束的01背包就是二位费用

import java.util.Scanner;

public class Main {
    static int n, V, W;    //物品 容量 承重
    static int v, w, val;  //体积 重量 价值

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // f[j,k]:前i个物品，体积≤j，重量≤k 的最大价值
        int[][] f = new int[110][110];

        for (int i = 1; i <= n; i++) {  //物品
            v = scanner.nextInt();
            w = scanner.nextInt();
            val = scanner.nextInt();

            for (int j = V; j >= v; j--) {
                for (int k = W; k >= w; k--) {
                    f[j][k] = Math.max(f[j][k], f[j - v][k - w] + val);
                }
            }
        }
        System.out.println(f[V][W]);
        scanner.close();
    }
}
