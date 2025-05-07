package 数据结构.树.subject.线段树.P1531_I_Hate_It;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solution();
    }
}


class Solution{
    private int n, m;
    private int[] a = new int[200001]; // 原始数组
    private int[] tree = new int[800001]; // 线段树，4倍空间

    // 上传，更新最大
    private void push_up(int rt){
        tree[rt] = Math.max(tree[rt * 2], tree[rt * 2 + 1]);
    }

    // 建树
    private void build(int rt, int l, int r) {
        if(l == r){
            tree[rt] = a[l];  // 叶子节点直接赋值
            return;
        }
        int mid = (l + r) / 2;
        build(rt * 2, l, mid);
        build(rt * 2 + 1, mid + 1, r);
        push_up(rt);
    }


    // 单点修改
    private void modify(int rt, int l, int r, int x, int y){
        if(l == r){
            if(tree[rt] < y){
                // 只有修改后的值更大时才更新
                tree[rt] = y;
            }
            return;
        }

        int mid = (l + r) / 2;
        if(x <= mid) modify(rt * 2, l, mid, x, y);  // 左子树修改
        else modify(rt * 2 + 1, mid + 1, r, x, y);  // 右子树修改
        push_up(rt); // 父节点更新
    }


    // 区间查询
    private int query(int rt, int l, int r, int x, int y){
        // 正好在范围内
        if(x <= l && r <= y) return tree[rt];
        int mid = (l + r) / 2;
        int ans = Integer.MIN_VALUE; // 初始值为负无穷
        if(x <= mid) ans = Math.max(ans, query(rt * 2, l, mid, x, y)); // 左子树查询
        if(y > mid) ans = Math.max(ans, query(rt * 2 + 1, mid + 1, r, x, y)); // 右子树查询
        return ans;
    }


    public Solution(){
        FastReader sc = new FastReader();
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 1; i <= n; i++) a[i] = sc.nextInt();

        build(1, 1, n); // 构建线段树

        for(int i = 1; i <= m; i++){
            String ope = sc.next(); // 操作类型
            int x = sc.nextInt();
            int y = sc.nextInt();
            if(ope.equals("Q")){
                // 查询
                System.out.println(query(1, 1, n, x, y));
            }else {
                // 修改
                modify(1, 1, n, x, y);
            }
        }
    }


    private class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        String next() {
            while (!st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}