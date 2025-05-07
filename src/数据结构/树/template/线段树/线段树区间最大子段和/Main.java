package 数据结构.树.template.线段树.线段树区间最大子段和;

// 线段树维护最大字段和
// 需要维护四个信息：区间和sum，最大左字段和lmx，最大右字段和rmx，区间最大子段和mx

// 向上合并信息：
// 父节点最大字段和：max{L.mx， R.mx， L.mx + R.mx}
// 父节点最大左字段和：max{L.lmx,L.sum + R.lmx}
// 父节点最大右字段和:max{R.rmx, L.rmx + R.sum}

import com.sun.source.tree.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions{
    private class TreeNode{
        int l, r;
        int sum;
        int lmx, rmx, mx;

        public TreeNode(int l, int r, int sum, int lmx, int rmx, int mx){
            this.l = l;
            this.r = r;
            this.sum = sum;
            this.lmx = lmx;
            this.rmx = rmx;
            this.mx = mx;
        }
    }
    private TreeNode[] tree;
    private int n, m;
    private int[] w;

    private void push_up(TreeNode u, TreeNode l, TreeNode r){
        u.sum = l.sum + r.sum;    // 左右子区间的sum拼凑
        u.mx = Math.max(Math.max(l.mx, r.mx), l.mx + r.mx);
        u.lmx = Math.max(l.lmx, r.lmx + l.sum);
        u.rmx = Math.max(r.rmx, l.rmx + r.sum);
    }

    private void build(int p, int l, int r){
        tree[p] = new TreeNode(l, r, w[r], w[r], w[r], w[r]);
        if(l == r) return;
        int m = (l + r) / 2;
        build(2 * p, l, m);
        build(2 * p + 1, m + 1, r);
        push_up(tree[p], tree[2 * p], tree[2 * p + 1]);
    }

    /**
     * 点修
     * @param p 根节点
     * @param l 左端点
     * @param r 右端点
     */
    private void change(int p, int l, int r){
        if(l > tree[p].r || r < tree[p].l) return;
        // 更新处理线段树的叶子节点
        if(tree[p].l == tree[p].r){
            tree[p] = new TreeNode(l, l, r, r, r, r);
            return;
        }
        int m = (tree[p].l + tree[p].r) >> 1;
        if(l <= m) change(2 * p, l, m);
        else change(2 * p + 1, m + 1, r);
        push_up(tree[p], tree[2 * p], tree[2 * p + 1]);
    }

    /**
     * 区间查询
     * @param p 根节点
     * @param l 左端点
     * @param r 右端点
     * @return 区间最大字段和
     */
    private TreeNode query(int p, int l, int r){
        // 完全覆盖直接返回
        if(l <= tree[p].l && tree[p].r <= r) return tree[p];
        int m = (tree[p].l + tree[p].r) >> 1;
        if(r <= m) return query(2 * p, l, r);
        if(l > m) query(2 * p + 1, l, r);
        // 跨左右子树，开一个临时的节点
        TreeNode T = new TreeNode(0, 0, 0, 0, 0, 0);
        // 分别查询左右子树，并将结果合并存储，再上传更新本来那个位置的节点
        push_up(T, query(p * 2, l, m), query(p * 2 + 1, m + 1, r));
        return T;
    }

    public Solutions(){
        FastReader sc = new FastReader();
        n = sc.nextInt();
        m = sc.nextInt();

        w = new int[n + 1];
        tree = new TreeNode[4 * n + 1];

        for(int i = 1; i <= n; i++){
            w[i] = sc.nextInt();
        }

        build(1, 1, n);

        while (m-- > 0) {
            int k = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();
            if(k == 1){
                if(x > y){
                    int temp = x;
                    x = y;
                    y = temp;
                }
                System.out.println(query(1, x, y).mx);
            }else{
                change(1, x, y);
            }
        }
    }


    class FastReader{
        BufferedReader br;
        StringTokenizer st;
        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }
        String next(){
            while(!st.hasMoreElements()){
                try{
                    st = new StringTokenizer(br.readLine());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt(){
            return Integer.parseInt(next());
        }
    }
}