package 数据结构.图.subject.最短路.P5837_USACO19DEC_Milk_Pumping_G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 最大化路径流量与路径花费之比
// 路径上的流量等于路径上所有管道的最小流量

public class Main {
    public static void main(String[] args) {
        new Solution();
    }
}



class Solution {
    private static final int inf = 0x7f7f7f7f;
    private static final int N = 3006;
    private static final int stp = (int)Math.pow(10,6);
    private int n, m, ans;
    private class Edge{
        // 终点，边权，下一条边，限制
        int to, cost, next, f;
        private Edge(int to, int cost ,int next ,int f){
            this.to = to;
            this.cost = cost;
            this.next = next;
            this.f = f;
        }
    }
    private int[] head = new int[N];
    private int tot = 0;    // 该边编号
    private Edge[] edge = new Edge[N];
    private int[] dist = new int[N];
    private boolean[] vis = new boolean[N];
    private PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

    private void addEdge(int u, int v, int c, int f){
        edge[++tot].cost = c;
        edge[tot].to = v;
        edge[tot].next = head[u];
        edge[tot].f = f;
        head[u] = tot;
    }


    private void dijkstra(){
        // 固定一个最小流量值，然后只考虑那些所有管道流量都不小于这个固定值的路径，在此基础上用 Dijkstra 算法求出从 1 到 N 的花费最小的路径
        // 满足流量限制条件下的花费最小路径，从1到1000枚举这个最小流量，跑最短路
        for(int flow = 1; flow <= 1000; flow++) {
            for(int j = 1; j <= n; j++){
                dist[j] = inf;
                vis[j] = false;
            }
            dist[1] = 0;
            pq.offer(new int[]{0, 1});  // {距离, 节点编号}

            while(!pq.isEmpty()){
                int[] current = pq.poll();
                int u = current[1];
                if(vis[u]) continue;
                vis[u] = true;   // 标记出队

                for(int i = head[u]; i != 0; i = edge[i].next){
                    // 当前流量小于枚举到的，跳过
                    if(edge[i].f < flow) continue;
                    int v = edge[i].to;
                    if(dist[u] + edge[i].cost < dist[v]){
                        dist[v] = dist[u] + edge[i].cost;
                        if(!vis[v]){
                            pq.offer(new int[]{dist[v], v});
                        }
                    }
                }
            }
            if(dist[n] != inf){
                ans = Math.max(ans, flow * 1000000/dist[n]);// 向下取整
            }
        }


    }




    public Solution() {
        FastReader sc = new FastReader();
        n = sc.nextInt();
        m = sc.nextInt();

        for (int i = 0; i < edge.length; i++) {
            edge[i] = new Edge(0, 0, 0, 0);
        }

        for(int i = 1; i <= m; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            int f = sc.nextInt();
            addEdge(a, b, c, f);
            addEdge(b, a, c, f);
        }

        dijkstra();

        System.out.println(ans);
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
