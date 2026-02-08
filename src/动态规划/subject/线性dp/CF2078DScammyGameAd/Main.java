package 动态规划.subject.线性dp.CF2078DScammyGameAd;

import java.util.*;
import java.io.*;

public class Main {

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }

    static class Door {
        char type;
        long val;

        Door(String s, long v) {
            this.type = s.charAt(0);
            this.val = v;
        }
    }

    public static void main(String[] args) {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        int t = fr.nextInt();
        while (t-- > 0) {
            int n = fr.nextInt();
            Door[][] doors = new Door[n + 1][2];

            for (int i = 1; i <= n; i++) {
                // 左门
                doors[i][0] = new Door(fr.next(), fr.nextLong());
                // 右门
                doors[i][1] = new Door(fr.next(), fr.nextLong());
            }

            // f[i][0] 表示第 i 关结束后，在左通道的 1 个人到终点能变成多少人
            // f[i][1] 表示第 i 关结束后，在右通道的 1 个人到终点能变成多少人
            long[][] f = new long[n + 1][2];

            // 基础状态
            f[n][0] = 1;
            f[n][1] = 1;

            for (int i = n; i >= 1; i--) {
                long maxFuture = Math.max(f[i][0], f[i][1]);

                // 处理左门对上一关左通道人数的贡献
                if (doors[i][0].type == 'x') {
                    // 乘法门：原有的 1 人留下，新增的 (val-1) 人去未来价值更高的通道
                    f[i - 1][0] = f[i][0] + (doors[i][0].val - 1) * maxFuture;
                } else {
                    // 加法门：原有的 1 人直接通过，不产生基于人数的增量
                    f[i - 1][0] = f[i][0];
                }

                // 处理右门对上一关右通道人数的贡献
                if (doors[i][1].type == 'x') {
                    f[i - 1][1] = f[i][1] + (doors[i][1].val - 1) * maxFuture;
                } else {
                    f[i - 1][1] = f[i][1];
                }
            }

            // 计算最终总人数
            // 1. 初始在左右通道的各 1 人的最终贡献
            long totalMax = f[0][0] + f[0][1];

            // 2. 累加所有加法门产生的固定人数贡献
            for (int i = 1; i <= n; i++) {
                long maxFuture = Math.max(f[i][0], f[i][1]);
                if (doors[i][0].type == '+') {
                    totalMax += doors[i][0].val * maxFuture;
                }
                if (doors[i][1].type == '+') {
                    totalMax += doors[i][1].val * maxFuture;
                }
            }

            out.println(totalMax);
        }
        out.flush();
        out.close();
    }
}
