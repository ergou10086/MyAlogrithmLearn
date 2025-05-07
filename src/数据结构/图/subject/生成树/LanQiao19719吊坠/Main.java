package 数据结构.图.subject.生成树.LanQiao19719吊坠;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions3();
    }
}

class Solutions3 {
    private int n, m, ans = 0;
    private String[] strs;

    // 求最长公共子串长度
    // 令dp[i][j]表示以s1[i]和s2[j]结尾的最长公共子串长度
    // s1[i]!=s2[j], 则dp[i][j]=0
    // s1[i]==s2[j], 则dp[i][j]=1+dp[i-1][j-1]
    // 环形，破环成链
    private int lcd(String s1, String s2) {
        s1 += s1;
        s2 += s2;
        int[][] dp = new int[2 * m + 1][2 * m + 1];
        int maxLen = 0;
        for (int i = 1; i <= 2 * m; i++) {
            char c1 = s1.charAt(i - 1);
            for (int j = 1; j <= 2 * m; j++) {
                char c2 = s2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLen) {
                        maxLen = dp[i][j];
                    }
                }
            }
        }

        return Math.min(maxLen, m);
    }

    // kruskal用的并查集
    class UnionSet {
        int[] root;

        UnionSet(int n) {
            root = new int[n];
            for (int i = 0; i < n; i++) {
                root[i] = i;
            }
        }

        int find(int x) {
            if (root[x] == x) return x;
            return root[x] = find(root[x]);
        }

        boolean union(int x, int y) {
            int rx = find(x);
            int ry = find(y);
            if (rx == ry) {
                return false;
            }
            root[rx] = ry;
            return true;
        }
    }

    // 边类
    class Edge {
        int u, v, w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    // 吊坠i和j之间的lcd为其边权，那么就是找最大生成树
    private void kruskal(Edge[] edges, int edgeCount) {
        // 最大生成树，从大到小排序
        Arrays.sort(edges, 0, edgeCount, (e1, e2) -> Integer.compare(e2.w, e1.w));
        UnionSet set = new UnionSet(n);

        int count = 0;

        for (int i = 0; i < edgeCount; i++) {
            Edge edge = edges[i];
            if (count >= n - 1) break;

            if (set.union(edge.u, edge.v)) {
                ans += edge.w;
                count++;
            }
        }
    }

    public Solutions3() {
        FastReader sc = new FastReader();
        n = sc.nextInt();
        m = sc.nextInt();
        strs = new String[n];
        for (int i = 0; i < n; i++) strs[i] = sc.next();

        int edgeCount = 0;
        Edge[] edges = new Edge[n * (n - 1) / 2];

        // 求边权
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                edges[edgeCount++] = new Edge(i, j, lcd(strs[i], strs[j]));
            }
        }

        kruskal(edges, edgeCount);

        System.out.println(ans);
    }

    class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        String next() {
            while (!st.hasMoreElements()) {
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
}    