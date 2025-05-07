package 动态规划.template.背包.树上背包.P2014_CTSC1997_选课;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

// f[u][j]：以 u 点为根的子树，选了 j 门课程的最大学分

class Solutions{
    private static final int N = 305;
    private List<List<Integer>> tree = new ArrayList<>();
    private int n, m;   // 节点数量，课程数量
    private int[] w = new int[N];
    private int[][] f = new int[N][N];
    // 以每个节点为根的子树的节点数量
    private int[] siz = new int[N];

    {
        for (int i = 0; i < N; i++) {
            tree.add(new ArrayList<>());
        }
    }

    private void dfs(int u){
        // 只选当前课程的权重
        f[u][1] = w[u];
        siz[u] = 1;

        for(int v: tree.get(u)){
            dfs(v);
            siz[u] += siz[v];

            //  // 从最大可选的课程数量开始递减枚举
            for(int j = Math.min(m + 1, siz[u]); j > 0; j--){
                for(int k = 0; k <= Math.min(j - 1, siz[v]); k++){
                    f[u][j] = Math.max(f[u][j], f[u][j - k] + f[v][k]);
                }
            }
        }
    }

    public Solutions() {
        FastReader sc = new FastReader();
        // 节点数量和课程数量
        n = sc.nextInt();
        m = sc.nextInt();

        for(int i = 1; i <= n; i++){
            int k = sc.nextInt();
            w[i] = sc.nextInt();
            tree.get(k).add(i);
        }

        dfs(0);

        System.out.println(f[0][m + 1]);
    }

    private static class FastReader {
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
                    throw new RuntimeException(e);
                }

            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
