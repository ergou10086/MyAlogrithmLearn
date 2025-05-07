package 数据结构.图.template.最短路.SPFA算法.P3385_模板_负环;

// spfa算法是Bellman_Ford算法的改进
// 只有本轮被更新的点，其出边才有可能引起下一列的松弛操作，因此用队列来维护被更新的点的集合
// vis[u]标记u点是否在队内，cnt[v]记录边数，判负环
// 算法流程，其实有点像bfs
// 初始化，s入队，标记源点在队内，d[s]=0,d[其他点] = inf
// 从队头弹出u点，标记u不在队内
// 枚举u的所有出边，执行松弛操作，记录从s走到v的边数，并判负环，如果v不在对内则把v压入队尾，打上标记
// 重复2，3操作，直到队空

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions {
    private static final int inf = 0x3f3f3f3f;
    private static final int N = 2010, M = 6010;
    private int n, m;
    // 图的邻接表存储
    private int[] to = new int[M];   // to数组用于存储每条边指向的节点编号
    private int[] ne = new int[M];   // ne数组用于存储与当前边同起点的上一条边在数组中的下标，用于构建邻接表的链式结构
    private int[] w = new int[M];    // w数组用于存储每条边的权值
    private int[] h = new int[M];    // 表头数组，h[i]存储的是以节点i为起点的第一条边在边数组中的下标
    private int tot;
    private int[] dist = new int[N];
    private boolean[] vis = new boolean[N];  // 是否在队内
    private int[] cnt = new int[N];   // 边数

    private void add(int u, int v, int value) {
        to[++tot] = v;
        w[tot] = value;
        ne[tot] = h[u];
        h[u] = tot;
    }

    // 判负环
    public boolean spfa() {
        // 初始化
        Arrays.fill(dist, inf);
        Arrays.fill(vis, false);
        Arrays.fill(cnt, 0);

        // 将起点1加入队列，标记其在队内，起点距离设为0
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        vis[1] = true;
        dist[1] = 0;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            vis[u] = false;
            // 遍历以u为起点的所有边
            for (int i = h[u]; i != 0; i = ne[i]) {
                int v = to[i];
                // 新路径更短，就松弛
                if (dist[v] > dist[u] + w[i]) {
                    dist[v] = dist[u] + w[i];
                    // 记录边数
                    cnt[v] = cnt[u] + 1;
                    // 如果边数达到或超过节点数，说明存在负环
                    if (cnt[v] >= n) return true;
                    if (!vis[v]) {
                        queue.add(v);
                        vis[v] = true;
                    }
                }
            }
        }
        return false;
    }

    public Solutions() {
        FastReader reader = new FastReader(System.in);
        int t = reader.nextInt();
        while(t-- > 0) {
            tot = 0;
            Arrays.fill(h, 0);
            n = reader.nextInt();
            m = reader.nextInt();
            for(int i = 1; i <= m; i++){
                int u = reader.nextInt();
                int v = reader.nextInt();
                int w = reader.nextInt();
                add(u, v, w);
                if(w >= 0) add(v, u, w);
            }
            System.out.println(spfa()? "YES" : "NO");
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
