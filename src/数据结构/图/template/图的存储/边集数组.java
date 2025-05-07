package 数据结构.图.template.图的存储;

import java.util.*;

// 边集数组
// 边集数组e[i]存储第i条边的{起点u，终点v，边权w}
// 时间复杂度：O（nm），空间复杂度O（m）
// 应用：在Kruskal算法中，需要将边按边权排序，直接存边

public class 边集数组 {
    static final int N = 1010;
    static int n, m, a, b, c;

    // 边结构体，表示一条边，表示起点，终点，边权
    static class Edge {
        int u, v, w;
        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    // 边集，用 ArrayList 存储
    static ArrayList<Edge> edges = new ArrayList<>();
    static boolean[] vis = new boolean[N];  // 访问标记数组

    // 深度优先搜索访问
    static void dfs(int u) {
        vis[u] = true;
        // 对每条边遍历
        for (Edge edge : edges) {
            // 如果边的起点和要访问的节点一样，进入这条边
            if(edge.u == u){
                int v = edge.v;
                int w = edge.w;
                System.out.printf("%d,%d,%d\n", u, v, w);
                if (vis[v]) continue;   // 走过一次就不要再走了
                dfs(v);
            }
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        n = scanner.nextInt();
        m = scanner.nextInt();

        // 读取图的边信息
        for (int i = 1; i <= m; i++) {
            a = scanner.nextInt();
            b = scanner.nextInt();
            c = scanner.nextInt();
            edges.add(new Edge(a, b, c));  // 将边添加到边集
            // edges.add(new Edge(b, a, c)); // 如果是无向图，可以取消注释这一行
        }

        // 从节点1开始DFS遍历
        dfs(1);

        scanner.close();
    }
}
