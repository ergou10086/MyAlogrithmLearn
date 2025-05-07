package 数据结构.图.subject.生成树.P12158_蓝桥杯2025省JavaB_爆破;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions {
    private int n;

    private class cirlce implements Comparable<cirlce> {
        int x, y, r;

        public cirlce() {
        }

        public cirlce(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }

        @Override
        public int compareTo(cirlce o) {
            return this.x - o.x;
        }
    }

    private cirlce[] cirlces = new cirlce[5005];

    private double Getdist(int u, int v) {
        return Math.sqrt((cirlces[u].x - cirlces[v].x) * (cirlces[u].x - cirlces[v].x) + (cirlces[u].y - cirlces[v].y) * (cirlces[u].y - cirlces[v].y));
    }

    private double prim() {
        boolean[] visited = new boolean[n];   // 标记每个顶点是否已经被加入到最小生成树中
        double[] dist = new double[n];   // 距离数组
        // 初始化
        Arrays.fill(visited, false);
        Arrays.fill(dist, Double.MAX_VALUE);
        // 选择0号为起点，并且设为距离0
        dist[0] = 0.0;
        double sum = 0.0;   // 最小生成树的边总权重

        for (int i = 0; i < n; i++) {
            int u = -1;
            double minDist = Double.MAX_VALUE;   // 最小距离指针
            // 找到距离最小生成树最近且未被访问的顶点
            for (int j = 0; j < n; j++) {
                if(!visited[j] && dist[j] < minDist){
                    minDist = dist[j];
                    u = j;
                }
            }
            // 没有点可以移出去了，说明全部加入
            if(u == -1){
                break;
            }
            visited[u] = true;
            sum += minDist;

            for(int v = 0; v < n; v++){
                if(!visited[v]){
                    double w;
                    double spcr = Getdist(u, v);
                    // 圆心之间距离小于等于半径之和，相交相切
                    if(spcr <= cirlces[u].r + cirlces[v].r){
                        w = 0.0;   // 不需要搭桥
                    }else{
                        w = spcr - (cirlces[u].r + cirlces[v].r);
                    }
                    // 松弛操作
                    if(w < dist[v]){
                        dist[v] = w;
                    }
                }
            }
        }
        return sum;
    }

    public Solutions() {
        FastReader sc = new FastReader();
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            cirlces[i] = new cirlce(sc.nextInt(), sc.nextInt(), sc.nextInt());
        }

        double sum = prim();
        System.out.printf("%.2f", sum);
    }


    class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        public String next() {
            while (!st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
