package 动态规划.template.背包.多重背包.二进制拆分;

import java.util.Scanner;

// 二进制拆分就是用先准备2^k拆分然后组合成任意大小的数
// 可以把多重背包优化成01背包

// 将第i种物品拆分成若干种，每件物品的体积和价值乘一个拆分系数，就可以转化成01背包的物品求解

public class Main {
    static final int N = 2005; // 2000 < 2^12
    static int n, m;
    static int wei1, val1, s;
    static int[] wei = new int[N * 12];
    static int[] val = new int[N * 12];
    static int[] f = new int[N];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();

        // 二进制拆分
        int num = 1;    // 拆分计数
        for (int i = 1; i <= n; i++) {
            // 体积，价值，数量
            wei1 = scanner.nextInt();
            val1 = scanner.nextInt();
            s = scanner.nextInt();
            // 把本来存每一件物品，变成存j件的，然后j每次*2，这样处理相当于每个位置都是下一个位置的2倍
            for (int j = 1; j <= s; j <<= 1) {
                wei[num] = j * wei1;
                val[num++] = j * val1;
                s -= j;
            }
            // 处理剩余的
            if (s > 0) {
                wei[num] = s * wei1;
                val[num++] = s * val1;
            }
        }

        // 01背包
        // 物品数循环到小于num，拆分后拆成了num个
        for (int i = 1; i < num; i++) {
            // 逆序枚举
            for (int j = m; j >= wei[i]; j--) {
                f[j] = Math.max(f[j], f[j - wei[i]] + val[i]);
            }
        }

        System.out.println(f[m]);

        scanner.close();
    }
}
