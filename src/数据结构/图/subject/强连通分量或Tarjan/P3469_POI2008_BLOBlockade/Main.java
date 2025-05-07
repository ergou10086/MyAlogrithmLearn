package 数据结构.图.subject.强连通分量或Tarjan.P3469_POI2008_BLOBlockade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}


class Solutions{
    private static final int N = 100005;
    private int n, m, cnt, tot, root;
    private int[] dfn = new int[N];
    private int[] low = new int[N];
    private int[] siz = new int[N];   // 以节点 v 为根的子树包含的节点个数
    private boolean[] cut = new boolean[N];
    private class Edge{
        int to, next;
        private Edge(int to, int next){
            this.to = to;
            this.next = next;
        }
    }
    private Edge[] edges = new Edge[500005];
    private int[] head = new int[N];
    {
        Arrays.fill(edges, new Edge(0, 0));
    }
    private long[] ans = new long[N];

    private void add(int u, int v){
        edges[++cnt] = new Edge(0, 0);
        edges[cnt].to = v;
        edges[cnt].next = head[u];
        head[u] = cnt;
    }

    private void tarjan(int u){
        dfn[u] = low[u] = ++tot;
        siz[u] = 1;
        long child = 0, sum = 0;

        for(int i = head[u]; i != 0; i = edges[i].next){
            int v = edges[i].to;
            if(dfn[v] == 0){
                tarjan(v);
                siz[u] += siz[v];
                low[u] = Math.min(low[u], low[v]);
                // 删除u形成连通块，在割点上，计算贡献
                if(low[v] >= dfn[u]){
                    // 这一棵子树在割点后，对于两个独立连通块，一个连通块中的每个点与另一个连通块中的每个点组成的点对在删除 u 后都不能互相连通了
                    // 原本是连通的，贡献是两个连通块子树大小的乘积，以 v 为根的子树与图其余部分产生的不连通有序点对的数量贡献。
                    ans[u] += (long) siz[v] * (n - siz[v]);
                    child++;
                    sum += siz[v];    // 子树节点和
                    if(u != root || child > 1){
                        cut[u] = true;
                    }
                }
            }else{
                // 从节点 v 出发存在返祖边可以到达比 u 更早被访问的节点
                // 用 dfn[v] 来更新 low[u]
                low[u] = Math.min(low[u], dfn[v]);
            }
        }
        // 不是割点，割后依旧连通，u独立，其余 n - 1 个点作为一个大的连通图
        // 除 u 以外的每个点与 u 组成的点对在删除 u 后都不能连通了，一共有 2 * (n - 1) 个这样的有序点对
        if(!cut[u]){
            ans[u] += (long) 2 * (n - 1);
        }else{
            // u 是割点,sum 已经累计了那些因删除 u 而成为独立连通块的子树节点个数之和。
            // (n - sum - 1) 表示的是除了这些会成为独立连通块的子树节点之外，通过返祖边与 u 的祖先节点相连的那部分节点个数（这里减去 1 是要去掉节点 u 自身）也就是去掉了割u后，产生的新连通块剩下的点减自身
            // (sum + 1)是独立连通块的子树节点个数之和加自身
            // (n - sum - 1) * (sum + 1) 表示的是独立连通块中的每个点与剩余点组成的有序点对个数
            // (n - 1) 是除了 u 以外的每个点与 u 组成的有序点对个数
            ans[u] += (n - sum - 1) * (sum + 1) + (n - 1);
        }
    }


    public Solutions(){
        FastReader fs = new FastReader();
        n = fs.nextInt();
        m = fs.nextInt();
        for(int i = 1; i <= m; i++){
            int u = fs.nextInt(), v = fs.nextInt();
            add(u, v);
            add(v, u);
        }
        tarjan(1);
        for(int i = 1; i <= n; i++){
            System.out.println(ans[i]);
        }
    }


    private class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next(){
            while(st == null || !st.hasMoreElements()){
                try {
                    st = new StringTokenizer(br.readLine());
                }catch (IOException e){
                    System.out.println("Nothing");
                }
            }
            return st.nextToken();
        }

        int nextInt(){
            return Integer.parseInt(next());
        }
    }
}
