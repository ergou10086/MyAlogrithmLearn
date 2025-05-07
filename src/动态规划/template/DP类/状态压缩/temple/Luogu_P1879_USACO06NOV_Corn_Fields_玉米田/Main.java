package 动态规划.template.DP类.状态压缩.temple.Luogu_P1879_USACO06NOV_Corn_Fields_玉米田;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {

    }
}

// 在一个有障碍的矩阵中放置物品，要求不能相邻，求方案数。
// 状态压缩dp
// 基本思想就是用二进制来优美地暴力枚举出现的方案，
// 若二进制下第i位有赋值1，则一行的第i列有放牛
// 所以f[i][j]表示在前i行中，在j个状态下的最大方案数
// f[i][j] = (f[i][j] + f[i - 1][k]) mod p  (j是第i行的状态，k是第i−1行的状态)
// 目标状态就是 f[n][i]全部相加

class Solutions {
    public Solutions() {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        int m = sc.nextInt();
        // g[i]表示第i个状态是否存在
        int[] g = new int[15];   // 各行的状态

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int x = sc.nextInt();
                g[i] = (g[i] << 1) + x;
            }
        }


    }

    class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        String next() {
            while (!st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
