package 数据结构.图.subject.最短路.P1186_玛丽卡;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

// 给出一张 n 个点 m 条边的带权的无向图。删除一条边，使得 n 到 1 的最短路的长度最大，输出这个最大长度。
// 先求起点到终点的最短路，如果断路不在原来的最短路上，那对结果不影响
// 所以我们枚举最短路上的每一条边，删掉后，然后求最短路，然后取最大就行

class Solutions {
    private static final int inf = 0x3f3f3f3f;
    private class node {
        int from, to, next, cost;
        public node() {}
        public node(int from, int to, int next, int cost) {
            this.from = from;
            this.to = to;
            this.next = next;
            this.cost = cost;
        }
    }
    private int n, m, tot, ans;  // 表示城市的数量以及城市间道路的数量
    private node[] edges = new node[1000009];
    private int[] head = new int[1009];    // 表头数组，h[i]存储的是以节点i为起点的第一条边在边数组中的下标
    private boolean[] visited = new boolean[1009];
    private int[] cnt = new int[1009];   // 边数
    private int[] dist = new int[1009];  // 距离
    private int[] pre = new int[1009];  // 枚举最短路的边
    private int delu, delv;  // 删除的边

    private void addEdge(int x, int y, int v) {
        tot++;
        edges[tot] = new node(x, y, head[x], v);
        head[x] = tot;
    }

    private void spfa() {
        // 初始化
        Arrays.fill(dist, inf);  // 修改为初始化为 inf
        Arrays.fill(visited, false);
        Arrays.fill(cnt, 0);  // 重置边数记录

        // 将起点1加入队列，标记其在队内，起点距离设为0
        Deque<Integer> queue = new LinkedList<>();
        queue.addLast(1);
        visited[1] = true;
        dist[1] = 0;

        while (!queue.isEmpty()) {
            int u = queue.pollFirst();
            visited[u] = false;

            // 遍历以u为起点的所有边
            for (int i = head[u]; i != 0; i = edges[i].next) {
                int v = edges[i].to;
                // 新路径更短，就松弛
                if ((u == delu && v == delv) || (v == delu && u == delv)) continue;  // 跳过删除的边
                if (dist[v] > dist[u] + edges[i].cost) {
                    dist[v] = dist[u] + edges[i].cost;
                    if (ans == 0) {
                        pre[v] = u;
                    }
                    // 记录边数
                    cnt[v] = cnt[u] + 1;
                    // 有负环，润，虽然没有负环
                    if (cnt[v] >= n) return;
                    if (!visited[v]) {
                        queue.add(v);
                        visited[v] = true;
                    }
                }
            }
        }
    }

    public Solutions() {
        FastReader sc = new FastReader();
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 1; i <= m; i++) {
            edges[i] = new node();
            int x = sc.nextInt();
            int y = sc.nextInt();
            int v = sc.nextInt();
            addEdge(x, y, v);
            addEdge(y, x, v);
        }
        // 第一次spfa确定最短路
        spfa();

        ans = dist[n];

        for (int i = n; i != 1; i = pre[i]) {
            // 开始删边
            delu = pre[i];
            delv = i;
            spfa();
            ans = Math.max(dist[n], ans);
        }

        System.out.println(ans);
    }

    class FastReader {
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