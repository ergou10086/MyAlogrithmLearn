package 数据结构.树.subject.线段树.P1198_JSOI2008_最大数;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Soultions();
    }
}


class Soultions{
    private long[] w;  // 数组w，用来存储输入的值
    // 没有修改，貌似不用懒标记
    private class TreeNode{
        int l, r;
        long max;   // 区间最大值
    }
    private TreeNode[] tree;   // 需要开4n空间



    public Soultions(){
        FastReader sc = new FastReader();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int M = sc.nextInt();
        int D = sc.nextInt();

        w = new long[M + 1];
        tree = new TreeNode[4 * (M + 1)];
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new TreeNode();
        }

        long lastQuery = 0;    // 上次查询值
        int index = 0;    // 当前数列长度

        for(int i = 1; i <= M; i++){
            String operation = sc.next();
            int num = sc.nextInt();

            // 插入
            if (operation.equals("A")) {
                int value = (int) ((num + lastQuery) % D);
                w[index] = value;
                update(1, 0, M - 1, index, value);   // 插入到数列的末尾
                index++;
            // 查询
            }else if (operation.equals("Q")) {
                int L = num;   // 当前数列中末尾 L 个数
                lastQuery = query(1, 0, M - 1, index - L, index - 1);  // 查询最大值
                try {
                    bw.write(lastQuery + "\n");         // 输出结果
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        try {
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 更新
     * @param node 根节点
     * @param l    左端
     * @param r    右端
     * @param idx  位置，因为是在数列末端插入
     * @param val  值
     */
    private void update(int node, int l, int r, int idx, long val){
        // 完全覆盖，直接更新
        if(l == r){
            tree[node].max = val;
            return;
        }

        int mid = (l + r) / 2;
        if(idx <= mid){
            // 更新左子树
            update(2 * node, l, mid, idx, val);
        }else {
            update(2 * node + 1, mid + 1, r, idx, val);  // 更新右子树
        }
        tree[node].max = Math.max(tree[2 * node].max, tree[2 * node + 1].max);
    }

    /**
     * 查询区间最大值
     * @param node  根节点
     * @param l     左端
     * @param r     右端
     * @param ql   数列末端左起点
     * @param qr   数列末端右端点
     * @return
     */
    private long query(int node, int l, int r, int ql, long qr){
        // 没有交集
        if (ql > r || qr < l) {
            return Long.MIN_VALUE;
        }
        // 完全覆盖，查询完毕
        if(ql <= l && qr >= r){
            return tree[node].max;
        }
        int mid = (l + r) / 2;
        long leftMax = query(2 * node, l, mid, ql, qr);
        long rightMax = query(2 * node + 1, mid + 1, r, ql, qr);
        return Math.max(leftMax, rightMax);
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