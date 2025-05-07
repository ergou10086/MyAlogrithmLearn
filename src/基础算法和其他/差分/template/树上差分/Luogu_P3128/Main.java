package 基础算法和其他.差分.template.树上差分.Luogu_P3128;

// 多次对树上的一些路径做加法操作，然后询问某个点或某条边经过操作后的值，考虑树上差分
// 依旧是路径类比一维序列的区间
// 点差分
// 例如初始树上的各点点权为0，现对路径(x,y)上的点均做+1操作，等价于
//    dx + 1， dy + 1， dlca - 1， dfa[lca] - 1
// 边差分
// 例如初始树上的各点点权为0，现对路径(x,y)上的边均做+1操作
// 对边权的操作比较困难，通常把边权下移给节点，变成点权操作
//    dx + 1，dy + 1，dlca - 2

// 初态各个点的点权不为0，如何计算差分值
// 叶子节点的差分值等于自己的点权
// 其他节点的差分值 = 父权 - 子权和

import java.util.*;

public class Main {
    static final int N = 50005;
    static final int M = 2 * N;
    static int[] head = new int[N];   // 每个节点的邻接链表的头部
    static int[] to = new int[M];   // 边的目标节点
    static int[] next = new int[M];   // 存储边的下一条边
    static int edge = 0;        // 边数
    static int[] dep = new int[N];    //  存储每个节点的深度。
    static int[][] fa = new int[N][22];  // 预处理的数组，用于存储每个节点的祖先信息
    static int n;
    static int m;
    static int ans = 0;
    static int[] power = new int[N];

    // 加双向边
    public static void addEdge(int u, int v) {
        to[++edge] = v;
        next[edge] = head[u];
        head[u] = edge;

        to[++edge] = u;
        next[edge] = head[v];
        head[v] = edge;
    }

    // 倍增预处理，计算每个节点的深度并预处理其祖先
    static void dfs1(int u, int f){
        dep[u] = dep[f] + 1;
        fa[u][0] = f;
        for(int i = 0; fa[u][i] != 0; ++i) {
            fa[u][i + 1] = fa[fa[u][i]][i];
        }
        for (int i = head[u]; i != 0; i = next[i]) {
            int v = to[i];
            if (v != f) {
                dfs1(v, u);
            }
        }
    }

    // 倍增求lca
    static int lca(int u, int v) {
        // 保证 u 是较浅的节点。
        if (dep[u] > dep[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        // 将 v 向上移动到与 u 同一深度
        for (int i = 20; i >= 0; i--) {
            if (dep[u] <= dep[v] - (1 << i)) {
                v = fa[v][i];
            }
        }
        if (u == v) return u;
        // 如果 u 和 v 不相同，继续向上移动，直到找到公共祖先
        for (int i = 20; i >= 0; i--) {
            if (fa[u][i] != fa[v][i]) {
                u = fa[u][i];
                v = fa[v][i];
            }
        }
        return fa[u][0];
    }

    // 统计答案，构造差分数组
    static void dfs2(int u, int f) {
        for (int i = head[u]; i != 0; i = next[i]) {
            int v = to[i];
            if (v == f) continue;
            dfs2(v, u);
            power[u] += power[v];
        }
        ans = Math.max(ans, power[u]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        for (int i = 1; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            addEdge(x, y);
        }

        dfs1(1, 0);

        // 点差分
        for (int i = 1; i <= m; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int lca = lca(x, y);
            power[x]++;
            power[y]++;
            power[lca]--;
            power[fa[lca][0]]--;
        }
        dfs2(1, 0);
        System.out.println(ans);
        scanner.close();
    }
}
