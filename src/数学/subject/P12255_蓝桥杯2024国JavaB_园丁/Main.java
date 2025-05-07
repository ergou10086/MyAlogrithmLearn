package 数学.subject.P12255_蓝桥杯2024国JavaB_园丁;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

// 对于每个有两个及以上儿子的结点 i，其所有儿子的权值两两乘积都不能是完全平方数。
// 我们需要最少修改多少个结点的权值，使得全树满足此条件。

class Solutions {
    private int n ,ans = 0;     // 树中节点的数量
    private int[] a = new int[100086];   // 节点值
    private int[] f = new int[100086];   // 节点值的平方自由部分
    private List<Integer>[] tree;   // 邻接表表示树

    // 返回 x 的平方自由部分
    // 平方自由部分是指一个数分解质因数后，每个质因数的指数都为 1 的部分
    private int squareFree(int x) {
        int res = 1;
        for (int i = 2; i * i <= x; i++) { // 枚举所有可能的质因数
            int cnt = 0;
            while (x % i == 0) { // 统计i作为质因子的次数
                x /= i;
                cnt++;
            }
            if ((cnt & 1) == 1) res *= i; // 只保留奇数次的质因数
        }
        if (x > 1) res *= x; // x本身是大于1的质数
        return res;
    }

    private void dfs(int u, int fa) {
        List<Integer> children = new ArrayList<>();
        // 遍历当前节点 u 的所有邻接节点 v
        for (int v : tree[u]) {
            if (v != fa) { // 避免回到父节点
                children.add(v);
                dfs(v, u); // 递归遍历子节点
            }
        }
        if (children.size() >= 2) {
            Map<Integer, Integer> cnt = new HashMap<>();
            for (int v : children) {
                cnt.put(f[v], cnt.getOrDefault(f[v], 0) + 1);
            }
            for (int c : cnt.values()) {
                if (c > 1) ans += c - 1; // 有冲突的平方自由部分，累积修改 c-1
            }
        }
    }

    public Solutions(){
        FastReader sc = new FastReader();
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
            f[i] = squareFree(a[i]); // 计算每个节点值的平方自由部分
        }
        // 初始化邻接表
        tree = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) tree[i] = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            tree[u].add(v);
            tree[v].add(u);
        }
        // 从根节点（节点 1）开始进行深度优先搜索
        dfs(1, 0);
        // 输出最终结果
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
