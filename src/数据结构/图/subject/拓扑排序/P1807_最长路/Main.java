package 数据结构.图.subject.拓扑排序.P1807_最长路;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions {
    // 邻接表存图
    public class Edge {
        int v, w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    private int n, m;
    private int[] inDegree;
    private LinkedList<Integer> tp = new LinkedList<>(); // 存放拓扑排序结果
    private List<List<Edge>> graph;
    private int[] dp;

    // Kahn求拓扑序列
    private boolean topologicalSort() {
        Queue<Integer> queue = new LinkedList<>(); // 存放入度为0的点

        // 找入度为0的加入Q
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // 维护入度为0的集合
        while (!queue.isEmpty()) {
            // 把那些入度为0的起点拿出来一个
            int u = queue.poll();
            tp.add(u);
            // 访问所有邻接的点，减入度
            for (Edge edge : graph.get(u)) {
                int v = edge.v;
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        // 看看是不是有环
        return tp.size() == n;
    }

    private void get_dp() {
        Arrays.fill(dp, Integer.MIN_VALUE); // 初始化为 -∞
        dp[1] = 0; // 起点为0

        for (int u : tp) {
            // 可达就更新
            if (dp[u] != Integer.MIN_VALUE) {
                for (Edge edge : graph.get(u)) {
                    int v = edge.v;
                    int w = edge.w;
                    dp[v] = Math.max(dp[v], dp[u] + w);
                }
            }
        }
    }

    public Solutions() {
        FastReader fs = new FastReader();
        n = fs.nextInt();
        m = fs.nextInt();

        // 初始化 graph、inDegree 和 dp
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        inDegree = new int[n + 1];
        dp = new int[n + 1];

        // 输入边
        for (int i = 0; i < m; i++) {
            int u = fs.nextInt(), v = fs.nextInt(), w = fs.nextInt();
            graph.get(u).add(new Edge(v, w));
        }

        // 求入度
        for (int i = 1; i <= n; i++) {
            for (Edge edge : graph.get(i)) {
                inDegree[edge.v]++;
            }
        }

        boolean reps = topologicalSort();
        get_dp();
        if (!reps || m == 0 || dp[n] == Integer.MIN_VALUE) {
            System.out.println("-1");
        } else {
            System.out.println(dp[n]); // 最长路径
        }
    }

    class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        String next() {
            while (!st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}