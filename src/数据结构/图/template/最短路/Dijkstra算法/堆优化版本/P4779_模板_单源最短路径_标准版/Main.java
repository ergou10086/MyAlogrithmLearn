package 数据结构.图.template.最短路.Dijkstra算法.堆优化版本.P4779_模板_单源最短路径_标准版;

// 如何实现优化
// 每一列更新的点是有限的，没有必要把所有点枚举
// 也没有必要枚举已经出圈的点
// 能更新零点的只有圈内被更新的点，使用优先队列维护

// 创建一个二元组pair的大根堆pq{-距离，点}，把距离取负值，距离最小的元素最大，一定在堆顶
// 初始时，{0，s}入队，d[s]=0,d[其他点]=正无穷
// 每次从队头弹出距离最小的点u，若u扩展过就跳过，否则打标记
// 对u的所有出边进行松弛操作，把{-d[v],v}压入队尾
// 重复如上，直到队列为空

// 优化后时间复杂度为O（mlogm）

import java.io.*;
import java.util.*;

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


class Solution{
    // 定义一个边的结构体类
    static class Edge {
        // 终点，边权
        int v, w;
        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    private final int INF = 2147483647;
    private int n, m, s;
    private List<Edge>[] e;
    private int[] dist;
    private boolean[] vis;


    // 堆优化就是使用优先队列来维护被更新的点的集合
    private void dijkstra(int s) {
        // 初始化
        Arrays.fill(dist, INF);
        // 源点到自身的距离为0
        dist[s] = 0;

        // 创建一个存二元组的大根堆
        // 优先队列，按最短路径进行排序，堆的元素是 {distance, node}
        // 比较器 Comparator.comparingInt(a -> a[0]) 保证队列中的元素是按每个 int[] 数组的第一个元素升序排列。小的在前
        // 默认的优先队列就是小根堆，这样就维护了始终是第一个元素最小的数组在堆顶的情况
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, s}); // {距离, 节点编号}

        // 队列不空
        while(!pq.isEmpty()){
            // 弹出堆顶，poll方法从队列的头部移除并返回队列的第一个元素
            int[] current = pq.poll();
            // 取出点
            int u = current[1];
            // 判定有没有出过队
            if(vis[u]) continue;
            vis[u] = true;   // 标记出队

            // 枚举u的每一个临点
            for (Edge edge : e[u]) {
                int v = edge.v, w = edge.w;
                // 松弛操作
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    // 再把松弛操作更新距离后的点重新入队，出现多次不用管，会自己上浮的
                    // 优先队列中出现的元素个数是O（m）的，跟边条数相关的
                    pq.offer(new int[]{dist[v], v});
                }
            }
        }
    }


    public Solution(){
        FastReader sc = new FastReader();
        n = sc.nextInt();
        m = sc.nextInt();
        s = sc.nextInt();

        // 初始化图
        e = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            e[i] = new ArrayList<>();
        }

        // 输入边
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            e[a].add(new Edge(b, c));
        }

        dist = new int[n + 1];
        vis = new boolean[n + 1];

        dijkstra(s);

        // 使用BufferedWriter进行快速输出
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 1; i <= n; i++) {
            if (dist[i] == INF) {
                try {
                    writer.write(2147483647 + " ");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                try {
                    writer.write(dist[i] + " ");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        try {
            writer.newLine();  // 换行
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        try {
            writer.flush();    // 确保输出
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}


public class Main {
    public static void main(String[] args) throws IOException {
        new Solution();
    }
}
