package 数据结构.树.template.树链刨分.Luogu_P3384_模板_轻重链剖分_树链剖分;

import javax.sound.midi.Synthesizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 树链剖分 - 线段树维护 - 树上修改与查询
// 重儿子：父节点中所有儿子中子树节点数目最多的节点
// 轻儿子：父节点除了重儿子以外的儿子
// 重边：父节点和重儿子连成的边
// 轻边：父节点和轻儿子连成的边
// 重链：由多条重边连接而成的路径

// 整棵树会被剖分为若干条重链
// 轻儿子一定是每条重链的顶点
// 任意一条路径被切分为不超过logn条重链


public class Main {
    public static void main(String[] args) {
        new Solution();
    }
}



class Solution {
    private final static int N = 100010;
    private ArrayList<Integer>[] e = new ArrayList[N];
    private int[] w = new int[N];
    private int[] fa = new int[N];     // 存u的父节点
    private int[] son = new int[N];    // 存u的重儿子
    private int[] dep = new int[N];    // 存u的深度
    private int[] siz = new int[N];    // 存以u为根的子树节点数
    private int[] top = new int[N];    // 存u所在重链的顶点
    private int[] id = new int[N];     // 存u剖分后的新编号
    private int[] nw = new int[N];     // 存新编号在树中所对应结点的权值
    private int n, m, a, b, root, p, cnt;

    // 线段树
    private class SegmentTree {
        int l, r;
        long add, sum;
    }

    private SegmentTree[] tr = new SegmentTree[N * 4];

    {
        for (int i = 0; i < tr.length; i++) {
            tr[i] = new SegmentTree();
        }
    }


    // 搞出fa，dep，siz，son
    private void dfs1(int u, int f){
        fa[u] = f;
        dep[u] = dep[f] + 1;
        siz[u] = 1;
        for(int v : e[u]){
            if(v == f) continue;
            dfs1(v, u);
            siz[u] += siz[v];
            // 如果出现了父节点子树的节点数小于子节点对应的，更新u的重儿子
            if(siz[son[u]] < siz[v]){
                son[u] = v;
            }
        }
    }


    // 搞出top，id，nw
    private void dfs2(int u, int t){
        top[u] = t;
        id[u] = ++cnt;    // 给树上节点编号
        nw[cnt] = w[u];   // 节点的权值映射
        if (son[u] == 0) return;   // 叶节点无重儿子，返回
        dfs2(son[u], t);   // 递归，搜重儿子
        for(int v : e[u]){
            // 找轻儿子
            if(v == fa[u] || v == son[u]) continue;
            dfs2(v, v);
        }
    }

    private void push_up(int u) {
        tr[u].sum = tr[u * 2].sum + tr[u * 2 + 1].sum;
    }

    private void push_down(int u) {
        if (tr[u].add != 0){
            tr[u * 2].sum += tr[u].add * (tr[u * 2].r - tr[u * 2].l + 1);
            tr[u * 2 + 1].sum += tr[u].add * (tr[u * 2 + 1].r - tr[u * 2 + 1].l + 1);
            tr[u * 2].add += tr[u].add;
            tr[u * 2 + 1].add += tr[u].add;
            tr[u].add = 0;
        }
    }

    private void build(int u, int l, int r) {
        tr[u].l = l;
        tr[u].r = r;
        tr[u].add = 0;
        // 线段树每个节点对应的编号对应的是旧树上的权值
        tr[u].sum = nw[r];
        if (l == r) return;
        int mid = (l + r) >> 1;
        build(u * 2, l, mid);
        build(u * 2 + 1, mid + 1, r);
        push_up(u);
    }


    // 查询线段树
    private long query(int u, int l, int r) {
        if(l <= tr[u].l && tr[u].r <= r){
            return tr[u].sum;
        }
        push_down(u);
        int mid = (tr[u].l + tr[u].r) >> 1;
        long ans = 0;
        if(l <= mid) ans += query(u * 2, l, r);
        if(r > mid) ans += query(u * 2 + 1, l, r);
        return ans;
    }


    // 查询路径,带入树上节点的编号
    private long query_path(int u, int v) {
        long res = 0;
        // 只要两个节点链的顶端不相等
        while(top[u] != top[v]){
            // 比较两个节点链的顶端深度，保证让u指向深度更深的链
            if(dep[top[u]] < dep[top[v]]) {
                int temp = u;
                u = v;
                v = temp;
            }
            // 范围是u所在重链的顶点到自己的剖分后的新编号
            // 这个范围就是找u所在重链的编号范围，在线段树中再找这段重链的和
            res += query(1, id[top[u]], id[u]);
            // 让u指向u所在重链的顶点的父节点，也就是向上爬
            u = fa[top[u]];
        }
        // 处理最后一段，此时上升到了一条重链上，单独处理
        if(dep[u] < dep[v]){
            int temp = u;
            u = v;
            v = temp;
        }
        res += query(1, id[v], id[u]);
        return res;
    }

    // 查询子树
    long query_tree(int u) {
        return query(1, id[u], id[u] + siz[u] - 1);
    }


    // 线段树修改
    private void change(int u, int l, int r, int k) {
        if(l <= tr[u].l && tr[u].r <= r){
            tr[u].add += k;
            tr[u].sum += (long)k * (tr[u].r - tr[u].l + 1);
            return;
        }
        push_down(u);
        int mid = (tr[u].l + tr[u].r) >> 1;
        if(l <= mid) change(u * 2, l, r, k);
        if(r > mid) change(u * 2 + 1, l, r, k);
        push_up(u);
    }


    // 修改路径
    private void change_path(int u, int v, int k) {
        // 只要两个节点链的顶端不相等,也就是不在一条重链上
        while (top[u] != top[v]) {
            // 比较两个节点链的顶端深度，保证让u指向深度更深的链
            if (dep[top[u]] < dep[top[v]]) {
                int temp = u;
                u = v;
                v = temp;
            }
            // 修改 u所在重链的顶点到自己的剖分后的新编号 这段区间的值
            change(1, id[top[u]], id[u], k);
            // 让u指向u所在重链的顶点的父节点，也就是向上爬
            u = fa[top[u]];
        }
        // 处理最后一段，此时上升到了一条重链上，单独处理
        if (dep[u] < dep[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        change(1, id[v], id[u], k);
    }

    // 修改子树
    private void change_tree(int u, int k) {
        change(1, id[u], id[u] + siz[u] - 1, k);
    }


    public Solution(){
        FastReader sc = new FastReader();
        n = sc.nextInt();
        m = sc.nextInt();
        root = sc.nextInt();
        p = sc.nextInt();

        for (int i = 1; i <= n; i++) w[i] = sc.nextInt();

        for (int i = 0; i < n - 1; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            if (e[a] == null) e[a] = new ArrayList<>();
            if (e[b] == null) e[b] = new ArrayList<>();
            e[a].add(b);
            e[b].add(a);
        }

        dfs1(root, 0);
        dfs2(root, root);

        build(1, 1, n);

        while (m-- > 0) {
            int t = sc.nextInt();
            int u = sc.nextInt();
            if (t == 1) {
                int v = sc.nextInt();
                int k = sc.nextInt();
                change_path(u, v, k);
            } else if (t == 3) {
                int k = sc.nextInt();
                change_tree(u, k);
            } else if (t == 2) {
                int v = sc.nextInt();
                System.out.println(query_path(u, v) % p);
            } else {
                System.out.println(query_tree(u) % p);
            }
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

        String nextLine() {
            try {
                return br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}