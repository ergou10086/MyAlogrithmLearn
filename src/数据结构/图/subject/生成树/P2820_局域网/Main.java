package 数据结构.图.subject.生成树.P2820_局域网;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// 删去的边最大长度最大，也就是剩下的长度最小，也就是最小生成树
// 只需要在输入时算出边长总和，再减去最小生成树的长度就行了

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions{
    private int n;
    private int k;
    private int ans = 0;
    private static class Edge {
        private int u, v, w;
        private Edge(int u, int v, int w){
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
    private Edge[] edges = new Edge[20010];
    private int[] fa = new int[200];
    private PrintWriter pw = new PrintWriter(System.out);
    private FastReader sc = new FastReader(System.in);

    private int find(int x){
        if(x == fa[x]) return x;
        else return fa[x] = find(fa[x]);
    }

    private void Kuskal(){
        int a, b, cnt = 0;
        // 边权排序
        Arrays.sort(edges, 1, k + 1, Comparator.comparingInt(o -> o.w));
        // 按顺序连接每一条边,并求这两个点是否在同一集合
        for(int i = 1; i <= k; i++){
            a = find(edges[i].u);
            b = find(edges[i].v);
            if(a != b){
                fa[a] = b;
                ans += edges[i].w;
                cnt++;
                if(cnt == n) return;
            }
        }
    }

    public Solutions() {
        n = sc.nextInt();
        k = sc.nextInt();
        int sum = 0;

        for (int i = 1; i <= n; i++)  fa[i]=i;

        for(int i = 1; i <= k; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            edges[i] = new Edge(u, v, w);
            sum += w;
        }

        Kuskal();

        pw.println(sum - ans);
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