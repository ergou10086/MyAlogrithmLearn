package 数据结构.图.template.图的存储;

import java.util.*;

// 邻接矩阵
// 二维数组 w[u][v]存储从点u到点v的边的权值
// 时间空间复杂度都是 O(n^2)
// 应用：点数不多的稠密图（有向图）
// 访问是按行列顺序访问

public class 邻接矩阵 {
    static final int N = 1010;
    static int n, m, a, b, c;
    static int[][] w = new int[N][N];  // 邻接矩阵，用来存储边权
    static boolean[] vis = new boolean[N];  // 访问标记数组

    // 深度优先搜索访问
    static void dfs(int u) {
        vis[u] = true;    // 标记为已访问
        for (int v = 1; v <= n; v++) {
            if (w[u][v] != 0) {  // 有边，权值不是0
                System.out.printf("%d,%d,%d\n", u, v, w[u][v]);
                if (vis[v]) continue;   // 判重，防止陷入环的死循环
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
            w[a][b] = c;  // 假设是有向图
            // w[b][a] = c;  // 如果是无向图，可以取消注释这一行
        }

        // 从节点1开始DFS遍历
        dfs(1);

        scanner.close();
    }
}
