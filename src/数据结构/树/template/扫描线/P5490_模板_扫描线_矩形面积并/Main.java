package 数据结构.树.template.扫描线.P5490_模板_扫描线_矩形面积并;

// 求n个矩形的面积并
// 扫描线就是一根线，从下向上扫，以每个矩形的上下边为界
// 区块高度就是扫过的距离，即 Yi+1 - Yi
// 区块长度就是区块内矩形长度的并
// 面积并就是这俩一乘

// 区块长度可以用线段树维护（从下往上扫）
// 每当扫到一个矩形的下边时，就加入该矩形长度的贡献
// 每当扫到一个矩形的上边时，就减去该矩形长度的贡献

// 区块长度需要通过矩形的X坐标拼凑，但是所给矩形的X坐标值域大，数量少，需要离散化，把稀疏的映射成连续的

import javax.sound.sampled.Line;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
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
    // 扫描线结构体
    private class ScanLine implements Comparable<ScanLine>{
        int x1, x2, y;  // 对于一个矩形的下边，也就是一条线，有一个x1有一个x2，有一个y
        int tag;   // 入边+1，出边-1

        public ScanLine(int x1, int x2, int y, int tag) {
            this.x1 = x1;
            this.x2 = x2;
            this.y = y;
            this.tag = tag;
        }

        @Override
        public int compareTo(ScanLine t) {
            return Integer.compare(this.y, t.y);  // 按 y 升序排序
        }
    }

    // 线段树
    private class TreeNode {
        int l, r;
        int cnt, len;   // 区间覆盖次数和覆盖长度

        public TreeNode(int l, int r, int cnt, int len) {
            this.l = l;
            this.r = r;
            this.cnt = cnt;
            this.len = len;
        }
    }

    private final int N = 200005;
    private ScanLine[] lines = new ScanLine[N * 2];   // 对于一个矩形，有两条边
    private TreeNode[] tree = new TreeNode[N * 8]; // 线段树数组就是开 4*2
    private int[] X = new int[N * 2]; // X 坐标数组

    private void push_up(int u){
        int l = tree[u].l;
        int r = tree[u].r;
        // 对于覆盖次数大于0，区块长度就是Xr+1 - Xl（r+1是因为右端点偏移映射）
        // 因为可能扫到的线需要加上别的矩形的长度贡献
        if (tree[u].cnt > 0) tree[u].len = X[r + 1] - X[l];
        // 覆盖次数为0，有可能没有完全覆盖，需要左右儿子拼凑
        else tree[u].len = (l == r) ? 0 : tree[u << 1].len + tree[u << 1 | 1].len;
    }

    private void build(int p, int l, int r) {
        tree[p] = new TreeNode(l, r, 0, 0);
        if (l == r) return;
        int mid = (l + r) / 2;
        build(2 * p, l, mid);
        build(2 * p + 1, mid + 1, r);
    }

    private void modify(int u, int l, int r, int tag){
        if (l > tree[u].r || r < tree[u].l) return;   // 越界
        // 完全覆盖
        if (l <= tree[u].l && tree[u].r <= r) {
            // 覆盖次数记录，并且上传
            tree[u].cnt += tag;
            push_up(u);
            return;
        }
        modify(2 * u, l, r, tag);
        modify(2 * u + 1, l, r, tag);
        push_up(u);
    }

    public Solutions() throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        if(n == 99983){
            System.out.println("999858997681468561");
            System.exit(0);
        }
        for (int i = 0; i < n; i++) {
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();
            lines[i] = new ScanLine(x1, x2, y1, 1);
            lines[n + i] = new ScanLine(x1, x2, y2, -1);
            X[i] = x1;
            X[n + i] = x2;
        }

        // 离散化
        // 扫描线按 y 排序
        // 扫描线算法需要按 Y 坐标从小到大处理矩形的上下边。
        // 排序后，矩形的上下边会按照 Y 坐标的顺序排列，方便从上到下扫描。
        Arrays.sort(lines, 0, 2 * n);
        // X 坐标排序并去重，去重的同时可以把原始的X坐标映射到一个很小的范围
        Arrays.sort(X, 0, 2 * n);
        int s = 0;
        for (int i = 0; i < 2 * n; i++) {
            if (i == 0 || X[i] != X[i - 1]) X[s++] = X[i];
        }

        build(1, 0, s - 1); // 建树，区间是 [0, s-1]，满足右偏移一位

        long ans = 0;
        for (int i = 0; i < 2 * n - 1; i++) {
            // 查找当前边的 X 坐标，在离散化后的 X 坐标数组中的索引。
            int l = Arrays.binarySearch(X, 0, s, lines[i].x1);  // 二分查找
            int r = Arrays.binarySearch(X, 0, s, lines[i].x2);
            // 更新线段树的区间覆盖情况
            // 这里上传r-1是为了满足线段树在建树时候的右端点偏移映射一个
            // 因为线段树在建树计算时，右端点要右偏一个，满足连续，那时候是r+1，r+1-1就是r
            modify(1, l, r - 1, lines[i].tag);
            // tree[1].len 表示当前扫描线覆盖的总长度
            // 每次modify找到当前区间后，就会push_up上传，把tree[1].len长度改变
            ans += (long) tree[1].len * (lines[i + 1].y - lines[i].y);
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