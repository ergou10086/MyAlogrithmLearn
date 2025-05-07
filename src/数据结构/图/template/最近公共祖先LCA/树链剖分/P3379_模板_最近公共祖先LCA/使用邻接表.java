package 数据结构.图.template.最近公共祖先LCA.树链剖分.P3379_模板_最近公共祖先LCA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 使用邻接表 {
    public static void main(String[] args) {
        new Solutions2();
    }
}


class Solutions2{
    private static final int N = 500010;
    private int n, m, s, a, b, idx;
    private int[] h = new int[N], to = new int[N << 1], ne = new int[N << 1];
    private int[] fa = new int[N], son = new int[N], dep = new int[N], siz = new int[N], top = new int[N];

    private void add(int u, int v) {
        to[++idx] = v;
        ne[idx] = h[u];
        h[u] = idx;
    }


    private void dfs1(int u, int f) {
        fa[u] = f;
        dep[u] = dep[f] + 1;
        siz[u] = 1;
        for (int i = h[u]; i != 0; i = ne[i]) {
            int v = to[i];
            if (v == f) continue;
            dfs1(v, u);
            siz[u] += siz[v];
            if (siz[v] > siz[son[u]]) son[u] = v;
        }
    }


    private void dfs2(int u, int t) {
        top[u] = t;
        if(son[u] == 0) return;
        dfs2(son[u], t);
        for(int i = h[u]; i != 0; i = ne[i]) {
            int v = to[i];
            if (v == fa[u] || v == son[u]) continue;
            dfs2(v, v);
        }
    }


    private int lca(int u, int v) {
        while (top[u] != top[v]) {
            if (dep[top[u]] < dep[top[v]]) {
                int temp = u;
                u = v;
                v = temp;
            }
            u = fa[top[u]];
        }
        return dep[u] < dep[v] ? u : v;
    }

    public Solutions2(){
        FastReader sc = new FastReader();
        n = sc.nextInt();
        m = sc.nextInt();
        s = sc.nextInt();

        // 读入树的边
        for (int i = 1; i < n; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            add(a, b);
            add(b, a);
        }

        // 从根节点 s 开始深度优先搜索
        dfs1(s, 0);
        dfs2(s, s);

        // 处理每个查询，输出 LCA
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            System.out.println(lca(a, b));
        }

    }


    private class FastReader {
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
}
