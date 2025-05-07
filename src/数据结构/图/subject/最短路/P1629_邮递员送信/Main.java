package 数据结构.图.subject.最短路.P1629_邮递员送信;

// n<=1000且路径距离皆为正整数
// 有向图,送完货物之后还要返回

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


class Solutions{
    private static final int N = 205, M = 100005, INF = 99999999;
    private int n, m;
    private int[] u = new int[M], v = new int[M], w = new int[M];
    private int[] dis = new int [M];

    private void bellmanFord(){
        // 初始化
        Arrays.fill(dis, INF);
        dis[1] = 0;

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(dis[v[j]] > dis[u[j]] + w[j]){
                    dis[v[j]] = dis[u[j]] + w[j];
                }
            }
        }
    }

    public Solutions(){
        FastReader fs = new FastReader(System.in);
        int ans = 0;
        n = fs.nextInt();
        m = fs.nextInt();
        for(int i = 1; i <= m; i++){
            u[i] = fs.nextInt();
            v[i] = fs.nextInt();
            w[i] = fs.nextInt();

        }
        bellmanFord();
        for(int i = 1; i <= n; i++) ans += dis[i];  // 从邮局到其他地方

        // 反向建图
        for(int i = 1; i <= m; i++){
            int temp = u[i];
            u[i] = v[i];
            v[i] = temp;
        }
        bellmanFord();
        for(int i = 1; i <= n; i++) ans += dis[i];

        System.out.println(ans);
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