package 数据结构.树.template.平衡树.Splay普通平衡树.Luogu_P3369;

// 二叉查找树BST
// 允许快速查找，插入，删除某一个节点的一个容器
// 左小右大，中序遍历是有序的

// Splay平衡树，通过不断将某个节点旋转到根节点，使得整棵树满足BST的性质，并且保持平衡

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args){
        new Solutions();
    }
}

class Solutions{
    // 平衡树节点
    private class Node{
        int[] ch = new int[2];   // x0代表左儿子，x1代表右儿子
        int fa;   // 父节点
        int value;   // 权值
        int cnt;   // 权值出现的次数
        int siz;   // 以这个点为根的子树的大小

        // 初始化
        public void init(int p, int v1){
            fa = p;
            value = v1;
            cnt = siz = 1;
        }
    }

    private int root, tot;  //根节点和当前节点的总数。
    private final int INF = (1 << 30) + 1, N = 1100010;
    private Node[] tree = new Node[N];

    {
        for (int i = 0; i < N; i++) {
            tree[i] = new Node();
        }
    }

    // 上传，更新节点的大小
    private void pushup(int x) {
        // 左子树 + 右子树 + 当前x的点的个数（插入多次）
        tree[x].siz = tree[tree[x].ch[0]].siz + tree[tree[x].ch[1]].siz + tree[x].cnt;
    }

    // 旋转，用于维护树的平衡
    // 左旋：左儿子节点x旋转上去把父节点y顶成其x的右儿子节点，x的左儿子不动，右儿子成为y的左儿子
    // 右旋差不多情况
    private void rotate(int x){
        int y = tree[x].fa;  // 取出x的父节点y
        int z = tree[y].fa;  // 取出y的父节点z
        int k = tree[y].ch[1] == x ? 1 : 0;   // k 表示 x 是 y 的右子节点（1,右旋）还是左子节点（0，左旋）

        // 将 x 提升为 z 的子节点，替换掉y
        // tree[z].ch[1] == y ? 1 : 0是在找y是z的左儿子还是右儿子，然后用x替换掉y
        tree[z].ch[tree[z].ch[1] == y ? 1 : 0] = x;
        tree[x].fa = z;

        // 将 x 的异侧子节点（与 x 在 y 的异侧）挂到 y 上
        // tree[x].ch[k ^ 1]表示 x 中在 y 的异侧子节点
        tree[y].ch[k] = tree[x].ch[k ^ 1];
        tree[tree[x].ch[k ^ 1]].fa = y;

        // 将 y 挂到 x 的异侧
        tree[x].ch[k ^ 1] = y;
        tree[y].fa = x;

        // 更新 y 和 x 的子树大小
        pushup(y);
        pushup(x);
    }


    // 伸展操作，访问一个节点x，并且把x旋转到根节点
    // 如果y就是根，做单旋
    // y不是根，直线型，做双旋
    // y不是根，折线型，做双旋

    /**
     *
     * @param x 要上升的节点
     * @param k x要做k的儿子
     */
    private void splay(int x, int k) {
        while(tree[x].fa != k){
            int y = tree[x].fa;  // 父亲
            int z = tree[y].fa;  // 爷爷

            if(z != k){
                // y的左儿子是x，z的左儿子是y，是直线型，转x
                if ((tree[y].ch[1] == x) ^ (tree[z].ch[1] == y)) {
                    rotate(x);
                }else {
                    // 折线形转y
                    rotate(y);
                }
            }
            // 第二次都转x
            rotate(x);
        }
        // k=0时,x转到根，k>0时，把x转到k下面
        if(k == 0) root = x;
    }


    // 查找，找到v所在节点，并把该节点转到根
    private void find(int v) {
        int x = root;
        // v > tree[x].value ? 1 : 0 是指 要找的编号v如果大于x的值，往右子树上找，不是往左子树上找
        // tree[x].ch[v > tree[x].value ? 1 : 0] != 0 保证找到的节点不空
        // v == tree[x].value 是找到了，找到退出
        while(tree[x].ch[v > tree[x].value ? 1 : 0] != 0 && v != tree[x].value){
            x = tree[x].ch[v > tree[x].value ? 1 : 0];
        }
        // 找到后转到根节点，调整高度，为了下一步操作
        splay(x, 0);
    }

    // 插入
    private void insert(int v) {
        int x = root, p = 0;  // p实际上是记录父节点
        // x为0，走到了空节点上，要么这个点的值找到了，退出
        while (x != 0 && tree[x].value != v) {
            p = x;
            // 比x点大，右子树，小就左子树上走
            x = tree[x].ch[v > tree[x].value ? 1 : 0];
        }
        if (x != 0) {
            // 非空节点cnt加一
            tree[x].cnt++;
        } else {
            // 插入了一个不存在的点
            x = ++tot;   // 节点数量增加
            tree[p].ch[v > tree[p].value ? 1 : 0] = x;   // 考虑x插入p的哪一边
            tree[x].init(p, v);  // 建节点
        }
        splay(x, 0);
    }


    // 求v的前驱，返回节点编号
    private int getpre(int v) {
        find(v);
        // x赋值为根节点编号
        int x = root;
        // 如果编号x的值小于v，为前驱，返回x
        if(tree[x].value < v) return x;
        // 取其左子树
        x = tree[x].ch[0];
        // tree[x].ch[1] != 0保证沿着右子树走
        while (tree[x].ch[1] != 0) x = tree[x].ch[1];
        splay(x, 0);
        return x;
    }


    // 求v的后继，返回节点编号
    private int getsuc(int v) {
        find(v);
        int x = root;
        if (tree[x].value > v) return x;
        x = tree[x].ch[1];
        while (tree[x].ch[0] != 0) x = tree[x].ch[0];
        splay(x, 0);
        return x;
    }

    // 删除
    // 把一个节点移动到叶子节点上去
    private void del(int v) {
        int pre = getpre(v);   // 找前驱节点
        int suc = getsuc(v);   // 找后继节点
        splay(pre, 0);  // 前驱节点转到根上去
        splay(suc, pre);  // 要删除的点的后继节点转到前驱节点下面去，此时该节点就转到叶子节点去了
        int del = tree[suc].ch[0];
        // 个数大于1
        if(tree[del].cnt > 1){
            tree[del].cnt--;
            splay(del, 0);  // 更新受影响的子树
        }else{
            tree[suc].ch[0] = 0;  // 清空
            splay(suc, 0);   // 更新子树
        }
    }

    // 查排名
    private int getrank(int v){
        // 先插入v
        insert(v);
        // 返回左子树的大小
        int res = tree[tree[root].ch[0]].siz;
        // 删除v
        del(v);
        // 返回左子树的大小就行，这是性质
        return res;
    }

    // 查排名为k的数
    private int getval(int k){
        int x = root;
        while (true){
            // y是x的左儿子编号
            int y = tree[x].ch[0];
            // 左子树的大小比排名大，排名小，需要在左子树上找
            if (k <= tree[y].siz) {
                x = tree[x].ch[0];
            // 因为有多个x可能，此时已经符合了
            } else if (k <= tree[y].siz + tree[x].cnt) {
                break;
            // 排名大，右子树上找
            } else {
                k -= tree[y].siz + tree[x].cnt;
                x = tree[x].ch[1];
            }
        }
        splay(x, 0);    // 将访问的节点 伸展到根节点
        return tree[x].value;
    }

    public Solutions() {
        FastReader scanner = new FastReader();
        int n = scanner.nextInt();
        // 哨兵，保证找到任何一个数的前驱和后继
        insert(-INF);
        insert(INF);
        for (int i = 0; i < n; i++) {
            int op = scanner.nextInt();
            int x = scanner.nextInt();
            switch (op) {
                case 1:
                    insert(x);
                    break;
                case 2:
                    del(x);
                    break;
                case 3:
                    System.out.println(getrank(x));
                    break;
                case 4:
                    System.out.println(getval(x + 1));
                    break;
                case 5:
                    System.out.println(tree[getpre(x)].value);
                    break;
                case 6:
                    System.out.println(tree[getsuc(x)].value);
                    break;
            }
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
        while (!st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt(){
        return Integer.parseInt(next());
    }
}

