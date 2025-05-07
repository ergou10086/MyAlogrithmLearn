package 数据结构.图.subject.生成树.P1967_NOIP2013提高组_货车运输;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// 由于是跑最大的限重，也就是需要构建最大生成树
// 在最大生成树上的两点之间的查询，求lca
// x,y两点的lca为p，x到y的最小边权就是x到p和y到p取最小

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions{
    private static final int N = 10005, M = 100005, INF = 0x3f3f3f3f;
    private int n, m, q, cnt;
    private int[] fa = new int[N];   // 并查集用的fa数组
    private boolean[] vis = new boolean[N];
    private int[] deep = new int[N];
    private int[][] f = new int[N][21];   // 倍增lca用的祖先
    private int[][] w = new int[N][21];   // 存最大载重
    private int[] head = new int[N];
    // 存原图
    private class Edge{
        int x, y, w;
        public Edge(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }
    }
    private Edge[] edges = new Edge[M];
    // 存最大生成树
    private class BTEdge{
        int to, next, w;
        public BTEdge(int to, int next, int w) {
            this.to = to;
            this.next = next;
            this.w = w;
        }
    }
    private BTEdge[] btedges = new BTEdge[M];


    private void addEdge(int from, int to, int w) {
        btedges[++cnt] = new BTEdge(to, head[from], w);
        head[from] = cnt;
    }

    private int find(int x) {
        if(fa[x] != x) fa[x] = find(fa[x]);
        return fa[x];
    }

    private void kruskal(){
        // 最大生成树，从大到小排序
        Arrays.sort(edges, 1, m + 1, (e1, e2) -> Integer.compare(e2.w, e1.w));
        // 并查集初始化
        for (int i = 1; i <= n; i++) {
            fa[i] = i;
        }

        for(int i = 1; i <= m; i++){
            int xp = find(edges[i].x);
            int yp = find(edges[i].y);
            if(xp != yp){
                // 合并
                fa[xp] = yp;
                // 构建最大生成树，双向加边
                addEdge(xp, yp, edges[i].w);
                addEdge(yp, xp, edges[i].w);
            }
        }
    }

    private void dfs(int p){
        vis[p] = true;

        for(int i = head[p]; i != 0; i = btedges[i].next){
            int to = btedges[i].to;
            if(vis[to]) continue;
            deep[to] = deep[p] + 1;
            f[to][0] = p;
            w[to][0] = btedges[i].w;
            dfs(to);
        }
    }

    private int lca(int x, int y){
        // 不连通，输出-1
        if (find(x) != find(y)) return -1;

        // 保证x结点更深
        if (deep[x] < deep[y]) {
            int temp = x;
            x = y;
            y = temp;
        }

        int ans = INF;

        for(int i = 20; i >= 0; i--){
            if(deep[f[x][i]] >= deep[y]){
                ans = Math.min(ans, w[x][i]);   // 找其中到lca的路径中最小的边权就是这条路的最大载重
                x = f[x][i];
            }
        }

        if(x == y) return ans;

        for (int i = 20; i >= 0; i--) {
            if (f[x][i] != f[y][i]) {
                ans = Math.min(ans, Math.min(w[x][i], w[y][i]));
                x = f[x][i];
                y = f[y][i];
            }
        }
        // 更新此时x,y到公共祖先最大载重，fa[x][0], fa[y][0]即为公共祖先
        ans = Math.min(ans, Math.min(w[x][0], w[y][0]));
        return ans;
    }

    public Solutions(){
        FastReader fr = new FastReader();
        n = fr.nextInt();
        m = fr.nextInt();
        for(int i = 1; i <= m; i++){
            int x = fr.nextInt();
            int y = fr.nextInt();
            int z = fr.nextInt();
            edges[i] = new Edge(x, y, z);
        }

        kruskal();

        // dfs初始化，求深度和w
        for (int i = 1; i <= n; i++) {
            if (!vis[i]) {
                deep[i] = 1;
                dfs(i);
                w[i][0] = INF;
                f[i][0] = i;
            }
        }

        // lca初始化
        for (int i = 1; i <= 20; i++) {
            for (int j = 1; j <= n; j++) {
                f[j][i] = f[f[j][i - 1]][i - 1];
                w[j][i] = Math.min(w[j][i - 1], w[f[j][i - 1]][i - 1]);
            }
        }


        q = fr.nextInt();
        for (int i = 1; i <= q; i++) {
            int x = fr.nextInt();
            int y = fr.nextInt();
            System.out.println(lca(x, y));
        }
    }

    private class FastReader{
        BufferedReader br;
        StringTokenizer st;
        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }
        String next(){
            while(!st.hasMoreTokens()){
                try{
                    st = new StringTokenizer(br.readLine());
                }catch(Exception e){
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
/*
4 3
1 2 4
2 3 3
3 1 1
3
1 3
1 4
1 3
 */