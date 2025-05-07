package 数据结构.图.template.最近公共祖先LCA.树链剖分.P3379_模板_最近公共祖先LCA;


// 树链剖分
// 重儿子：父节点中所有儿子中子树节点数目最多的节点
// 轻儿子：父节点除了重儿子以外的儿子
// 重边：父节点和重儿子连成的边
// 轻边：父节点和轻儿子连成的边
// 重链：由多条重边连接而成的路径

// 整棵树会被剖分为若干条重链
// 轻儿子一定是每条重链的顶点
// 任意一条路径被切分为不超过logn条链

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}


class Solutions {
    private final static int N = 500010;
    private int n, m, s, a, b;
    private int[] fa = new int[N];   // 存u的父节点
    private int[] son = new int[N];  // 存u的重儿子
    private int[] dep = new int[N];  // 存u的深度
    private int[] siz = new int[N];  // 存以u为根的子树的节点数
    private int[] top = new int[N];  // 存u所在重链的顶点
    private List<Integer>[] e = new ArrayList[N];
    // 初始化邻接表
    {
        for (int i = 0; i < N; i++) {
            e[i] = new ArrayList<>();
        }
    }


    // 求出fa，dep，son
    private void dfs1(int u, int f) {
        // 入时更新
        fa[u] = f;
        dep[u] = dep[f] + 1;
        siz[u] = 1;
        //遍历邻点
        for(int v : e[u]) {
            if(v == f) continue; // 防止向上走
            dfs1(v, u);    // 递归
            siz[u] += siz[v];  // 更新
            // 如果出现了父节点子树的节点数小于子节点，更新
            if(siz[v] > siz[son[u]]) {
                son[u] = v;
            }
        }
    }


    // 求出top
    private void dfs2(int u, int t) {
        top[u] = t; // 当前点的链头是传入点的，记录链头
        if(son[u] == 0) return;   // 无重儿子返回（叶节点）
        dfs2(son[u], t);     // 递归，搜重儿子
        // 枚举u的邻点
        for (int v : e[u]) {
            // 等于父节点跳过，不往回走和判断搜过的，走到轻儿子
            if (v == fa[u] || v == son[u]) continue;
            dfs2(v, v);   // 搜轻儿子
        }
    }


    // 求两个节点的最近公共祖先 (LCA)
    private int lca(int u, int v) {
        // 当u和v不在同一条链上时，跳到u和v所在链的链头
        while (top[u] != top[v]) {
            // 保证u链头的深度深，也就是保证u的深度深，往上爬
            if(dep[top[u]] < dep[top[v]]) {
                int temp = u;
                u = v;
                v = temp;
            }
            u = fa[top[u]];
        }
        return dep[u] < dep[v] ? u : v;
    }


    public Solutions() {
        FastReader fastReader = new FastReader();
        n = fastReader.nextInt();
        m = fastReader.nextInt();
        s = fastReader.nextInt();

        for (int i = 1; i < n; i++) {
            a = fastReader.nextInt();
            b = fastReader.nextInt();
            e[a].add(b);
            e[b].add(a);
        }

        // 从根节点 s 开始深度优先搜索
        dfs1(s, 0);
        dfs2(s, s);

        // 处理每个查询，输出 LCA
        for (int i = 0; i < m; i++) {
            a = fastReader.nextInt();
            b = fastReader.nextInt();
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
