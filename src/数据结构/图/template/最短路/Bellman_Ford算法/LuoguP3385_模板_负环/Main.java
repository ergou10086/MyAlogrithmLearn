package 数据结构.图.template.最短路.Bellman_Ford算法.LuoguP3385_模板_负环;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
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

    private void add(int a, int b, int c) {
        to[++tot] = b;
        ne[tot] = h[a];
        w[tot] = c;
        h[a] = tot;
    }

    private boolean BellmanFord() {
        Arrays.fill(dist, inf);
        dist[1] = 0;
        boolean flag = true;

        for (int i = 1; i <= n; i++) {
            flag = false;
            // 遍历所有节点，尝试对每个节点出发的边进行松弛操作
            for (int u = 1; u <= n; u++) {
                if(dist[u] == inf) continue;   // 加速，避免没必要的遍历
                for(int j = h[u]; j != 0; j = ne[j]){
                    int v = to[j];
                    if(dist[v] > dist[u] + w[j]){
                        dist[v] = dist[u] + w[j];
                        flag = true;
                    }
                }
            }
            if(!flag){
                break;
            }
        }
        return flag;  //第n轮=true,有负环
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
            System.out.println(BellmanFord()? "YES" : "NO");
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