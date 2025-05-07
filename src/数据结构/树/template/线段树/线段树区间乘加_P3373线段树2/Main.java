package 数据结构.树.template.线段树.线段树区间乘加_P3373线段树2;

// 如何维护乘和加两个懒标记
// 下传信息，先加后乘（加法优先）
// {[(x+add) * mul] + a} * m = (x + add + a / mul) * mul * m
// 丢失精度
// 所以下传信息，需要乘法优先

// (xl * m + a) +... + (xr * m + a) = sum * m + (r - l + 1) * a


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
    private long n, q, p;
    private long[] w = new long[INT];

    private class TreeNode {
        long l, r, sum, mul, add;   // 两个懒标记

        private TreeNode(long l, long r, long sum, long mul, long add) {
            this.l = l;
            this.r = r;
            this.sum = sum;
            this.mul = mul;
            this.add = add;
        }
    }

    private TreeNode[] tree = new TreeNode[INT * 4];

    // 上传
    private void push_up(int u) {
        // 左右节点拼凑父节点的sum
        tree[u].sum = (tree[u * 2].sum + tree[u * 2 + 1].sum) % p;
    }

    // 计算节点值
    private void calc(TreeNode tree, long m, long a) {
        tree.sum = (tree.sum * m + (tree.r - tree.l + 1) * a) % p;
        tree.mul = tree.mul * m % p;
        tree.add = (tree.add * m + a) % p;
    }

    // 下传
    private void push_down(int u) {
        // 处理左右子树
        calc(tree[u * 2], tree[u].mul, tree[u].add);
        calc(tree[u * 2 + 1], tree[u].mul, tree[u].add);
        // 清空懒标记
        tree[u].add = 0;
        tree[u].mul = 1;
    }

    // 建树
    private void build(int u, long l, long r) {
        tree[u] = new TreeNode(l, r, w[(int) r], 1, 0);
        if (l == r) return;
        long mid = (l + r) / 2;
        build(u * 2, l, mid);
        build(u * 2 + 1, mid + 1, r);
        push_up(u);
    }

    /**
     * 区间修改
     *
     * @param u 进入的根节点
     * @param l 左端点
     * @param r 右端点
     * @param m 如果是乘上一个数
     * @param a 如果是加上一个数
     */
    private void change(int u, long l, long r, long m, long a) {
        if(l > tree[u].r || r < tree[u].l) return;
        if(l <= tree[u].l && tree[u].r <= r){
            calc(tree[u], m, a);
            return;
        }
        push_down(u);
        change(u * 2, l, r, m, a);
        change(u * 2 + 1, l, r, m, a);
        push_up(u);
    }


    /**
     * 区间查询
     *
     * @param u 进入的根节点
     * @param l 左端点
     * @param r 右端点
     * @return
     */
    private long query(int u, long l, long r) {
        if(l > tree[u].r || r < tree[u].l) return 0;
        if(l <= tree[u].l && tree[u].r <= r) return tree[u].sum;
        push_down(u);
        return (query(2 * u, l , r) + query(2 * u + 1, l , r)) % p;
    }

    public Solutions() {
        FastReader sc = new FastReader();
        n = sc.nextLong();
        q = sc.nextLong();
        p = sc.nextLong();

        for (int i = 1; i <= n; i++) {
            w[i] = sc.nextLong();
        }

        build(1, 1, n);

        while (q-- > 0) {
            {
                long op = sc.nextLong();
                long x = sc.nextLong();
                long y = sc.nextLong();

                if (op == 1) {
                    long k = sc.nextLong();
                    change(1, x, y, k, 0);
                }else if (op == 2) {
                    long k = sc.nextLong();
                    change(1, x, y,1, k);
                }else if (op == 3) {
                    System.out.println(query(1, x, y));
                }
            }
        }
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}