package 数据结构.树.subject.扫描线.P1502_窗口的星星;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        try {
            new Solutions();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Solutions {
    private static final int N = 10010; // 减少数组大小
    private long[] Y;
    private Line[] lines;
    private TreeNode[] tree;

    private class Line {
        long l, r, h;
        long val;

        public Line(long l, long r, long h, long val) {
            this.l = l;
            this.r = r;
            this.h = h;
            this.val = val;
        }
    }

    private class TreeNode {
        long l, r;
        long maxx, add;

        public TreeNode(long l, long r, long maxx, long add) {
            this.l = l;
            this.r = r;
            this.maxx = maxx;
            this.add = add;
        }
    }

    private void pushUp(int x) {
        tree[x].maxx = Math.max(tree[2 * x].maxx, tree[2 * x + 1].maxx);
    }

    private void pushDown(int x) {
        if (tree[x].add != 0) {
            tree[2 * x].maxx += tree[x].add;
            tree[2 * x + 1].maxx += tree[x].add;
            tree[2 * x].add += tree[x].add;
            tree[2 * x + 1].add += tree[x].add;
            tree[x].add = 0;
        }
    }

    private void build(int p, long l, long r) {
        tree[p] = new TreeNode(l, r, 0, 0);
        if (l == r) return;
        long mid = (l + r) / 2;
        build(2 * p, l, mid);
        build(2 * p + 1, mid + 1, r);
    }

    private void modify(int u, long l, long r, long d) {
        if (l > tree[u].r || r < tree[u].l) return;
        if (l <= tree[u].l && tree[u].r <= r) {
            tree[u].maxx += d;
            tree[u].add += d;
            return;
        }
        pushDown(u);
        long mid = (tree[u].l + tree[u].r) / 2;
        if (l <= mid) modify(2 * u, l, r, d);
        if (r > mid) modify(2 * u + 1, l, r, d);
        pushUp(u);
    }

    public Solutions() throws IOException {
        FastReader sc = new FastReader();
        int T = sc.nextInt();

        while (T-- > 0) {
            int n = sc.nextInt();
            int w = sc.nextInt();
            int h = sc.nextInt();

            Y = new long[2 * n + 2];
            lines = new Line[2 * n + 2];
            tree = new TreeNode[8 * n + 8]; // 线段树大小调整为 8 * n

            for (int i = 1; i <= n; i++) {
                long x = sc.nextLong();
                long y = sc.nextLong();
                long li = sc.nextLong();
                Y[i] = y;
                Y[n + i] = y + h - 1;
                lines[i] = new Line(y, y + h - 1, x, li);
                lines[n + i] = new Line(y, y + h - 1, x + w - 1, -li);
            }
            n *= 2;

            Arrays.sort(Y, 1, n + 1);
            // 当 h 相同时，按 val 从小到大排序
            // 以确保在相同高度下，先处理负值（结束线段），再处理正值（开始线段）
            Arrays.sort(lines, 1, n + 1, (a, b) -> {
                if (a.h != b.h) return Long.compare(a.h, b.h);
                return Long.compare(b.val, a.val);
            });

            int s = 1;
            for (int i = 1; i <= n; i++) {
                if (Y[i] != Y[i - 1]) Y[s++] = Y[i];
            }

            for (int i = 1; i <= n; i++) {
                int l = Arrays.binarySearch(Y, 1, s, lines[i].l);
                int r = Arrays.binarySearch(Y, 1, s, lines[i].r);
                lines[i].l = l;
                lines[i].r = r;
            }

            build(1, 1, s);

            long ans = 0;
            for (int i = 1; i <= n; i++) {
                modify(1, (int) lines[i].l, (int) lines[i].r, lines[i].val);
                ans = Math.max(ans, tree[1].maxx);
            }
            System.out.println(ans);
        }
    }

    class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        String next() throws IOException {
            while (!st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }
    }
}