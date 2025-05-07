package 动态规划.template.背包.求具体方案.完全背包的求具体方案;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions {
    static int n, m;
    int[][] f = new int[2005][2005]; // 动态规划数组
    int[] w = new int[2005]; // 物品体积
    int[] v = new int[2005]; // 物品价值
    int[] fl = new int[2005]; // 记录每个物品被选择的次数


    public Solutions() {
        Scanner sc = new Scanner(System.in);
        // 物品数量和背包体积
        n = sc.nextInt();
        m = sc.nextInt();

        for(int i = 1; i <= n; i++){
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }

        // 动态规划处理物品，从后往前（n到1）
        for (int i = n; i >= 1; i--) {
            for (int j = 1; j <= m; j++) {
                f[i][j] = f[i + 1][j]; // 不选当前物品
                if (j >= w[i] && f[i][j] < f[i][j - w[i]] + v[i]) {
                    f[i][j] = f[i][j - w[i]] + v[i];
                }
            }
        }

        System.out.println(f[1][m]); // 输出最大价值

        // 回溯构造具体方案
        // 按物品1到n的顺序，保证字典序最小
        int ret = m;
        for (int i = 1; i <= n; i++) {
            while(ret >= w[i] && f[i][ret] == f[i][ret - w[i]] + v[i]){
                fl[i]++;
                ret -= w[i];
            }
        }

        // 输出每个物品的选择次数
        for (int i = 1; i <= n; i++) {
            if (fl[i] > 0) {
                System.out.println(i + " " + fl[i]);
            }
        }

        sc.close();
    }
}