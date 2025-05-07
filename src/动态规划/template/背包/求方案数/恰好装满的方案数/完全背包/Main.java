package 动态规划.template.背包.求方案数.恰好装满的方案数.完全背包;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 物品数量
        int n = scanner.nextInt();
        // 背包容量
        int m = scanner.nextInt();
        int[] v = new int[n + 1];   // 体积

        for (int i = 1; i <= n; i++) {
            v[i] = scanner.nextInt();
        }

        // c[i]表示背包容量为i时恰好装满的方案数
        int[] c = new int[1010];
        // 不装入物品，背包容量为0时，方案数为1
        c[0] = 1;

        // 枚举物品
        for (int i = 1; i <= n; i++) {
            // 枚举体积
            for (int j = v[i]; j <= m; j++) {
                // 状态转移方程：当前容量j的方案数等于原来的方案数加上装入当前物品i后的方案数
                c[j] = (c[j] + c[j - v[i]]) % 1000000007;
            }
        }

        System.out.println(c[m]);
        scanner.close();
    }
}