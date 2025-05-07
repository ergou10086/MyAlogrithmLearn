package 数据结构.图.template.图的存储;

import java.util.*;

// 在邻接表的基础上修复了不能处理反向边的情况
// 边集数组e[j]存储第j条边的{起点u，终点v，边权w}
// 表头数组h[u][i]存储u点的所有出边的编号
// 时间空间复杂度都是O（n + m），复杂度低
// 应用：各种图，能处理反向边

public class 链式邻接表 {
    static final int N = 510;
    static int n, m, a, b, c;

    static class Edge{
        // 起点，终点，边权
        int u, v, w;
        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
    // 边集
    static List<Edge> edges = new ArrayList<>();
    // 点的所有出边，点跟了几条边，下标为其编号
    static List<Integer>[] h = new ArrayList[N];
    // 初始化每个点的出边集合
    static {
        for (int i = 0; i < N; i++) {
            h[i] = new ArrayList<>();
        }
    }

    // 添加边
    static void add(int a, int b, int c) {
        // 边集数组里面添加边的完成信息
        edges.add(new Edge(a, b, c));
        // 表头数组存储边的信息，初始下标为0
        h[a].add(edges.size() - 1);
    }

    // 深度优先搜索访问
    static void dfs(int u, int fa) {
        // 对起点u，访问每条边
        for(int i = 0; i < h[u].size(); i++) {
            // 取出边的信息，j为起点u的第i条边的编号
            int j = h[u].get(i);
            // 提取j号边的信息
            Edge edge = edges.get(j);
            int v = edge.v, w = edge.w;
            // 判重
            if(v == fa) continue;
            System.out.printf("%d,%d,%d\n", u, v, w);
            dfs(v, u);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            add(a, b, c);
            add(b, a, c);  // 由于是无向图，因此需要反向添加
        }

        dfs(1, 0);
        sc.close();
    }
}
