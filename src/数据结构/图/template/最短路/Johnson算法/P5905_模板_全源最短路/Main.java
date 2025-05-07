package 数据结构.图.template.最短路.Johnson算法.P5905_模板_全源最短路;

// 全源最短路算法
// floyd，时间复杂度O(n^3)
// 跑n次堆优化dijkstra，时间复杂度O(nmlogm)
// 跑n次bellman_ford，时间复杂度O(n^2*m)

// 约翰逊最短路算法，可以求负边权的全源最短路
// 新建一个虚拟源点0，从该点向其他所有点连一条边权为0的边
// 再用spfa算法求出0号点到其他所有点的最短路h(i)
// 新图的边权改造为，w(i,j) = w(i,j) + h(i) - h(j)，确保非负
// 以每个点为起点，然后跑n次dijkstra算法，时间复杂度O(nmlogm)

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}


class Solutions {
    private final int INF = 1000000000;
    private final int N = 30010;
    private int n, m;
    // 目标节点v和边的权重w
    private class Edge{
        int v;
        int w;
        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    private List<Edge>[] e = new ArrayList[N];  // 邻接表存图
    private boolean[] vis = new boolean[N];
    private int[] cnt = new int[N];
    private long[] h = new long[N];    // 节点入队次数，用于SPFA算法中检测负环
    private long[] d = new long[N];    // 存储Dijkstra算法计算出的最短路径距离
    private PrintWriter bw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    // 自定义的类，用于在优先队列中存储节点距离和节点编号的组合
    // 按照距离从小到大排序节点
    private class LongPair implements Comparable<LongPair> {
        long first;
        int second;

        LongPair(long first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        // 按照第一个元素的顺序升序排列
        public int compareTo(LongPair o) {
            return Long.compare(this.first, o.first);
        }
    }


    // 初始化邻接表
    private void initGraph() {
        for (int i = 0; i < N; i++) {
            e[i] = new ArrayList<>();
        }
    }


    // SPFA预处理，检测负环并计算h数组（类似势能函数）
    // spfa类似板子
    private void spfa() {
        Queue<Integer> queue = new LinkedList<>();
        // 初始化
        Arrays.fill(h, 63);
        Arrays.fill(vis, false);
        h[0] = 0;
        vis[0] = true;
        queue.add(0);

        // 取出队首节点u，标记为未访问，然后遍历其所有出边。
        while(!queue.isEmpty()) {
            int u = queue.poll();
            vis[u] = false;
            // 遍历以u为起点的所有边
            for(Edge e : e[u]) {
                int v = e.v;
                int w = e.w;
                // 新路径更短，就松弛,同时记录边数
                if(h[v] > h[u] + w) {
                    h[v] = h[u] + w;
                    cnt[v] = cnt[u] + 1;
                    // 如果节点v的入队次数超过节点总数n，说明存在负环，输出 -1并结束程序
                    if (cnt[v] > n) {
                        bw.write("-1\n");
                        bw.flush();
                        System.exit(0);
                    }
                    // 目标节点v未在队列中，则将其入队并标记为已访问。
                    if(!vis[v]){
                        queue.add(v);
                        vis[v] = true;
                    }
                }
            }
        }

    }


    // 堆优化dijkstra，用于计算从给定起点s出发到其他节点的最短路径，存储在d数组中
    private void dijkstra(int s) {
        // PriorityQueue默认是小根堆
        PriorityQueue<LongPair> q = new PriorityQueue<>();
        Arrays.fill(d, INF);
        Arrays.fill(vis, false);

        // 源点到自身的距离为0
        d[s] = 0;
        q.add(new LongPair(0, s));

        while(!q.isEmpty()) {
            // 从优先队列中取出距离最小的节点u
            int u = q.poll().second;
            // 被访问过，跳过
            if(vis[u]) {
                continue;
            }
            vis[u] = true;
            // 遍历当前节点u的所有出边
            for (Edge ed : e[u]) {
                int v = ed.v;
                int w = ed.w;
                if (d[v] > d[u] + w) {
                    d[v] = d[u] + w;
                    if (!vis[v]) {
                        q.add(new LongPair(d[v], v));
                    }
                }
            }
        }
    }



    public Solutions() {
        FastReader sc = new FastReader();
        n = sc.nextInt();
        m = sc.nextInt();

        initGraph();

        // 建图
        for(int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            e[a].add(new Edge(b, c));
        }

        // 添加虚拟边，从节点0到其他所有节点建立边权为0的一条虚拟边
        for (int i = 1; i <= n; i++) {
            e[0].add(new Edge(i, 0));
        }

        // 求0号点到每个点的最短路（势能函数）
        spfa();

        // 枚举每个点，对其出边更新
        for (int u = 1; u <= n; u++) {
            for (Edge ed : e[u]) {
                // 更新出边的边权，头减去尾
                ed.w += h[u] - h[ed.v];
            }
        }

        // 求新图中两个点的最短路，n轮dijkstra算法
        for (int i = 1; i <= n; i++) {
            dijkstra(i);
            long ans = 0;
            for (int j = 1; j <= n; j++) {
                if (d[j] == INF) {
                    ans += (long) j * INF;
                } else {
                    // 转换成旧图两点之间的最短路
                    ans += (long) j * (d[j] + h[j] - h[i]);
                }
            }
            bw.write(ans + "\n");
        }
        bw.flush();
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