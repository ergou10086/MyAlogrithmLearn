package 数据结构.图.template.最短路.Dijkstra算法.P3371_模板_单源最短路径_弱化;

// Dijkstra算法是基于贪心思想的单源最短路算法
// e[u]存节点u的所有出边的终点和边权
// d[u]存u到源点s的最小距离
// vis[u]标记u是否出圈

// 初始时，所有点都在圈（集合）内，vis=0，d[s]=0,d[其他点]=正无穷
// 每次从圈内选择一个距离最小的点，打标记移出圈
// 对u的所有出边执行松弛操作（尝试更新邻点v的最小距离）
// 重复2，3，直到圈空

// 这个代码不适用于负边权

// 时间复杂度为O（n^2 + m)
// 如果点数很多，边数没那么多，会超时，稀疏图会超时，适用于稠密图

import java.io.*;
import java.util.*;

// 快读类
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
    // 定义一个边的结构体类
    static class Edge {
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
    private int[] d;
    private boolean[] vis;

    private void dijkstra(int s){
        // 初始化
        for(int i = 0; i <= n; i++) d[i] = INF;
        // 源点到自身的距离为0
        d[s] = 0;

        // 枚举次数n-1轮
        for(int i = 1; i < n; i++){
            // 当前点设置为0(0点不存在)
            int u = 0;
            // 枚举每一个点
            for(int j = 1; j <= n; j++){
                // 如果点j没有出圈并且距离更短，更新u为j，第一次u肯定选择源点自己
                if(!vis[j] && d[j] < d[u]) u = j;
            }
            // 标记为u已经被访问
            vis[u] = true;

            // 枚举u点的所有临边并更新距离，松弛化
            for(Edge edge : e[u]){
                int v = edge.v;
                int w = edge.w;
                if (d[v] > d[u] + w) {
                    d[v] = d[u] + w;
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

        d = new int[n + 1];
        vis = new boolean[n + 1];

        dijkstra(s);

        // 使用BufferedWriter进行快速输出
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 1; i <= n; i++) {
            if (d[i] == INF) {
                try {
                    writer.write(2147483647 + " ");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                try {
                    writer.write(d[i] + " ");
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
    public static void main(String[] args) throws IOException {
        new Solution();
    }
}
