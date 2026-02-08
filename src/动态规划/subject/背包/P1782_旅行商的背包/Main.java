package 动态规划.subject.背包.P1782_旅行商的背包;

import java.io.*;
import java.util.*;

public class Main {
    static int MAX_C = 10005;
    static int[] dp = new int[MAX_C];
    static int[] preDp = new int[MAX_C];
    static int[] q = new int[MAX_C];

    static int n, m, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);

        st.nextToken(); n = (int) st.nval;
        st.nextToken(); m = (int) st.nval;
        st.nextToken(); C = (int) st.nval;

        // --- 多重背包 ---
        for (int i = 0; i < n; i++) {
            st.nextToken(); int v = (int) st.nval;
            st.nextToken(); int w = (int) st.nval;
            st.nextToken(); int d = (int) st.nval;

            if (v == 0) continue;
            mqPack(v, w, d);
        }

        // --- 奇货处理 ---
        for (int i = 0; i < m; i++) {
            st.nextToken(); int a = (int) st.nval;
            st.nextToken(); int b = (int) st.nval;
            st.nextToken(); int c = (int) st.nval;

            // 备份当前状态，避免重复使用
            System.arraycopy(dp, 0, preDp, 0, C + 1);

            // 分组背包：从大到小遍历
            for (int j = 0; j <= C; j++) {
                // 尝试分配 k 体积给这个奇货
                for (int k = 0; k <= j; k++) {
                    int val = a * k * k + b * k + c;
                    dp[j] = Math.max(dp[j], preDp[j - k] + val);
                }
            }
        }

        System.out.println(dp[C]);
    }

    private static void mqPack(int v, int w, int d) {
        System.arraycopy(dp, 0, preDp, 0, C + 1);

        for(int r = 0; r < v; r++){
            int head = 0, tail = -1;

            for(int k = 0; k * v + r <= C; k++){
                int val = preDp[k * v + r] - k * w;

                // 维护单调递减队列
                while(head <= tail && val >= preDp[q[tail] * v + r] - q[tail] * w){
                    tail--;
                }

                tail++;
                q[tail] = k;

                // 移除滑出窗口的元素
                while(head <= tail && q[head] < k - d){
                    head++;
                }

                int bK = q[head];
                dp[k * v + r] = (preDp[bK * v + r] - bK * w) + k * w;
            }
        }
    }
}