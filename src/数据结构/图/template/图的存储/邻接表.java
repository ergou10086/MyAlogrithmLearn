package 数据结构.图.template.图的存储;

import java.util.*;

// 出边数组e[u][i]存储u点的所有出边的{终点v，边权w}
// 时间空间复杂度都是O（n + m），复杂度低
// 应用：各种图，不能处理反向边，没有存储边的编号

public class 邻接表 {
    static final int N = 510;
    static int n, m, a, b, c;
    static List<Edge> e[] = new ArrayList[N];   // 边集，下标存储的是起点

    // 边结构体，v为终点，w为边权
    static class Edge {
        int v, w;
        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void dfs(int u, int fa) {
        // 遍历以u为起点的每一条边
        for (Edge ed : e[u]) {
            int v = ed.v, w = ed.w;   // 取出终点边权
            if (v == fa) continue;    // 如果终点的值等于父节点，跳过，去重
            System.out.printf("%d,%d,%d\n", u, v, w);
            dfs(v, u);
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            e[i] = new ArrayList<>();
        }

        for (int i = 1; i <= m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            e[a].add(new Edge(b, c));
            // e[b].add(new Edge(a, c)); // 无向边
        }

        dfs(1, 0);
    }
}
