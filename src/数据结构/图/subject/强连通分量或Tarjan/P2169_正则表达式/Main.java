package 数据结构.图.subject.强连通分量或Tarjan.P2169_正则表达式;

// 存在 A 到 B 的连接的同时也存在 B 到 A 的连接的话
// 那么 A 和 B 实际上处于同一局域网内，可以通过本地传输，这样花费的传输时间为 0。
// 强连通分量（局域网）内的点之间距离为0

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solution();
    }
}


class Solution{
    private int n, m, cnt, tot, top, scnt;
    // 边的起点终点的长度
    private int[] u = new int[1000005];
    private int[] v = new int[1000005];
    private int[] w = new int[1000005];

    private int[] dfn = new int[200005];   // 时间戳：节点x第一次被访问的顺序
    private int[] low = new int[200005];   // 追溯值：从节点x出发，所能访问到的最早时间戳
    private int[] stk = new int[200005];   // 栈
    private int[] instk = new int[200005];  // 是否在栈中
    private int[] scc = new int[200005];   // 记录强连通分量
    private int[] dis = new int[200005];

    private int[] head = new int[200005];

    // Edge 类表示每条边
    private class Edge {
        int next, len, to;

        public Edge(int next, int len, int to) {
            this.next = next;
            this.len = len;
            this.to = to;
        }
    }
    private Edge[] edge = new Edge[20005];

    // 清空原图
    private void init(){
        for (int i = 1; i <= n; i++) {
            head[i] = 0;
            dfn[i] = low[i] = 0;
            dis[i] = 0;
            instk[i] = 0;
        }
        cnt = 0;
    }


    private void addEdge(int u, int v, int w) {
        edge[++cnt] = new Edge(head[u], w, v);
        head[u] = cnt;
    }


    private void tarjan(int x) {
        // 入x时，盖戳，入栈
        dfn[x] = low[x] = ++tot;
        stk[++top] = x;
        instk[x] = 1;

        for(int e = head[x]; e != 0; e = edge[e].next){
            int v = edge[e].to;
            if(dfn[v] == 0){
                tarjan(v);
                low[x] = Math.min(low[x], low[v]);
            }else if(instk[v] == 1) {
                if(instk[v] == 1){
                    low[x] = Math.min(low[x], dfn[v]);
                }
            }
        }

        if(dfn[x] == low[x]){
            ++scnt;
            int temp;
            do{
                temp = stk[top--];
                scc[temp] = scnt;
                instk[temp] = 0;
            }while(temp != x);
        }
    }


    private void Dijkstra(){
        // 初始化距离为INF
        for (int i = 1; i <= n; i++) {
            dis[i] = 0x3f3f3f3f;
        }
        
        int s = scc[1];  // 获取1号点的颜色
        dis[s] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[]{0, s});

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int u = cur[1];
            int d = cur[0];
            
            if(d != dis[u]) continue;  // 重要的剪枝
            
            for(int e = head[u]; e != 0; e = edge[e].next){
                int v = edge[e].to;
                if(dis[v] > dis[u] + edge[e].len){
                    dis[v] = dis[u] + edge[e].len;
                    pq.add(new int[]{dis[v], v});
                }
            }
        }
    }


    public Solution(){
        FastReader sc = new FastReader();
        n = sc.nextInt();
        m = sc.nextInt();

        // 存储原始边的信息
        for(int i = 1; i <= m; i++){
            u[i] = sc.nextInt();
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
            addEdge(u[i], v[i], w[i]);  // 构建原图
        }

        // 跑Tarjan
        for(int i = 1; i <= n; i++){
            if(dfn[i] == 0){
                tarjan(i);
            }
        }

        // 清空原图
        init();

        // 建立新图
        for(int i = 1; i <= m; i++){
            int up = u[i], vp = v[i], wp = w[i];
            if(scc[up] != scc[vp]){  // 不在同一强连通分量中
                addEdge(scc[up], scc[vp], wp);
            }
        }

        // 跑最短路
        Dijkstra();

        // 输出结果
        System.out.println(dis[scc[n]]);  // 注意是n的颜色的距离
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
