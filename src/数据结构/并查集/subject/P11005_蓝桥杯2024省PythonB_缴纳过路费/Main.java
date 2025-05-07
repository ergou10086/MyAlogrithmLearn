package 数据结构.并查集.subject.P11005_蓝桥杯2024省PythonB_缴纳过路费;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

// 一条路线包含一条或多条商路，但路线的成本并不是沿途累积的过路费总和，而是这条路线上最贵的那一次收费
// 给出一张 n 个点 m 条边的带权无向图。定义路径的权值为路径上所有边权的最大值
// 设 f(u,v) 表示 u,v 之间的所有路径中最小的路径权值，求满足 u<v,l≤f(u,v)≤r 的二元组 (u,v) 的数量。

class Solutions{
    private int n, m, l, r;   // 有 N 座城市和 M 和 区间上下限

    private int find(int u, int[] fa) {
        if (u != fa[u]) fa[u] = find(fa[u], fa);
        return fa[u];
    }

    public Solutions() {
        FastReader sc = new FastReader();
        n = sc.nextInt();
        m = sc.nextInt();
        l = sc.nextInt();
        r = sc.nextInt();

        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            // u,v之间权值w
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            edges.add(new int[]{w, u, v});
        }

        edges.sort(Comparator.comparingInt(a -> a[0]));

        // 初始化并查集
        int[] fa = new int[n + 1];
        int[] size = new int[n + 1];   // 记录每个连通分量的大小
        for (int i = 1; i <= n; i++) {
            fa[i] = i;   // 并查集初始化
            size[i] = 1;   // 初始连通块大小就是自己
        }

        long ans = 0;

        for (int[] edge : edges) {
            int w = edge[0];
            int u = edge[1];
            int v = edge[2];

            int rootU = find(u, fa);
            int rootV = find(v, fa);
            if (rootU != rootV) {
                if(l <= w && w <= r){
                    // 连通块内任意两两组合都是满足的
                    ans += (long) size[rootU] * size[rootV];
                }
                // 合并
                fa[rootV] = rootU;
                size[rootU] += size[rootV];
            }
        }

        System.out.println(ans);
    }

    class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        String next(){
            while(!st.hasMoreElements()){
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        int nextInt(){
            return Integer.parseInt(next());
        }
    }
}