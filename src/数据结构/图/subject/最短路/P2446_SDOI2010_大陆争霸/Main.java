package 数据结构.图.subject.最短路.P2446_SDOI2010_大陆争霸;

// m条单向边连接n个城市，通过每边要wi单位时间。从1开始，依次摧毁城市
// 仅仅摧毁不用时间，直到摧毁n结束。
// 部分城市被别的城市保护，只有保护它的城市被摧毁后，才能摧毁这个城市。
// 求结束的最短时间。

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions {
    private int n, m, tot_e, tot_p;
    private static final int N = 3001, M = 200001;
    private boolean[] vis = new boolean[N];
    private int[] dis = new int[N];
    // 进入该城市的时间和到达该城市的时间
    private int[] into = new int[N];
    private int[] arrive = new int[N];
    // 记录被保护的城市的入度
    private int[] indege = new int[N];

    // 存储城市之间的情况，原图
    private class Edge {
        int to, next, wei;

        Edge(int to, int next, int wei) {
            this.to = to;
            this.next = next;
            this.wei = wei;
        }
    }
    private int[] head_edge = new int[N];
    private Edge[] edges = new Edge[M];

    // 存储保护城市之间的关系
    private class Edge_pro {
        int to, next;

        Edge_pro(int to, int next) {
            this.to = to;
            this.next = next;
        }
    }
    private int[] head_pro = new int[N];
    private Edge_pro[] edges_p = new Edge_pro[M];

    // dijk堆中的节点
    private class QNode {
        int key, len;

        QNode(int key, int len) {
            this.key = key;   // 节点编号
            this.len = len;   // 距离
        }
    }
    private PriorityQueue<QNode> q = new PriorityQueue<>(Comparator.comparingInt(a -> a.len));


    private void dijkstra(int s) {
        Arrays.fill(dis, 0x3f3f3f3f);
        Arrays.fill(arrive, 0x3f3f3f3f);  // 统一在这里初始化，避免重复且不一致的初始化情况
        dis[s] = into[s] = arrive[s] = 0;
        indege[s] = 0;
        q.offer(new QNode(s, 0));

        while (!q.isEmpty()) {
            QNode cur = q.poll();
            int u = cur.key;
            if (vis[cur.key]) continue;
            vis[cur.key] = true;

            // 正常的在原图跑最短路
            for (int k = head_edge[u]; k!= 0; k = edges[k].next) {
                int v = edges[k].to;
                int w = edges[k].wei;
                if (dis[u] + w < arrive[v]) {
                    arrive[v] = dis[u] + w;
                    // 入度为0，可以被摧毁，更新进入城市的时间
                    if (indege[v] == 0) {
                        dis[v] = Math.max(into[v], arrive[v]);
                        // 去干下一个地方
                        q.add(new QNode(v, dis[v]));
                    }
                }
            }

            for (int k = head_pro[u]; k > 0; k = edges_p[k].next) {
                // 对于每条保护关系边，获取它所保护的城市 v
                int v = edges_p[k].to;
                // dis[u]不为0，说明结界保护器u的摧毁时间得出，更新能够进入v也就是所有保护器全部爆炸的时候
                into[v] = Math.max(into[v], dis[u]);
                // 逐层删去保护关系
                indege[v]--;
                // 进入v城市开始爆炸
                if (indege[v] == 0) {
                    // 更新距离
                    dis[v] = Math.max(into[v], arrive[v]);
                    q.add(new QNode(v, dis[v]));
                }
            }
        }
    }


    public Solutions() {
        FastReader fs = new FastReader(System.in);
        n = fs.nextInt();
        m = fs.nextInt();
        for (int i = 1; i <= m; i++) {
            int ui = fs.nextInt();
            int vi = fs.nextInt();
            int wi = fs.nextInt();
            edges[++tot_e] = new Edge(vi, head_edge[ui], wi);
            head_edge[ui] = tot_e;  // 修改为存储边的索引
        }
        for (int i = 1; i <= n; i++) {
            int li = fs.nextInt();
            if (li!= 0) {
                while (li-- > 0) {  // 修改循环条件
                    int pro_c = fs.nextInt();
                    ++indege[i];  // 增加当前城市的入度
                    edges_p[++tot_p] = new Edge_pro(i, head_pro[pro_c]);
                    head_pro[pro_c] = tot_p;
                }
            }
        }

        dijkstra(1);
        System.out.println(dis[n]);
    }


    private class FastReader {

        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public FastReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null ||!tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public String nextLine() {
            String str = null;
            try {
                str = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public Double nextDouble() {
            return Double.parseDouble(next());
        }

        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }
    }
}

/*
6 6
1 2 1
1 4 3
2 3 1
2 5 2
4 6 2
5 3 2
0
0
0
1 5
0
2 3 5
 */