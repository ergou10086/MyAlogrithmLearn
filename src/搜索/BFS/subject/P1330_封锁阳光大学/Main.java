package 搜索.BFS.subject.P1330_封锁阳光大学;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions {
    private final int N = 10010, M = 200010;
    private int n, m, tot = 0, ans = 0;
    private int[] h = new int[N];
    private int[] used = new int[N];
    private int[] sum = new int[3];
    private Edge[] e = new Edge[M];

    private class Edge {
        int v;
        int next;

        public Edge(int v, int next) {
            this.v = v;
            this.next = next;
        }
    }

    private void addEdge(int u, int v) {
        tot++;
        e[tot] = new Edge(v, h[u]);
        h[u] = tot;
    }

    private boolean bfs(int start) {
        Arrays.fill(used, 0);
        Arrays.fill(sum, 0);
        Queue<Integer> q = new LinkedList<>();
        used[start] = 1;
        sum[1] = 1;
        q.add(start);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int k = h[u]; k!= 0; k = e[k].next) {
                int v = e[k].v;
                if (used[v] == used[u]) {
                    return true;
                }
                if (used[v] == 0) {
                    used[v] = used[u] % 2 + 1;
                    sum[used[v]]++;
                    q.add(v);
                }
            }
        }
        return false;
    }

    public Solutions() {
        FastReader sc = new FastReader(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        while (m-- > 0) {
            int uu = sc.nextInt();
            int vv = sc.nextInt();
            addEdge(uu, vv);
            addEdge(vv, uu);
        }
        for (int i = 1; i <= n; i++) {
            if (used[i]!= 0) {
                continue;
            }
            if (bfs(i)) {
                System.out.println("Impossible");
                return;
            } else {
                ans += Math.min(sum[1], sum[2]);
            }
        }
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
            while (tokenizer == null ||!tokenizer.hasMoreTokens()) {
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


    }
}