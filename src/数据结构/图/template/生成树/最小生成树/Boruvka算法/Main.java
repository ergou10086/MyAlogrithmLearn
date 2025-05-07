package 数据结构.图.template.生成树.最小生成树.Boruvka算法;

// 对于最小生成树，我们一般将这类问题进行分类，对于稀松图选择Kruskal求解，对于稠密图则使用Prim算法进行求解。
// 对于某些毒瘤的问题，边的数量极其大，而边集内部又存在各种规律可能需要套上各种数据结构加以优化
// 在边具有较多特殊性质的问题中，Boruvka 算法具有优势
// Kruskal和Prim并不能很好的嵌合进这些数据结构，此时我们引入Boruvka算法，该算法的思想是前两种算法的结合
// 思想是一开始所有点看做独立子集，每次遍历边找到两个集合(连通块)之间连接的最短边，不断扩大集合(连通块)直到所有点合并为一个集合(连通块)。
// 基本原理
// 在Brouvka算法中，我们在一开始将所有点视为独立子集，每次我们找到两个集合(即为连通块)之间的最短边，然后扩展连通块进行合并。
// Boruvka算法将求解最小生成树的问题分解为求连通块间最小边的问题
// 可以发现
// 生成树中所有顶点必然是连通的，所以两个不相交集必须连接起来才能构成生成树，而且所选择的连接边的权重必须最小，才能得到最小生成树


import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}



class Solutions {
    private static final int M = 1000010;
    private Edge[] edge = new Edge[M];
    private int[] f;     // 并查集用fa数组
    private int[] best;  // best 数组用于在 Boruvka 算法执行过程中记录每个连通分量（集合）选出的最优边
    private boolean[] vis;  // vis 数组用于标记边是否已经被选择过
    private int n;   // 节点数
    private int m;   // 边数

    // 内部类，表示图中的边，包含两个端点和边的权重
    private static class Edge {
        int u;
        int v;
        int w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    // 比较两条边的权重等情况，排序用
    private boolean cmp(int index1, int index2) {
        if (index2 == 0) {
            return true;
        }
        if (edge[index1].w != edge[index2].w) {
            return edge[index1].w < edge[index2].w;
        }
        return index1 < index2;
    }

    // 并查集的查找操作，找到节点所在集合的根节点
    private int find(int x) {
        if (f[x] != x) {
            f[x] = find(f[x]);
        }
        return f[x];
    }

    private int boruvka() {
        // 各种初始化
        Arrays.fill(vis, false);
        int ans = 0;   // 记录最小生成树的边权重总和
        int cnt = 0;   // 记录已经选择的边的数量
        boolean status = true;  // 当前轮次是否发现可加入最小生成树的边

        while (status) {
            status = false;  // 先置f，方便退出
            // 遍历边
            for (int i = 0; i < m; i++) {
                // 对于每条未被选择的边
                if (!vis[i]) {
                    // 获取根节点
                    int uu = find(edge[i].u);
                    int vv = find(edge[i].v);
                    // 边两个端点所在集合的根节点同属于一个集合就跳过
                    if (uu == vv) {
                        continue;
                    }
                    // 判断更优，更新best数组，权重更小为更优
                    if (cmp(i, best[uu])) {
                        best[uu] = i;
                    }
                    if (cmp(i, best[vv])) {
                        best[vv] = i;
                    }
                }
            }

            // 遍历点，进行合并，构造最小生成树
            for (int i = 1; i <= n; i++) {
                // 存在最优边且该最优边未被选择的节点
                if (best[i] != 0 && !vis[best[i]]) {
                    // 表示有可被加入的边，并把选边数量加一，将这条边的权重累加到 ans，作为答案的一部分
                    status = true;
                    cnt++;
                    ans += edge[best[i]].w;
                    vis[best[i]] = true;   // 去重
                    // 把两个集合合并，因为两个点代表的连通块之间的最短边被找到了，已经是最小生成树的一部分了
                    int uu = find(edge[best[i]].u);
                    int vv = find(edge[best[i]].v);
                    f[uu] = vv;
                }
            }
        }
        // 直到加入了n-1条边，n个点的最小生成树有n-1边，唯一性，结束
        if (cnt == n - 1) {
            return ans;
        }
        // 不连通，找不到
        // 算法复杂度是 O(Elog V) 的
        return -1;
    }

    public Solutions() {
        FastReader sc = new FastReader(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        n = sc.nextInt();
        m = sc.nextInt();
        f = new int[n + 1];
        best = new int[n + 1];
        vis = new boolean[M];
        // 读图
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            edge[i] = new Edge(u, v, w);
        }
        // 初始化fa
        for (int i = 1; i <= n; i++) {
            f[i] = i;
        }

        int result = boruvka();
        pw.println(result);
        pw.close();
    }
}



class FastReader {

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