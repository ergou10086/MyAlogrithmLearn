package 数据结构.图.subject.最短路.P2349_金字塔;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 一个 n 个点，m 条边的双向图，求一条从前点到终点的最短路并将这条路的最大值取出来，单独加上，求加上后的路的最小值。
// 优先队列优化djikstra，使用邻接多重表

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions {
    private class Node {
        int to;
        int next;
        int cost;
        private Node(int to, int next, int cost) {
            this.to = to;
            this.next = next;
            this.cost = cost;
        }
    }
    private Node[] node = new Node[4040];
    private int[] head = new int[110];
    private int cnt = 0;
    private int[] dist = new int[110];
    private boolean[] vis = new boolean[110];
    private int[] weight = new int[2024];
    private int n, m;
    private int ans = (int) 1e9 + 7;

    // 加边
    private void add(int from, int to, int cost) {
        node[cnt] = new Node(to, head[from], cost);
        head[from] = cnt++;
    }

    // dijkstra
    private void dijkstra(int sp_w) {
        // 初始化
        Arrays.fill(vis, false);
        Arrays.fill(dist, (int) 1e9 + 7);
        int inf = dist[0];
        dist[1] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, 1}); // {距离, 节点编号},起点入堆

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int u = current[1];
            if (vis[u]) continue;
            vis[u] = true;   // 标记出队

            for (int i = head[u]; i != -1; i = node[i].next) {
                int v = node[i].to;
                // 枚举每一条最短路,让这条是最短路的代价最大的路径，再枚举，每次都不能大于它
                if (dist[v] > dist[u] + node[i].cost && node[i].cost <= sp_w) {
                    dist[v] = dist[u] + node[i].cost;
                    pq.offer(new int[]{dist[v], v});
                }
            }
        }
        if (dist[n] != inf) ans = Math.min(ans, dist[n] + sp_w);
    }

    public Solutions() {
        FastReader sc = new FastReader(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = sc.nextInt();
        m = sc.nextInt();
        Arrays.fill(head, -1);  // 初始化head数组

        for (int i = 1; i <= m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            weight[i] = sc.nextInt();
            add(u, v, weight[i]);
            add(v, u, weight[i]);
        }
        // 对每条边执行一次dijkstra，更新答案
        for (int i = 1; i <= m; i++) {
            dijkstra(weight[i]);
        }

        try {
            bw.write(ans + "\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class FastReader {
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

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
