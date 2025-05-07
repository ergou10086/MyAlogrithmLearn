package 数据结构.树.subject.线段树.P2023_AHOI2009_维护序列;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions {
    private static final int INT = 100005;
    private int n, p, m;
    private long[] w = new long[INT];

    private class TreeNode {
        int l, r;
        long sum, mul, add;

        public TreeNode(int l, int r, long sum, long mul, long add) {
            this.l = l;
            this.r = r;
            this.sum = sum;
            this.mul = mul;
            this.add = add;
        }
    }

    private TreeNode[] tree;

    private void push_up(int u) {
        tree[u].sum = (tree[2 * u].sum + tree[2 * u + 1].sum) % p;
    }

    private void calc(TreeNode node, long add, long mul) {
        node.sum = (node.sum * mul + (node.r - node.l + 1) * add) % p;
        node.mul = node.mul * mul % p;
        node.add = (node.add * mul + add) % p;
    }

    private void push_down(int u) {
        if (tree[u].mul != 1 || tree[u].add != 0) {
            calc(tree[2 * u], tree[u].add, tree[u].mul);
            calc(tree[2 * u + 1], tree[u].add, tree[u].mul);
            tree[u].add = 0;
            tree[u].mul = 1;
        }
    }

    private void build(int u, int l, int r) {
        tree[u] = new TreeNode(l, r, w[l], 1, 0);
        if (l == r) {
            tree[u].sum = w[l];
            return;
        }
        int mid = (l + r) / 2;
        build(2 * u, l, mid);
        build(2 * u + 1, mid + 1, r);
        push_up(u);
    }

    private void change(int u, int l, int r, long add, long mul) {
        if (l > tree[u].r || r < tree[u].l) return;
        if (l <= tree[u].l && tree[u].r <= r) {
            calc(tree[u], add, mul);
            return;
        }
        push_down(u);
        change(2 * u, l, r, add, mul);
        change(2 * u + 1, l, r, add, mul);
        push_up(u);
    }

    private long query(int u, int l, int r) {
        if (l > tree[u].r || r < tree[u].l) return 0;
        if (l <= tree[u].l && tree[u].r <= r) return tree[u].sum;
        push_down(u);
        return (query(2 * u, l, r) + query(2 * u + 1, l, r)) % p;
    }

    public Solutions() {
        FastReader sc = new FastReader();
        n = sc.nextInt();
        p = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            w[i] = sc.nextLong();
        }
        m = sc.nextInt();
        tree = new TreeNode[4 * n + 1];

        build(1, 1, n);

        while (m-- > 0) {
            int op = sc.nextInt();
            if (op == 1) {
                int t = sc.nextInt();
                int g = sc.nextInt();
                int c = sc.nextInt();
                change(1, t, g, 0, c);
            } else if (op == 2) {
                int t = sc.nextInt();
                int g = sc.nextInt();
                int c = sc.nextInt();
                change(1, t, g, c, 1);
            } else if (op == 3) {
                int t = sc.nextInt();
                int g = sc.nextInt();
                System.out.println(query(1, t, g));
            }
        }
    }

    static class FastReader {
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}