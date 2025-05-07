package 动态规划.template.DP类.树形dp.P1122_最大子树和;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions2();
    }
}

class Solutions2 {
    private static final int N = 16010;
    private List<List<Integer>> tree = new ArrayList<>();
    private int[] w = new int[N];
    // 以i为根的子树中点权和最大的一棵子树
    private int[] f = new int[N];

    private void dfs(int u, int fa) {
        f[u] = w[u];   // 遍历设初值
        // f[u] 对于其中的儿子v
        // 如果f[v] > 0, f[u] = a[u] + f[v] ,小于0就不加

        for(int i = 0; i < tree.get(u).size(); i++){
            int sp = tree.get(u).get(i);
            if(sp != fa){
                dfs(sp, u);
                if(f[sp] > 0)  f[u] += f[sp];
            }
        }
    }

    public Solutions2() {
        FastReader sc = new FastReader();
        int n = sc.nextInt();

        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 1; i <= n; i++) w[i] = sc.nextInt();

        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        dfs(1, 0);

        int ans = -1564123654;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, f[i]);  // 找出最大点权和
        }

        System.out.println(ans);
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