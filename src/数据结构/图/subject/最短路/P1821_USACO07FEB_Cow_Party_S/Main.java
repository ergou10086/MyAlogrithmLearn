package 数据结构.图.subject.最短路.P1821_USACO07FEB_Cow_Party_S;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}



class Solutions{
    private int n, m, x, sum, cnt = 0;
    private class Edge{
        int v, w, next;
        private Edge(int v, int w, int next){
            this.v = v;
            this.w = w;
            this.next = next;
        }
    }
    private int[] ans = new int[1005];
    private Edge[] edges = new Edge[100086];
    private int[] heads = new int[1005];
    private int[] dist = new int[1005];
    private boolean[] vis = new boolean[1005];


    private void addEdge(int u, int v, int w){
        edges[++cnt] = new Edge(v, w, heads[u]);
        heads[u] = cnt;
    }

    private void dijkstra(int s){
        // 初始化
        Arrays.fill(dist, 0x3f3f3f3f);
        Arrays.fill(vis, false);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, s});
        dist[s] = 0;

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int u = cur[1];

            if(vis[u]) continue;
            vis[u] = true;

            for(int i = heads[u]; i != 0; i = edges[i].next){
                int v = edges[i].v;
                int w = edges[i].w;
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    pq.offer(new int[]{dist[v], v});
                }
            }
        }
    }


    public Solutions(){
        FastReader sc = new FastReader();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = sc.nextInt();
        m = sc.nextInt();
        x = sc.nextInt();
        int[] u = new int[100086];
        int[] v = new int[100086];
        int[] w = new int[100086];

        Arrays.fill(edges, null);

        for(int i = 1; i <= m; i++){
            u[i] = sc.nextInt();
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
            addEdge(u[i], v[i], w[i]);
        }
        dijkstra(x);
        for (int i = 1; i <= n; i++) ans[i] = dist[i];
        // 清空，为回家做准备
        cnt = 0;
        Arrays.fill(heads, 0);
        //Arrays.fill(edges, null);
        Arrays.fill(vis, false);
        Arrays.fill(dist, 0);
        for(int i = 1; i <= m; i++){
            addEdge(v[i], u[i], w[i]);
        }
        dijkstra(x);
        for(int i = 1; i <= n; i++){
            ans[i] += dist[i];
            sum = Math.max(ans[i], sum);
        }

        System.out.println(sum);

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
