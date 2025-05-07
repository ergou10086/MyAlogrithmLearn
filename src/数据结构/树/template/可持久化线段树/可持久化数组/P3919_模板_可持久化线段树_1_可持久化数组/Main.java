package 数据结构.树.template.可持久化线段树.可持久化数组.P3919_模板_可持久化线段树_1_可持久化数组;

import org.w3c.dom.Node;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 可持久化线段树：支持回退，访问之前版本的线段树
// 可持久化数组也一样

// 维护：用可持久化线段树维护可持久化数组，叶子节点存储数组元素的值，其他节点不存信息。
// 但是其他节点按照线段树那样，把数组拆分成一段，存取下标的一个区间
// 每次只修改logn节点，故只增加修改的节点即可

// 和可持久化线段树一样
// 动态开点存储：对于每个节点，应该保存左右孩子的编号
// 对于每个历史版本，应该保存根节点的编号

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions{
    private final int N = 1000005;
    private int n, m;
    private int[] a = new int[N];
    private  List<Node> tree = new ArrayList<>();
    private List<Integer> root = new ArrayList<>();// 历史版本根节点
    private int idx;   // 新开节点编号

    private class Node{
        int l, r;
        int v;

        Node(){}
        Node(int l, int r, int v){
            this.l = l;
            this.r = r;
            this.v = v;
        }
    }


    // 递归创建[1,n]内的各个节点
    // 这里返回idx为当前版本的根节点
    private int build(int l, int r, int[] a) {
        Node node = new Node();
        tree.add(node);
        int idx = tree.size() - 1;

        if(l == r){
            node.v = a[l];
            return idx;
        }
        int mid = (l + r) >> 1;
        node.l = build(l, mid, a);
        node.r = build(mid + 1, r, a);
        return idx;
    }

    /**
     * 在某个历史版本上修改某个位置的值
     * @param y 被修改版本的结点指针
     * @param l 左区间端点
     * @param r 右端点
     * @param pos 需要修改的位置
     * @param v 需要修改的值
     * @return 返回新版本的根节点索引
     */
    private int modify(int y, int l, int r, int pos, int v){
        // 获取当前版本的节点
        Node nodeY = tree.get(y);
        // 位置找到修改
        if(l == r){
            Node newNode = new Node();
            newNode.v = v;
            tree.add(newNode);
            return tree.size() - 1;  // 返回新节点的索引
        }
        // 二分寻找
        int mid = (l + r) >> 1;
        // 目标位置 pos 在左子树
        if (pos <= mid) {
            // 生成一个新的左子树根节点索引
            int newLeft = modify(nodeY.l, l, mid, pos, v);
            // 创建一个新节点 newNode，其左子节点为 newLeft，右子节点复用当前节点的右子节点 nodeY.r。
            // 保证递归修改左子树
            Node newNode = new Node(newLeft, nodeY.r, 0);
            tree.add(newNode);
            return tree.size() - 1;
        }else{
            int newRight = modify(nodeY.r, mid + 1, r, pos, v);
            Node newNode = new Node(nodeY.l,newRight ,0);
            tree.add(newNode);
            return tree.size() - 1;
        }
    }

    /**
     * 查询某个历史版本上的某一位置的值
     * @param x  某个历史版本
     * @param l  分裂的下标区间左
     * @param r  右
     * @param pos  查询位置
     * @return 值
     */
    private int query(int x, int l, int r, int pos){
        Node nodeX = tree.get(x);
        if(l == r) return nodeX.v;

        int mid = (l + r) >> 1;
        if (pos <= mid) {
            return query(nodeX.l, l, mid, pos);
        }else {
            return query(nodeX.r, mid + 1, r, pos);
        }
    }

    public Solutions(){
        FastReader sc = new FastReader();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = sc.nextInt();
        m = sc.nextInt();

        for(int i = 1; i <= n; i++){
            a[i] = sc.nextInt();
        }

        tree.add(new Node());
        root.add(0);
        int initalRoot = build(1, n, a);
        root.set(0, initalRoot);


        for (int i = 1; i <= m; i++) {
            int ver = sc.nextInt();
            int op = sc.nextInt();
            if (op == 1) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int newRoot = modify(root.get(ver), 1, n, x, y);
                root.add(newRoot);
            } else {
                int x = sc.nextInt();
                int res = query(root.get(ver), 1, n, x);

                try {
                    bw.write(res);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                root.add(root.get(ver));
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
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }
        int nextInt(){
            return Integer.parseInt(next());
        }
    }
}