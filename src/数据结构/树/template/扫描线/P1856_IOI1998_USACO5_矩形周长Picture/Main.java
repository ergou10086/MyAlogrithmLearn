package 数据结构.树.template.扫描线.P1856_IOI1998_USACO5_矩形周长Picture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// 扫描线。上下扫一次，左右扫一次
// 开两棵树，一个存扫x的，一个存扫y的

public class Main {
    public static void main(String[] args) {
        try {
            new Solutions();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Solutions{
    private static final int N = 5010;

    private class ScanLine{
        // 当存x轴平行边时候，le为x1，re为x2，byd为y
        // 当存y轴平行边时候，le为y1，re为y2，byd为x
        int le, re, byd;
        int tag;    // 标记，入边+1，出边-1

        public ScanLine(){}
        public ScanLine(int le, int re, int byd, int tag){
            this.le = le;
            this.re = re;
            this.byd = byd;
            this.tag = tag;
        }
    }

    private class TreeNode{
        int l, r;
        int cnt, len; // 区间覆盖次数和覆盖长度

        public TreeNode(){}
        public TreeNode(int l, int r, int cnt, int len) {
            this.l = l;
            this.r = r;
            this.cnt = cnt;
            this.len = len;
        }
    }

    private ScanLine[] xlines = new ScanLine[N * 2];   // 扫描X的
    private ScanLine[] ylines = new ScanLine[N * 2];   // 扫描Y的
    private TreeNode[] treeX = new TreeNode[N * 8]; // 线段树数组就是开 4*2
    private TreeNode[] treeY = new TreeNode[N * 8]; // 存扫y的
    private int[] X = new int[N * 2];   // X 坐标数组
    private int[] Y = new int[N * 2];   // Y 坐标数组

    // 其实我应该写两个push和build
    private void push_up(int u, TreeNode[] tree, int[] point){
        int l = tree[u].l;
        int r = tree[u].r;
        // 覆盖次数大于0，直接算，也算是重新算吧
        if(tree[u].cnt > 0){
            tree[u].len = point[r + 1] - point[l];
        }else{
            // 覆盖次数为0，需要左右儿子拼凑
            tree[u].len = (l == r) ? 0 : tree[u << 1].len + tree[u << 1 | 1].len;
        }
    }

    private void build(int u, int l, int r, TreeNode[] tree){
        tree[u] = new TreeNode(l, r, 0, 0);
        if(l == r) return;
        int mid = (l + r) >> 1;
        build(2 * u, l, mid, tree);
        build(2 * u + 1, mid + 1, r, tree);
    }

    private void modify(int u, int l, int r, int tag, TreeNode[] tree, int[] point){
        if (l > tree[u].r || r < tree[u].l) return;   // 越界
        // 完全覆盖，加覆盖次数，上传
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

    public Solutions() throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            // 左下角
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            // 右上角
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();

            // 出入边情况别忘
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

        // 排序，准备离散化
        Arrays.sort(xlines, 0, n, (a, b) -> a.byd == b.byd ? Integer.compare(b.tag, a.tag) : Integer.compare(a.byd, b.byd));
        Arrays.sort(ylines, 0, n, (a, b) -> a.byd == b.byd ? Integer.compare(b.tag, a.tag)  : Integer.compare(a.byd, b.byd));
        Arrays.sort(X, 0, n);
        Arrays.sort(Y, 0, n);

        // 离散化x
        int sXcVp = 1;
        for (int i = 0; i < n; i++) {
            if (i == 0 || X[i] != X[i - 1]) X[sXcVp++] = X[i];
        }

        // 离散化y
        int nXcVp = 1;
        for(int i = 0; i < n; i++){
            if(i == 0 || Y[i] != Y[i - 1]) Y[nXcVp++] = Y[i];
        }

        build(1, 0, sXcVp - 1, treeX);
        build(1, 0, nXcVp - 1, treeY);

        long tempX = 0, tempY = 0;
        long ans = 0;
        for (int i = 0; i < n - 1; i++) {
            // 二分查找x的左端点和右端点
            int lx = Arrays.binarySearch(X, 0, sXcVp, xlines[i].le);
            if (lx < 0) lx = -lx - 1;
            int rx = Arrays.binarySearch(X, 0, sXcVp, xlines[i].re);
            if (rx < 0) rx = -rx - 1;
            // 再找y的
            int ly = Arrays.binarySearch(Y,0, nXcVp, ylines[i].le);
            if (ly < 0) ly = -ly - 1;
            int ry = Arrays.binarySearch(Y,0, nXcVp, ylines[i].re);
            if (ry < 0) ry = -ry - 1;

            modify(1, lx, rx - 1, xlines[i].tag, treeX, X);
            modify(1, ly, ry - 1, ylines[i].tag, treeY, Y);

            // 需要有temp记录上次扫描的长度，避免多加
            ans += Math.abs(treeX[1].len - tempX);
            ans += Math.abs(treeY[1].len - tempY);

            tempX = treeX[1].len;
            tempY = treeY[1].len;
        }
    }

    class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        String next() throws IOException{
            while(!st.hasMoreElements()){
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        int nextInt() throws IOException{
            return Integer.parseInt(next());
        }
    }
}
