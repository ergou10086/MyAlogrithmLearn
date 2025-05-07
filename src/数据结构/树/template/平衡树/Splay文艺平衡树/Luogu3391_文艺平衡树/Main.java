package 数据结构.树.template.平衡树.Splay文艺平衡树.Luogu3391_文艺平衡树;

// 文艺平衡树用于维护有序序列并且支持翻转操作，它在中序遍历时保持下标递增
// Splay树可以动态维护一个有序序列，中序遍历就是该有序序列
// Splay树也可以维护一个区间翻转序列，中序遍历就是每次区间翻转后的序列

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
    class Node{
        int[] ch = new int[2];
        int fa;
        int val;
        int siz;
        int tag;  // 懒标记

        private void init(int p1, int v2){
            fa = p1;
            val = v2;
            siz = 1;
        }
    }
    private int n,  m;
    private final int N = 100010;
    private Node[] tree = new Node[N];
    private int root, idx;

    {
        for (int i = 0; i < N; i++) {
            tree[i] = new Node();
        }
    }

    // 上传
    private void push_up(int x){
        tree[x].siz = tree[tree[x].ch[0]].siz + tree[tree[x].ch[1]].siz + 1;
    }


    // 旋转
    private void rotate(int x){
        int y = tree[x].fa;
        int z = tree[y].fa;
        int k = tree[y].ch[1] == x ? 1 : 0;

        // 找z的儿子y，用y的儿子x替换掉y（x的父亲改为z）
        tree[z].ch[tree[z].ch[1] == y ? 1 : 0] = x;
        tree[x].fa = z;

        // x的异侧节点挂到y上
        tree[y].ch[k] = tree[x].ch[k ^ 1];
        // 此时x的异侧节点的父亲为y
        tree[tree[x].ch[k ^ 1]].fa = y;

        // y挂到x的异侧
        tree[x].ch[k ^ 1] = y;
        tree[y].fa = x;

        push_up(y);
        push_up(x);
    }

    // 伸展
    private void splay(int x, int k){
        while(tree[x].fa != k){
            int y = tree[x].fa;
            int z = tree[y].fa;

            if(z != k){
                if ((tree[y].ch[1] == x) ^ (tree[z].ch[1] == y)) {
                    rotate(x);
                } else {
                    rotate(y);
                }
            }
            rotate(x);
        }
        // k=0时,x就相当于转到根
        if(k == 0) root = x;
    }

    // 插入节点
    private void insert(int v){
        int x = root, p = 0;
        // 放置插入的节点
        while(x != 0){
            p = x;
            // v大于x，右子树，小于就是左子树
            x = tree[x].ch[v > tree[x].val ? 1 : 0];
        }

        x = ++idx;
        // judgement v on left or right
        tree[p].ch[v > tree[p].val ? 1 : 0] = x;
        tree[x].init(p, v);
        splay(x, 0);
    }

    // 下传
    private void push_down(int x){
        // 懒标记为0，不需要翻转下面的两个儿子，1需要
        if(tree[x].tag != 0){
            // 左右子树翻转操作
            int temp = tree[x].ch[0];
            tree[x].ch[0] = tree[x].ch[1];
            tree[x].ch[1] = temp;

            // 两个儿子打上懒标记
            tree[tree[x].ch[0]].tag ^= 1;
            tree[tree[x].ch[1]].tag ^= 1;
            // 恢复
            tree[x].tag = 0;
        }
    }

    // 返回第k个节点的编号
    private int get_k(int k){
        int x = root;
        while(true){
            // 先下传懒标记
            push_down(x);
            // 获取当前节点 x 的左子节点编号
            int y = tree[x].ch[0];
            // k太大了，第 k 个节点不在左子树和当前节点中，而在右子树中
            if(tree[y].siz + 1 < k){
                // 需要减掉左子树及其本身
                k -= tree[y].siz + 1;
                // 更新为右子节点
                x = tree[x].ch[1];
            // 第 k 个节点在左子树中
            }else if(tree[y].siz >= k) x = y;  // 将当前节点更新为左子节点
            else return x;
        }
    }

    // 中序遍历输出
    private void output(int x){
        push_down(x);
        // 左儿子不空，走到左子树上去
        if(tree[x].ch[0] != 0) output(tree[x].ch[0]);
        // 输出在中间，是中序遍历，注意过滤哨兵
        if(tree[x].val >= 1 && tree[x].val <= n){
            System.out.print(tree[x].val + " ");
        }
        // 右儿子不空，走到右子树上去
        if(tree[x].ch[1] != 0) output(tree[x].ch[1]);
    }

    public Solutions(){
        insert(-1000006);
        insert(1000006);
        FastReader sc = new FastReader();
        n = sc.nextInt();
        m = sc.nextInt();
        for(int i = 1; i <= n; i++) insert(i);

        while(m-- > 0){
            // 把[l,r]挤在[l-1,r+1]，也就是前驱和后继之间
            int l = sc.nextInt();
            int r = sc.nextInt();
            l = get_k(l);  // 前驱点的节点编号,l-1+1=l
            r = get_k(r + 2);  // 后继点的节点编号，本来是r+1，多了一个哨兵被影响了，r+2
            // 前驱点转到根上，后继点转到前驱点下面，这样区间都会被夹在左子树上
            splay(l, 0);
            splay(r, l);
            // 区间需要交换，打上懒标记
            tree[tree[r].ch[0]].tag ^= 1;
        }
        output(root);
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
            } catch(IOException e){
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt(){
        return Integer.parseInt(next());
    }
}