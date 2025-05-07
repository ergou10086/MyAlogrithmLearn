package 数据结构.树.template.扫描线.P1856_IOI1998_USACO5_矩形周长Picture;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main2 {
    public static void main(String[] args) {
        try {
            new Solutions2();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Solutions2 {
    private static final int N = 5010;

    private class ScanLine {
        int le, re, byd;
        int tag;

        public ScanLine() {}
        public ScanLine(int le, int re, int byd, int tag) {
            this.le = le;
            this.re = re;
            this.byd = byd;
            this.tag = tag;
        }
    }

    private class TreeNode {
        int l, r;
        int cnt, len;

        public TreeNode() {}
        public TreeNode(int l, int r, int cnt, int len) {
            this.l = l;
            this.r = r;
            this.cnt = cnt;
            this.len = len;
        }
    }

    private ScanLine[] xlines = new ScanLine[N * 2];
    private ScanLine[] ylines = new ScanLine[N * 2];
    private TreeNode[] treeX = new TreeNode[N * 8];
    private TreeNode[] treeY = new TreeNode[N * 8];
    private int[] X = new int[N * 2];
    private int[] Y = new int[N * 2];

    private void push_up(int u, TreeNode[] tree, int[] point) {
        int l = tree[u].l;
        int r = tree[u].r;
        if (tree[u].cnt > 0) {
            tree[u].len = point[r + 1] - point[l];
        } else {
            tree[u].len = (l == r) ? 0 : tree[u << 1].len + tree[u << 1 | 1].len;
        }
    }

    private void build(int u, int l, int r, TreeNode[] tree) {
        tree[u] = new TreeNode(l, r, 0, 0);
        if (l == r) return;
        int mid = (l + r) >> 1;
        build(u << 1, l, mid, tree);
        build(u << 1 | 1, mid + 1, r, tree);
    }

    private void modify(int u, int l, int r, int tag, TreeNode[] tree, int[] point) {
        if (l > tree[u].r || r < tree[u].l) return;   // 越界
        if (l <= tree[u].l && r >= tree[u].r) {
            tree[u].cnt += tag;
            push_up(u, tree, point);
            return;
        }

        int mid = (tree[u].l + tree[u].r) >> 1;
        if (l <= mid) modify(u << 1, l, r, tag, tree, point);  // 递归左子树
        if (r > mid) modify(u << 1 | 1, l, r, tag, tree, point);  // 递归右子树
        push_up(u, tree, point);
    }

    public Solutions2() throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();

            xlines[i] = new ScanLine(x1, x2, y1, 1);
            xlines[n + i] = new ScanLine(x1, x2, y2, -1);
            X[i] = x1;
            X[n + i] = x2;

            ylines[i] = new ScanLine(y1, y2, x1, 1);
            ylines[n + i] = new ScanLine(y1, y2, x2, -1);
            Y[i] = y1;
            Y[n + i] = y2;
        }
        n <<= 1;

        Arrays.sort(xlines, 0, n, (a, b) -> a.byd == b.byd ? Integer.compare(b.tag, a.tag) : Integer.compare(a.byd, b.byd));
        Arrays.sort(ylines, 0, n, (a, b) -> a.byd == b.byd ? Integer.compare(b.tag, a.tag) : Integer.compare(a.byd, b.byd));
        Arrays.sort(X, 0, n);
        Arrays.sort(Y, 0, n);

        int sXcVp = 1;
        for (int i = 1; i < n; i++) {
            if (X[i] != X[sXcVp - 1]) X[sXcVp++] = X[i];
        }

        int nXcVp = 1;
        for (int i = 1; i < n; i++) {
            if (Y[i] != Y[nXcVp - 1]) Y[nXcVp++] = Y[i];
        }

        build(1, 0, sXcVp - 1, treeX);
        build(1, 0, nXcVp - 1, treeY);

        long tempX = 0, tempY = 0;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int lx = Arrays.binarySearch(X, 0, sXcVp, xlines[i].le);
            if (lx < 0) lx = -lx - 1;
            int rx = Arrays.binarySearch(X, 0, sXcVp, xlines[i].re);
            if (rx < 0) rx = -rx - 1;

            int ly = Arrays.binarySearch(Y, 0, nXcVp, ylines[i].le);
            if (ly < 0) ly = -ly - 1;
            int ry = Arrays.binarySearch(Y, 0, nXcVp, ylines[i].re);
            if (ry < 0) ry = -ry - 1;

            modify(1, lx, rx - 1, xlines[i].tag, treeX, X);
            modify(1, ly, ry - 1, ylines[i].tag, treeY, Y);

            ans += Math.abs(treeX[1].len - tempX);
            ans += Math.abs(treeY[1].len - tempY);

            tempX = treeX[1].len;
            tempY = treeY[1].len;
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

        String next() throws IOException {
            while (!st.hasMoreElements()) {
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}