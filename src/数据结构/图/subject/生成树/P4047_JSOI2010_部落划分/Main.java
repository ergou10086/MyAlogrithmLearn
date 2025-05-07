package 数据结构.图.subject.生成树.P4047_JSOI2010_部落划分;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

class Solutions{
    private int N = 10001;
    private int[] fa = new int[N];

    // 存边结构体
    private class Edge{
        int u, v;
        double dis;
        private Edge(int u, int v, double dis) {
            this.u = u;
            this.v = v;
            this.dis = dis;
        }
    }
    private Edge[] edge = new Edge[10000001];
    private int cnt = 0;  // 记录边的数量
    private PrintWriter pw = new PrintWriter(System.out);

    private int find(int x){
        if(fa[x] == x) return x;
        return fa[x] = find(fa[x]);
    }

    public Solutions() {
        FastReader sc = new FastReader(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] x = new int[N];
        int[] y = new int[N];
        for (int i = 1; i <= n; i++) {
            fa[i] = i;
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
        }
        // 生成所有节点两两之间的边
        double[] a = new double[N];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j < i; j++){
                cnt++;
                // 计算sqrt((x1-x2)^2+(y1-y2)^2)，即距离
                edge[cnt] = new Edge(i, j, Math.sqrt(Math.pow(Math.abs(x[i]- x[j]), 2) + Math.pow(Math.abs(y[i]- y[j]), 2)));
            }
        }
        // Kruskal边权排序，权值从小到大依次考察每条边
        Arrays.sort(edge, 1, cnt+1, Comparator.comparingDouble(o -> o.dis));
        int i = 1;   // i逐个考察边
        int j = 0;   // j选边数
        // n 个节点的连通图最小生成树有 n - 1边
        double ans = 0;
        while(j < n - 1){
            int fu = find(edge[i].u);
            int fv = find(edge[i].v);
            double dis = edge[i].dis;
            // 不形成环，位于不同集合
            if(fu != fv){
                fa[fu] = fv;  // 合并集合
                j++;  // 选取的边数量 j 加 1
                a[j] = dis;  // 距离更新
                if(j == n - k + 1){
                    ans = dis;
                    break;
                }
            }
            i++;
        }
        pw.printf("%.2f", ans);
        pw.close();
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

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}
