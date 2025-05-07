package 数据结构.图.template.生成树.最小生成树.Prim算法.P3366_模板_最小生成树.堆优化prim;

import java.io.*;
import java.security.AlgorithmConstraints;
import java.util.*;

// 因为没必要枚举每一个点去判断，我们采用和dijk一样的优化思路
// 采用堆优化

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


class Solutions {
    private final int N = 5010;
    private final int INF = 114514114;
    private int n, m, a, b, c, ans, cnt;
    private int[] dis = new int[N];
    private boolean[] vis = new boolean[N];   // 标记u点是否出圈

    private class Edge {
        // 终点，边权
        int v, w;
        private Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    // 模拟cpp中的pair类型
    private class Pair {
        int d, v;
        Pair(int d, int v) {
            this.d = d;
            this.v = v;
        }
    }
    private ArrayList<Edge>[] e = new ArrayList[N];


    // 用优先队列维护被更新的点的集合
    // 创建一个大根堆q，存储{-距离，点}，把距离取负值，距离最小的元素最大，一定在堆顶
    // 初始化，{0，s}入队，dis[s]=0，d[其他点]=正无穷
    // 从队头弹出距离最小的点u，若u扩展过则跳过，否则打标记
    // 对u的所有邻点的距离执行更新操作，把{-d[v],v}压入队尾
    // 重复2，3操作，直到队列为空
    private boolean prim(int s){
        // 初始化数组
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[s] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> Integer.compare(y.d, x.d));
        pq.offer(new Pair(0, s));

        while(!pq.isEmpty()){
            Pair pair = pq.poll();
            int u = pair.v;
            if (vis[u]) continue;  // 出过队就跳过
            vis[u] = true;   // 标记为出队
            ans += dis[u];
            cnt++;
            for(Edge edge : e[u]){
                int v = edge.v;
                int w = edge.w;
                if(dis[v] > w){
                    dis[v] = w;
                    pq.add(new Pair(-dis[v], v)); // 使用负值实现大根堆
                }
            }
        }
        return cnt == n;
    }

    public Solutions(){
        FastReader sc = new FastReader();
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            e[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            e[a].add(new Edge(b, c));
            e[b].add(new Edge(a, c));
        }

        // 执行Prim算法
        if (!prim(1)) {
            System.out.println("orz");
        } else {
            System.out.println(ans);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}
