package 动态规划.subject.线性dp.P10911_蓝桥杯2024国B_数位翻转;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions {
    private int n, m;
    private long finf = Long.MIN_VALUE / 2;
    private int[] a;
    private long[][][] dp;
    private int[] f;

    private int f(int x) {
        // 先转成二进制
        int[] d = new int[46];
        int cur = 0;   // 当前位
        while (x >= 1) {
            d[++cur] = x % 2;
            x /= 2;
        }
        // 再翻转
        reverse(d, 1, cur);
        // 转成十进制
        int res10 = 0;
        for (int i = 1; i <= cur; i++) {
            if (d[i] == 1) res10 += (1 << (i - 1));
        }
        return res10;
    }

    private void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    public Solutions() {
        FastReader sc = new FastReader();
        n = sc.nextInt();
        m = sc.nextInt();
        a = new int[n + 1];
        f = new int[n + 1];
        dp = new long[n + 1][m + 1][2];

        for (int i = 1; i <= n; i++) a[i] = sc.nextInt();
        for (int i = 1; i <= n; i++) f[i] = f(a[i]);

        // 初始化 DP 数组
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j][0] = dp[i][j][1] = finf;
            }
        }
        dp[0][0][0] = 0;

        // DP 状态转移
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                // 翻这一段，那么上一段中没翻的就要被翻了,dp[i][j][1]更新,而且这一段内都要翻
                if(j > 0 && dp[i - 1][j - 1][0] != finf) dp[i][j][1] = dp[i - 1][j - 1][0] + f[i];
                // 处理翻转的状态
                if(dp[i - 1][j][1] != finf) {
                    dp[i][j][1] = Math.max(dp[i][j][1], dp[i - 1][j][1] + f[i]);
                    // 结束翻转区间的状态
                    dp[i][j][0] = dp[i - 1][j][1] + a[i];
                }
                // 处理不反转的状态
                if(dp[i - 1][j][0] != finf) dp[i][j][0] = Math.max(dp[i][j][0], dp[i - 1][j][0] + a[i]);
            }
        }

        long ans = 0;
        for(int i = 0; i <= m; i++) {
            ans = Math.max(ans, Math.max(dp[n][i][0], dp[n][i][1]));
        }
        System.out.print(ans);
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