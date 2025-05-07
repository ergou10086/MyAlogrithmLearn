package 数据结构.图.template.最短路.Dijkstra算法.堆优化版本.带路径的记录与输出版本;

import java.io.*;
import java.util.*;

class FastReader {
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



class Solution{
    private class Edge{
        // 终点，边权
        int v, w;
        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    private final int INF = 2147483647;
    private int n, m, s;
    private List<Edge>[] e;
    private int[] dist;
    private int[] pre = new int[100100];   // 前驱记录数组
    private boolean[] vis;
    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

    // 递归输出路径
    private void dfs_path(int u){
        if(u == s){
            System.out.print(u + " ");
            return;
        }
        dfs_path(pre[u]);
        System.out.print(u + " ");
    }

    // 打印从源点到节点u的最短路径
    private void print_path(int u, BufferedWriter writer) throws IOException {
        if (dist[u] == INF) {
            writer.write("NO PATH ");
            return;
        }
        dfs_path(u);
        writer.write("\n");
    }

    private void dijkstra(int s) {
        // 初始化
        Arrays.fill(dist, INF);
        dist[s] = 0;
        pq.offer(new int[]{0, s});

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int u = cur[1];
            if(vis[u]) continue;
            vis[u] = true;
            for(Edge e : e[u]){
                int v = e.v;
                int w = e.w;
                if(dist[v] > dist[u] + w){
                    dist[v] = dist[u] + w;
                    pre[v] = u;   // 记录前驱点,总是记录上一个走过的点
                    pq.offer(new int[]{dist[v], v});
                }
            }
        }
    }

    public Solution(){
        FastReader sc = new FastReader();
        n = sc.nextInt();
        m = sc.nextInt();
        s = sc.nextInt();

        // 初始化图
        e = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            e[i] = new ArrayList<>();
        }

        // 输入边
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            e[a].add(new Edge(b, c));
        }

        dist = new int[n + 1];
        vis = new boolean[n + 1];

        dijkstra(s);

        // 使用BufferedWriter进行快速输出
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 1; i <= n; i++) {
            if (dist[i] == INF) {
                try {
                    writer.write(2147483647 + " ");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                try {
                    writer.write(dist[i] + " ");
                    // 打印最短路径
                    print_path(i, writer);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        try {
            writer.newLine();  // 换行
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        try {
            writer.flush();    // 确保输出
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        new Solution();
    }
}
