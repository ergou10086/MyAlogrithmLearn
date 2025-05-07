package 数据结构.图.subject.强连通分量或Tarjan.P2341_USACO03FALL_HAOI2006_受欢迎的牛G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args){
        new Solutions();
    }
}

// 被所有奶牛喜欢的奶牛就是一头明星奶牛
// 也就是说，与所有点相连的点是解，也就是出度为0的强连通分量中的所有点

class Solutions{
    private static final int N = 10010;
    private int n, m, cnt, tot, top, scnt;
    private int dfn[] = new int[N];
    private int low[] = new int[N];
    private int stk[] = new int[N];
    private int instk[] = new int[N];
    private int scc[] = new int[N];
    private int siz[] = new int[N];
    private int dout[] = new int[N];

    private class Edge{
        int next, to;
        private Edge(int next, int to){
            this.next = next;
            this.to = to;
        }
    }
    private Edge[] edges = new Edge[N * 5 * 4];
    private int[] head = new int[N * 5 * 4];


    private void addE(int u, int v){
        edges[++cnt] = new Edge(head[u], v);
        head[u] = cnt;
    }

    private void tarjan(int x){
        dfn[x] = low[x] = ++tot;
        stk[++top] = x;
        instk[x] = 1;

        for(int i = head[x]; i != 0; i = edges[i].next){
            int v = edges[i].to;
            if(dfn[v] == 0){
                tarjan(v);
                low[x] = Math.min(low[x], low[v]);
            } else if (instk[x] == 1) {
                low[x] = Math.min(low[x], dfn[v]);
            }
        }

        if(dfn[x] == low[x]){
            int y;
            ++scnt;
            do{
                y = stk[top--];
                instk[y] = 0;
                scc[y] = scnt;
                siz[scnt]++;
            }while(y != x);
        }
    }


    public Solutions(){
        FastReader fr = new FastReader();
        n = fr.nextInt();
        m = fr.nextInt();
        for(int i = 1; i <= m; i++){
            int u = fr.nextInt();
            int v = fr.nextInt();
            addE(u, v);
        }

        for(int i = 1; i <= n; i++){
            if(dfn[i] == 0){
                tarjan(i);
            }
        }

        for(int p = 1; p <= n; p++){
            // 遍历每一个点并记录出度
            for(int i = head[p]; i != 0; i = edges[i].next){
                int v = edges[i].to;
                if(scc[p] != scc[v]){
                    ++dout[scc[p]];
                }
            }
        }

        int rep = 0, zero_re = 0;   // 记录出度为0的强连通分量中的点数和出度为0的scc个数
        for(int i = 1; i <= scnt; i++){
            if(dout[i] == 0){
                rep = siz[i];
                ++zero_re;
            }
        }
        if(zero_re > 1) rep = 0;

        System.out.println(rep);
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
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt(){
            return Integer.parseInt(next());
        }
    }
}
