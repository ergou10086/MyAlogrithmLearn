package 数据结构.图.subject.生成树.P4826_USACO15FEB_Superbull_S;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions {
    private int n, cnt = 0, ans = 0, sum = 0;
    private int[] fa = new int[2001];
    private int[] a = new int[2001];
    private class Edge {
        int u, v, w;

        private Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
    private Edge[] edges = new Edge[4000008];

    // 并查集查找方法
    private int find(int x) {
        return x == fa[x]? x : (fa[x] = find(fa[x]));
    }

    // 并查集合并方法
    private void union(int u, int v) {
        int fu = find(u);
        int fv = find(v);
        if (fu!= fv) {
            fa[fu] = fv;
        }
    }

    // 修正后的克鲁斯卡尔算法方法
    private void kruskal() {
        Arrays.sort(edges, 0, cnt, Comparator.comparingInt(o -> o.w));
        for (int i = 0; i < cnt; i++) {
            // 判断两个端点是否属于不同集合
            if (find(edges[i].u)!= find(edges[i].v)) {
                ans += edges[i].w;
                sum++;
                union(edges[i].u, edges[i].v);
            }
            if (sum == n - 1) {
                break;
            }
        }
    }

    public Solutions() {
        FastReader sc = new FastReader(System.in);
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
            fa[i] = i;
        }

        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                edges[cnt++] = new Edge(i, j, a[i] ^ a[j]);
            }
        }

        kruskal();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        try {
            bw.write(String.valueOf(ans));
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
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
            while (tokenizer == null ||!tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}