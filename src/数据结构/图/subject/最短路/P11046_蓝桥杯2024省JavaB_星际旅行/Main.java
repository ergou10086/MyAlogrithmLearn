package 数据结构.图.subject.最短路.P11046_蓝桥杯2024省JavaB_星际旅行;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    // 有 n 个点，m 条边，第 i 条边表示一条连接 u 与 v 的无向边
    // 接下来有 Q 个询问，求从 xi 出发，最多经过 yi 条边，所能到达的点的个数。
    // 能到达的点的个数的期望是多少
    // 跑n次dijk
    public static void main(String[] args) {
        new Solutions();
    }
}


class Solutions{
    private final int N = 5005;
    private int n, m, Q;

    private class Edge {
        // 终点，边权
        int v, w;
        Edge(){}
        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    private List<Edge>[] edges;
    private int[][] dist = new int[1005][5005];
    private boolean[][] vis = new boolean[1005][5005];


    private void dijkstra(int s) {
        for (int i = 0; i <= n; i++) {
            dist[s][i] = Integer.MAX_VALUE;
        }
        dist[s][s] = 0;  // 起点为0

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, s}); // {距离, 节点编号}

        while(!pq.isEmpty()) {
            int[] current = pq.poll();
            int u = current[1];   // 点
            if(vis[s][u]) continue;
            vis[s][u] = true;

            // 枚举u的每一个临点
            for (Edge edge : edges[u]) {
                int v = edge.v, w = edge.w;
                if (dist[s][v] > dist[s][u] + w) {
                    dist[s][v] = dist[s][u] + w;
                    if (!vis[s][v]) {
                        pq.add(new int[] {dist[s][v], v});
                    }
                }
            }
        }
    }


    public Solutions() {
        FastReader sc = new FastReader();
        n = sc.nextInt();
        m = sc.nextInt();
        Q = sc.nextInt();

        // 初始化
        edges = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }

        // // 双向传送门
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            edges[a].add(new Edge(b, 1));
            edges[b].add(new Edge(a, 1));
        }

        for (int i = 1; i <= n; i++) {
            dijkstra(i);
        }

        int ans = 0;
        for (int i = 1; i <= Q; i++) {
            // 起始星球
            int xi = sc.nextInt();
            // 最多可以使用传送门的次数
            int yi = sc.nextInt();
            // 枚举每一条边
            for (int j = 1; j <= n; j++) {
                // 距离可达
                if(dist[xi][j] <= yi) {
                    ans++;
                }
            }
        }

        // 期望
        System.out.printf("%.2f", ans * 1.0 / Q);
    }

    class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        String next() {
            while(st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    // TODO 自动生成的 catch 块
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

