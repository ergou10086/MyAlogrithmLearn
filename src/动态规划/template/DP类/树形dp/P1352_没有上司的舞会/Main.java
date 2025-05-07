package 动态规划.template.DP类.树形dp.P1352_没有上司的舞会;

// 考虑一棵以u为根节点的子树，这颗子树的快乐指数因该是u的函数
// 两种情况，选u，不选u
// f[u][1]表示以u为根节点的子树并且包括u的总快乐指数
// f[u][0]表示以u为根节点的子树并且不包括u的总快乐指数

// 记u的子节点为s
// 选u  f[u][1] += sigma f[s][0]  选u那么他的上司不能选
// 不选u  f[u][0] += sigma Math.max(f[s][0], f[s][1])

// 树形DP思路，分析子树，通常最优解和子树根节点相关，状态计算就是寻找根节点与子节点以及边权的递推关系
// dfs，从根到叶计算和，从叶到根做dp

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    // 最大节点数
    static final int N = 6010;
    // 邻接表存储树的结构
    static List<List<Integer>> adj = new ArrayList<>();
    // 节点的权值
    static int[] w = new int[N];
    // 记录父节点
    static boolean[] fa = new boolean[N];
    // 动态规划数组，f[u][0] 表示不选节点 u，f[u][1] 表示选节点 u
    static int[][] f = new int[N][2];

    // 添加边到邻接表
    static void add(int a, int b) {
        adj.get(a).add(b);
    }

    // 从根节点u开始dfs，实现累加和，从叶到根返回时，做DP累加
    private static void dfs(int u) {
        // 选u的快乐指数，做累加和
        f[u][1] = w[u];

        for(int v: adj.get(u)) {
            dfs(v);
            f[u][0] += Math.max(f[v][0], f[v][1]);
            f[u][1] += f[v][0];
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        // 初始化邻接表
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        // 读取每个节点的权值
        for (int i = 1; i <= n; i++) {
            w[i] = scanner.nextInt();
        }

        // 读取边的信息
        for (int i = 0; i < n - 1; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            add(b, a);
            fa[a] = true;
        }

        // 找到根节点
        int root = 1;
        while (fa[root]) {
            root++;
        }

        // 进行深度优先搜索
        dfs(root);

        // 输出结果
        System.out.println(Math.max(f[root][0], f[root][1]));
        scanner.close();
    }
}
