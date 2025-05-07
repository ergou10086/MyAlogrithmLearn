package 数据结构.图.template.最短路.Floyd算法.无向图的最小环问题.P6175_无向图的最小环问题;

// 求最小环，想到两点之间的最短路
// 任意两点之间的最短路，Floyd算法
// Floyd算法有一个性质
// 就是在循环到点k的时候，还没有开始第k次循环，dij表示的是从i到j且仅经过编号1到k-1的点的最短路
// 途径编号大于等于k的最短路还没有被计算
// 最小环中的编号最大的顶点为k，环上与k相邻的两个点为i，j，则在最外层循环到k点的时候
// 该环的长度为dij+wjk+wki
// 在循环时候对每个k枚举满足i<k且j<k的点对(i,j)选择答案

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}



class Solutions{
    private int n, m, ans = (int) 1e8;
    private int[][] w = new int[110][110];
    private int[][] dp = new int[110][110];

    public Solutions() {
        FastReader sc = new FastReader();
        n = sc.nextInt();
        m = sc.nextInt();

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                // 初始化两点不相等，置为无穷大
                if(i != j){
                    dp[i][j] = w[i][j] = (int) 1e8;
                }
            }
        }

        for(int i = 1; i <= m; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            // 双向边，邻接矩阵存图
            w[a][b] = Math.min(w[a][b], c);
            w[b][a] = Math.min(w[b][a], c);
            dp[a][b] = Math.min(dp[a][b], c);
            dp[b][a] = Math.min(dp[b][a], c);
        }



        for (int k = 1; k <= n; k++) {
            // 从这前 k-1 个点中选出两个点 i , j 来
            // 保证i，j不同点且小于k
            for (int i = 1; i < k; i++) {
                for (int j = i + 1; j < k; j++) {
                    // 构造环的周长,连接 / i-j-k-i / ，我们就得到了一个经过 i , j , k 的最小环
                    ans = Math.min(ans, dp[i][j] + w[j][k] + w[k][i]);
                }
            }
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }

        if (ans == (int) 1e8) {
            System.out.println("No solution.");
        } else {
            System.out.println(ans);
        }
    }




    private class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        String next() {
            while (!st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

}