package 数据结构.图.template.生成树.最小生成树.Prim算法.P3366_模板_最小生成树.暴力prim;

import java.io.*;
import java.util.*;

// 该算法的基本思想是从一个结点开始，不断加点（而不是 Kruskal 算法的加边）。
// 具体来说，每次要选择距离最小的一个结点，以及用新的边更新其他结点的距离。
// 其实跟 Dijkstra 算法一样，每次找到距离最小的一个点，可以暴力找也可以用堆维护
// 堆优化的方式类似 Dijkstra 的堆优化,一般情况下都使用 Kruskal 算法，在稠密图尤其是完全图上，暴力 Prim 的复杂度比 Kruskal 优，但 不一定 实际跑得更快。


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


class Solutions {
    private final int N = 5010;
    private final int INF = 114514114;
    private int n, m, a, b, c, ans, cnt;
    private int[] dis = new int[N];
    private boolean[] vis = new boolean[N];   // 标记u点是否出圈
    private class Edge{
        // 终点，边权
        int v, w;
        private Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    // 存u点的所有邻边的终点和边权
    // dis[u]存u点与圈外邻点的最小举例
    private ArrayList<Edge>[] e = new ArrayList[N];


    // prim算法流程类似dijkstra算法
    // 不断选择距离最小的点出圈，直到圈内为空
    // 初始化，所有的点都在圈内，vis=0，d[s]=0，d[其他点] = 正无穷
    // 每次从圈内选择一个距离最小的点u，打标记移出圈
    // 对u的所有邻点的距离执行更新操作
    // 重复2，3，直到圈空
    private boolean prim(int s){
        // 初始化
        Arrays.fill(dis, INF);
        Arrays.fill(vis, false);
        dis[s] = 0;

        for(int i = 1; i <= n; i++) {
            int u = 0;
            for (int j = 1; j <= n; j++) {
                if (!vis[j] && dis[j] < dis[u]) u = j;
            }
            vis[u] = true;  // 标记u已出圈
            ans += dis[u];
            // 判断是否联通
            if(dis[u] != INF) cnt++;
            for(Edge edge : e[u]){
                int v = edge.v;
                int w = edge.w;
                // 如果边权更小，更新dis[v]
                if(dis[v] > w) dis[v] = w;
            }
        }
        return cnt == n;
    }


    public Solutions() {
        FastReader fr = new FastReader();
        n = fr.nextInt();
        m = fr.nextInt();
        for (int i = 0; i < m; i++) {
            e[i] = new ArrayList<>();
        }
        for(int i = 0; i < m; i++){
            a = fr.nextInt();
            b = fr.nextInt();
            c = fr.nextInt();
            e[a].add(new Edge(b, c));
            e[b].add(new Edge(a, c));
        }
        if(!prim(1)) System.out.println("orz");
        else System.out.println(ans);
    }
}

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}
