package 数据结构.图.subject.最短路.P1744_采购特价商品;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Solution();
    }
}


class Solution {
    private static final int N = 106, M = 1006;
    private int[] ne = new int[M * 2];   // ne数组用于存储与当前边同起点的上一条边在数组中的下标，用于构建邻接表的链式结构
    private int[] h = new int[N * 2];    // 表头数组，h[i]存储的是以节点i为起点的第一条边在边数组中的下标
    private int[] to = new int[M * 2];
    private int[] x = new int[N * 2];
    private int[] y = new int[N * 2];
    private int idx = 0;
    private double[] w = new double[M * 2];
    private double[] dist = new double[N * 2];
    private boolean[] vis = new boolean[N * 2];
    private int n, m, start, end;

    // 计算两点间距离
    private double GetDis(int x1, int y1, int x2, int y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    // 加边
    private void add(int a, int b, double value) {
        to[idx] = b;
        w[idx] = value;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    // 判负环
    public void spfa() {
        // 初始化
        for(int i = 1; i <= n ;i++)  dist[i]=1000000000.0;
        dist[start] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        vis[start] = true;

        while(!queue.isEmpty()){
            int u = queue.poll();
            vis[u] = false;

            // 遍历以u为起点的所有边
            for(int i = h[u]; i != -1; i = ne[i]){
                int v = to[i];
                // 新路径更短，就松弛
                if (dist[v] > dist[u] + w[i]) {
                    dist[v] = dist[u] + w[i];
                    if(!vis[v]){
                        queue.add(v);
                        vis[v] = true;
                    }
                }
            }
        }
        System.out.printf("%.2f", dist[end]);
    }

    public Solution() {
        FastReader sc = new FastReader(System.in);
        n = sc.nextInt();
        Arrays.fill(h, -1);

        for (int i = 1; i <= n; i++) {
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
        }

        m = sc.nextInt();
        while (m-- > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            add(a, b, GetDis(x[a], y[a], x[b], y[b]));
            add(b, a, GetDis(x[a], y[a], x[b], y[b]));
        }

        start = sc.nextInt();
        end = sc.nextInt();
        spfa();
    }

    private class FastReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public FastReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
