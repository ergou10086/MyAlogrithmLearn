package 数据结构.图.template.最短路.Bellman_Ford算法.模板;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;


// 贝尔曼福特算法基于松弛操作的单源点最短路算法
// e[u]存u点的出边的邻点和边权，d[u]存u点到源点的距离
// 初始化，d[s] = 0，d[其他点] = inf
// 执行多轮循环，每轮循环，对所有边都尝试执行一次松弛操作
// 当一轮循环中没有成功的松弛操作时，算法停止
// BF算法最重要的一点是可处理负边权
// 每轮循环都是O（m）的复杂度，最短路的边数最多为n-1，每次松弛都会使得最短路的边数加1，所以时间复杂度是O（mn）
// 如果第n轮循环时候仍然存在可松弛的边，说明从s点出发，能够抵达一个负环，算法可以判负环
// 也就是flag仍然是真的时候，说明存在负环

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}


class Solutions {
    private static final int N = 2010, M = 6010;
    private static final int inf = 0x3f3f3f3f;
    private int n, m, s;
    private class Edge {
        int v, w;
        private Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    private Edge[] edge = new Edge[N];
    private int[] dis = new int [N];

    private boolean bellmanFord(int s){
        // 初始化
        Arrays.fill(dis, inf);
        dis[s] = 0;

        boolean flag = true;  // 标记是否松弛
        // n轮枚举
        for(int i = 1; i <= n; i++){
            flag = false;   // 标志置否
            // 枚举n个点，对每个点枚举出边（走到的那个点）
            for(int u = 1; u <= n; u++){
                // 这个点没有得到更新，先跳过
                if(dis[u] == inf) continue;
                // 对u的所有出边，松弛操作
                for(Edge edge: edge){
                    int v = edge.v;
                    int w = edge.w;
                    // 更短则更新
                    if(dis[u] + w < dis[v]){
                        dis[v] = dis[u] + w;
                        // 标记更新
                        flag = true;
                    }
                }
            }
            if(!flag) break;
        }
        return flag;  // 第n轮为true，则有环
    }

    public Solutions() {
        FastReader reader = new FastReader(System.in);
        n = reader.nextInt();
        m = reader.nextInt();
        s = reader.nextInt();
        for(int i = 0; i < m; i++){
            int u = reader.nextInt();
            int v = reader.nextInt();
            int w = reader.nextInt();
            edge[u] = new Edge(v, w);
        }
        boolean flag = bellmanFord(s);
        if(flag){
            System.out.println("-1");
        }else{
            for(int i = 1; i <= n; i++){
                if(dis[i] == inf){
                    System.out.println("不可达");
                }else{
                    System.out.println(dis[i]);
                }
            }
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
