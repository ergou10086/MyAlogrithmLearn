package 数据结构.图.subject.生成树.P9235_蓝桥杯2023省A_网络稳定性;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}
// 最小瓶颈生成树板子题
// 记这条路径的稳定性为其经过所有连接中稳定性最低的那个。
// 关键路径？？？生成树，kruskal重构生成树
// 记设备 A 到设备 B 之间通信的稳定性为 A 至 B 的所有可行路径的稳定性中最高的那一条
// 要查询两点之间的通信稳定性
// 也就是给一个有边权的无向图，对于给定的两个顶点 u 和 v，我们要找出从 u 到 v 的所有可能路径。
// 在这些路径中，每条路径上都有一个边权值最大，找到其中最小值
// 其实也就是找到最短路中的最大的一条边的权值
// 通信稳定性是最大的，找两点之间路径长度最小值最大的路径，然后输出最小的值。
// 考虑到你要让最小值尽可能大，而且还是构建一棵树，就能发现是让你构造一棵最大生成树。
// kruskal生成树+LCA最近公共祖先
// 将边按边权从大到小排序后依次插入原图，构建最大生成树，询问两个点在什么时候会连通
// kruskal重构生成树，就是在 kruskal 建最大生成树的过程中额外建点、赋权。

class Solutions{
    private static final int N = 400010;
    private int n, m, q;
    // 求lca用的倍增数组,深度数组
    private int[][] f = new int[N][21];
    private int[] dep = new int[N];
    private boolean[] vis = new boolean[N];
    // kruskal用并查集的fa
    private int[] fa = new int[N];
    // 最大生成树数组
    private int[] res = new int[N];

    private class Edge{
        int to, ne, w;
        public Edge(int to, int ne, int w) {
            this.to = to;
            this.ne = ne;
            this.w = w;
        }
    }
    private List<Integer>[] edges = new ArrayList[N];
    private Comparator<Edge> comparator = (e1, e2) -> e2.w - e1.w;

    private int find(int x){
        if(fa[x] == x) return x;
        return fa[x] = find(fa[x]);
    }

    private void dfs(int x, int fat){
        vis[x] = true;
        dep[x] = dep[fat] + 1;
        f[x][0] = fat;
        for(int i = 1; i <= 20; i++){
            f[x][i] = f[f[x][i-1]][i-1];
        }

        // 枚举x点的儿子
        for (int y : edges[x]) {
            // 判重，只允许向下深搜
            if (y != fat) {
                dfs(y, x);
            }
        }
    }

    // lca获取树中任意两个节点之间路径上的边权最小值。
    private int lca(int u, int v){
        if(dep[u] < dep[v]){
            int temp = u;
            u = v;
            v = temp;
        }

        for (int i = 20; i >= 0; i--) {
            if(dep[f[u][i]] >= dep[v]){
                u = f[u][i];
            }
        }

        if(u == v) return u;

        for (int i = 20; i >= 0; i--) {
            if (f[u][i] != f[v][i]) {
                u = f[u][i];
                v = f[v][i];
            }
        }
        return f[u][0];
    }

    public Solutions() {
        FastReader sc = new FastReader(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        q = sc.nextInt();

        // 初始化
        for(int i = 1; i <= n + m; i++){
            fa[i] = i;
            edges[i] =  new ArrayList<>();
        }

        Edge[] e = new Edge[m + 1];
        for(int i = 1; i <= m; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            e[i] = new Edge(u, v, w);
        }

        Arrays.sort(e, 1, m + 1, comparator);

        // 重新开个虚拟节点，构建最大生成树的额外索引用
        int cur = n;
        for (int i = 1; i <= m; ++i) {
            int u = e[i].to;
            int v = e[i].ne;
            int w = e[i].w;
            u = find(u);
            v = find(v);
            // 判断两个节点是否在同一个连通分量中，也就是根是否相同，如果不同需要合并
            // 按边权从大到小考虑边。当遇到两个端点属于不同连通分量的边时，将这两个连通分量合并，有助于保证最终生成树中任意两点间路径上的最大边权值尽可能小。
            if(u != v){
                ++cur;
                fa[u] = fa[v] = cur;
                // 构建最大生成树的结构
                edges[cur].add(u);
                edges[cur].add(v);
                res[cur] = w;
            }
        }

        // 对于每一颗树进行LCA预处理
        for (int i = cur; i >= 1; --i) {
            if (!vis[i]) {
                dfs(i, 0);
            }
        }

        // 初始化第一轮lca
        for (int j = 1; j < 20; ++j) {
            for (int i = 1; i <= cur; ++i) {
                f[i][j] = f[f[i][j - 1]][j - 1];
            }
        }

        while (q-- > 0) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            // u，v不是同一棵树
            if(find(u) != find(v)){
                System.out.println("-1");
            } else {
                // 在最大生成树中求得lca
                System.out.println(res[lca(u, v)]);
            }
        }

    }





    private class FastReader {

        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public FastReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public String nextLine() {
            String str = null;
            try {
                str = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public Double nextDouble() {
            return Double.parseDouble(next());
        }

        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }
    }
}