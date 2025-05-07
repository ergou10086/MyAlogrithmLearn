package 基础算法和其他.前缀和.template.树上前缀和.luoguP4427_求和;

import java.util.*;

// 多次询问树上的一些路径的权值和，就要考虑树上前缀和
// 路径就是类似于序列的区间

// 点前缀和
// 设s[i]表示从根节点到节点i的点权和
// 先自顶向下计算出前缀和s[i]，然后用前缀和拼凑(x,y)的路径和
//    s[x] + s[y] - s[lca] - s[fa[lca]]
// 也就是两点之间的前缀和需要是两点的前缀和之和减去最近公共祖先的前缀和再减去最近公共祖先的父节点的前缀和

// 边前缀和
// 设s[i]表示从和节点到节点i的边权和
// 先自顶向下计算出前缀和s[i]，然后用前缀和拼凑(x,y)的路径和
//     s[x] + s[y] - 2*s[lca]
// 两条边的前缀和就是根节点分别到两条边的前缀和之和减去被加两次的最近公共祖先的前缀和

public class Main {
    static final long MOD = 998244353;
    static final int N = 300010;
    static final int M = 2 * N;

    static int[] h = new int[N];   // 存储每个节点的邻接表头指针
    static int[] to = new int[M];  // 存储每条边指向的目标节点
    static int[] ne = new int[M];  // 构建邻接表中的链表结构，实现边的链式存储,ne[i]存储的是与第i条边同属一个节点的下一条边的存储位置（索引）
    static int tot = 0;    // 当前已经添加的边的总数
    static int[][] fa = new int[N][22]; // fa[u][i]表示从u向上跳2^i层的祖先结点
    static int[] dep = new int[N];   // dep[v]表示v的深度
    static long[] mi = new long[60];   // mi[j]表示dep[v]的j次幂
    static long[][] s = new long[N][60]; // s[v][j]表示从根到v的路径节点的深度的j次幂之和

    // 添加边的方法，用于构建树的邻接表
    static void add(int a, int b) {
        to[++tot] = b;  // 当前添加的这条边的终点是节点b
        ne[tot] = h[a];  // 将当前节点a的邻接表头指针（即h[a]的值）存储到ne数组中以新的tot值为索引的位置,将新添加的这条边（到节点b的边）链接到节点a的邻接表的头部
        h[a] = tot;   // 更新节点a的邻接表头指针，使得它指向刚刚添加的这条边（到节点b的边）的存储位置
    }


    // 深度优先搜索，用于预处理节点信息
    static void dfs(int u, int f) {
        for (int i = 1; i <= 20; i++) {
            fa[u][i] = fa[fa[u][i - 1]][i - 1];
        }

        for (int i = h[u]; i > 0; i = ne[i]) {
            int v = to[i];
            if (v == f) continue;
            fa[v][0] = u;   // 先处理左儿子
            dep[v] = dep[u] + 1;
            // 把每一个点的它的深度的多少次方
            for (int j = 1; j <= 50; j++) {
                mi[j] = mi[j - 1] * dep[v] % MOD;
            }
            // 前缀和
            for(int j = 1; j <= 50; j++) {
                s[v][j] = (mi[j] + s[u][j]) % MOD;
            }
            dfs(v, u);
        }
    }

    // 倍增法lca求最近公共祖先
    static int lca(int u, int v) {
        if (dep[u] < dep[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        for (int i = 20; i >= 0; i--) {
            if (dep[fa[u][i]] >= dep[v]) {
                u = fa[u][i];
            }
        }
        if (u == v) return v;
        for (int i = 20; i >= 0; i--) {
            if (fa[u][i] != fa[v][i]) {
                u = fa[u][i];
                v = fa[v][i];
            }
        }
        return fa[u][0];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        for (int i = 1; i < n; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            add(a, b);
            add(b, a);
        }
        mi[0] = 1;
        dfs(1, 0);

        int m = scanner.nextInt();
        for (int i = 1; i <= m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int k = scanner.nextInt();
            int l = lca(u, v);
            long ans = (s[u][k] + s[v][k] - s[l][k] - s[fa[l][0]][k] + 2 * MOD) % MOD;
            System.out.println(ans);
        }
        scanner.close();
    }
}
