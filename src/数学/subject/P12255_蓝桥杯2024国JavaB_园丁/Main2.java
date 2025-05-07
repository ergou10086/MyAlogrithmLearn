package 数学.subject.P12255_蓝桥杯2024国JavaB_园丁;

import java.util.*;
import java.io.*;

public class Main2 {
    public static void main(String[] args) throws IOException {
        new Solutions2();
    }
}


class Solutions2{
    private int n;
    private int[] a;
    private int[] f;
    private List<Integer>[] tree;
    private int ans = 0;

    private int squareFree(int x) {
        int res = 1;
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                int cnt = 0;
                while (x % i == 0) {
                    x /= i;
                    cnt++;
                }
                if (cnt % 2 != 0) {
                    res *= i;
                }
            }
        }
        if (x > 1) {
            res *= x;
        }
        return res;
    }

    public Solutions2(){
        FastReader sc = new FastReader();
        n = sc.nextInt();
        a = new int[n + 1];
        f = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
            f[i] = squareFree(a[i]);
        }

        tree = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) tree[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            tree[u].add(v);
            tree[v].add(u);
        }

        // 使用迭代的后序遍历来替代递归DFS
        Deque<Object[]> stack = new ArrayDeque<>();
        stack.push(new Object[]{1, 0, false});

        while (!stack.isEmpty()) {
            Object[] node = stack.pop();
            int u = (Integer) node[0];
            int fa = (Integer) node[1];
            boolean visited = (Boolean) node[2];

            if (!visited) {
                stack.push(new Object[]{u, fa, true});
                List<Integer> children = new ArrayList<>();
                for (int v : tree[u]) {
                    if (v != fa) {
                        children.add(v);
                    }
                }
                // 逆序压入，以保持原来的处理顺序
                for (int i = children.size() - 1; i >= 0; i--) {
                    int v = children.get(i);
                    stack.push(new Object[]{v, u, false});
                }
            } else {
                List<Integer> children = new ArrayList<>();
                for (int v : tree[u]) {
                    if (v != fa) {
                        children.add(v);
                    }
                }
                if (children.size() >= 2) {
                    Map<Integer, Integer> cnt = new HashMap<>();
                    for (int v : children) {
                        int sf = f[v];
                        cnt.put(sf, cnt.getOrDefault(sf, 0) + 1);
                    }
                    for (int c : cnt.values()) {
                        if (c > 1) {
                            ans += c - 1;
                        }
                    }
                }
            }
        }
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