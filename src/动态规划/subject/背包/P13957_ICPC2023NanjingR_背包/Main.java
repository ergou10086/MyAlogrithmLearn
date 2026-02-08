package 动态规划.subject.背包.P13957_ICPC2023NanjingR_背包;

import java.util.*;
import java.io.*;

public class Main {

    static class Node implements Comparable<Node> {
        int w, v;  // w是价格（体积），v是价值（美丽度）

        public Node(int w, int v) {
            this.w = w;
            this.v = v;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);  // 按价格从小到大排序
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Node[] nodes = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());   // 售价
            int v = Integer.parseInt(st.nextToken());   // 美丽度
            nodes[i] = new Node(w, v);
        }

        // 美丽度从小到大排序
        Arrays.sort(nodes, 1, n + 1);

        // 然后处理sum数组
        // sum[i]表示i到n中选k个最大价值的和
        long[] sum = new long[n + 2];
        List<Integer> p = new ArrayList<>();

        // 倒序选择美丽度大的
        for (int i = n; i >= 1; i--) {
            p.add(nodes[i].v);
            // 降序排序
            p.sort(Collections.reverseOrder());

            int size = p.size();
            long sum_c = 0;
            int take = Math.min(k, size);
            for (int j = 0; j < take; j++) {
                sum_c += p.get(j);
            }
            sum[i] = sum_c;
        }

        // 0-1 背包dp
        long[][] dp = new long[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            // 不选第i个物品
            System.arraycopy(dp[i - 1], 0, dp[i], 0, W + 1);

            // 选第i个物品
            int wi = nodes[i].w;
            int vi = nodes[i].v;
            // 倒序01背包，体积倒序
            for (int j = W; j >= wi; j--) {
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - wi] + vi);
            }
        }

        // 找最优解
        long res = 0;
        for (int i = 0; i <= n; i++) {
            res = Math.max(res, dp[i][W] + sum[i + 1]);
        }

        System.out.println(res);
    }
}
