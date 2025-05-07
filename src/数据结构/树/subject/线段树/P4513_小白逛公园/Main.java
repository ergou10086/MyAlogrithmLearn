package 数据结构.树.subject.线段树.P4513_小白逛公园;

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
    class TreeNode{
        int l, r;
        int sum;
        int mx, lmx, rmx;

        public TreeNode(int l, int r, int sum, int mx, int lmx, int rmx) {
            this.l = l;
            this.r = r;
            this.sum = sum;
            this.mx = mx;
            this.lmx = lmx;
            this.rmx = rmx;
        }
    }
    private TreeNode[] tree;
    private int n, m;
    private int[] w;

    private void push_up(TreeNode t, TreeNode l, TreeNode r){
        t.sum = l.sum + r.sum;
        t.lmx = Math.max(l.lmx, l.sum + r.lmx); // 当前节点的 lmx
        t.rmx = Math.max(r.rmx, r.sum + l.rmx); // 当前节点的 rmx
        t.mx = Math.max(Math.max(l.mx, r.mx), l.rmx + r.lmx); // 当前节点的 mx
    }

    private void build(int p, int l, int r){
        tree[p] = new TreeNode(l, r, 0, 0, 0, 0);
        if (l == r) {
            tree[p].sum = w[l];
            tree[p].mx = w[l];
            tree[p].lmx = w[l];
            tree[p].rmx = w[l];
            return;
        }
        int mid = (l + r) / 2;
        build(2 * p, l, mid);
        build(2 * p + 1, mid + 1, r);
        push_up(tree[p], tree[2 * p], tree[2 * p + 1]);
    }

    private void change(int u, int p, int s){
        if(tree[u].l == tree[u].r){
            tree[u].sum = s;
            tree[u].mx = s;
            tree[u].lmx = s;
            tree[u].rmx = s;
            return;
        }
        int mid = (tree[u].l + tree[u].r) / 2;
        if (p <= mid) {
            change(2 * u, p, s);
        } else {
            change(2 * u + 1, p, s);
        }
        push_up(tree[u], tree[2 * u], tree[2 * u + 1]);
    }

    private TreeNode query(int p, int a, int b){
        if (a <= tree[p].l && b >= tree[p].r) {
            return tree[p];
        }

        int m = (tree[p].l + tree[p].r) >> 1;
        if(b <= m) return query(2 * p, a, b);
        if(a > m) return query(2 * p + 1, a, b);
        TreeNode left = query(2 * p, a, m);
        TreeNode right = query(2 * p + 1, m + 1, b);
        TreeNode result = new TreeNode(0, 0, 0, 0, 0, 0);
        push_up(result, left, right);
        return result;
    }

    public Solutions(){
        FastReader sc = new FastReader();
        n = sc.nextInt();
        m = sc.nextInt();
        tree = new TreeNode[4 * n]; //  4 * n
        w = new int[n + 1];

        for(int i = 1; i <= n; i++){
            w[i] = sc.nextInt();
        }

        build(1, 1, n);

        while(m-- > 0){
            int op = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            if(op == 1){
                if(a > b){
                    int temp = a;
                    a = b;
                    b = temp;
                }
                TreeNode result = query(1, a, b);
                if(result != null) {
                    System.out.println(result.mx);
                }
            }else if(op == 2){
                change(1, a, b);
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


/*
优化版
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class TreeNode {
        int sum, mx, lmx, rmx;
    }

    static TreeNode[] tree;
    static int[] w;

    static void push_up(TreeNode t, TreeNode l, TreeNode r) {
        t.sum = l.sum + r.sum;
        t.lmx = Math.max(l.lmx, l.sum + r.lmx);
        t.rmx = Math.max(r.rmx, r.sum + l.rmx);
        t.mx = Math.max(Math.max(l.mx, r.mx), l.rmx + r.lmx);
    }

    static void build(int p, int l, int r) {
        tree[p] = new TreeNode();
        if (l == r) {
            tree[p].sum = w[l];
            tree[p].mx = w[l];
            tree[p].lmx = w[l];
            tree[p].rmx = w[l];
            return;
        }
        int mid = (l + r) / 2;
        build(2 * p, l, mid);
        build(2 * p + 1, mid + 1, r);
        push_up(tree[p], tree[2 * p], tree[2 * p + 1]);
    }

    static void change(int p, int l, int r, int pos, int val) {
        if (l == r) {
            tree[p].sum = val;
            tree[p].mx = val;
            tree[p].lmx = val;
            tree[p].rmx = val;
            return;
        }
        int mid = (l + r) / 2;
        if (pos <= mid) {
            change(2 * p, l, mid, pos, val);
        } else {
            change(2 * p + 1, mid + 1, r, pos, val);
        }
        push_up(tree[p], tree[2 * p], tree[2 * p + 1]);
    }

    static TreeNode query(int p, int l, int r, int a, int b) {
        if (a <= l && r <= b) {
            return tree[p];
        }
        int mid = (l + r) / 2;
        if (b <= mid) {
            return query(2 * p, l, mid, a, b);
        }
        if (a > mid) {
            return query(2 * p + 1, mid + 1, r, a, b);
        }
        TreeNode left = query(2 * p, l, mid, a, mid);
        TreeNode right = query(2 * p + 1, mid + 1, r, mid + 1, b);
        TreeNode result = new TreeNode();
        push_up(result, left, right);
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        w = new int[n + 1];
        tree = new TreeNode[4 * (n + 1)];

        for (int i = 1; i <= n; i++) {
            w[i] = Integer.parseInt(br.readLine());
        }

        build(1, 1, n);

        StringBuilder sb = new StringBuilder();
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (op == 1) {
                if (a > b) {
                    int temp = a;
                    a = b;
                    b = temp;
                }
                TreeNode result = query(1, 1, n, a, b);
                sb.append(result.mx).append("\n");
            } else if (op == 2) {
                change(1, 1, n, a, b);
            }
        }
        System.out.print(sb);
    }
}

* */