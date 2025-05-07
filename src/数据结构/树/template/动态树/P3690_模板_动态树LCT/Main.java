package 数据结构.树.template.动态树.P3690_模板_动态树LCT;

// 动态树（Link-Cut Tree, LCT）是一种用于维护动态森林（即支持动态加边、删边的树结构）的数据结构。
// 它的核心思想是通过 Splay 树 和 虚实边划分 来维护树的结构。

// 实链剖分，使用splay这种动态数据结构来维护实链，通过对实链的拆分和组合，构造答案

// 一个节点只能选一个儿子做实儿子，其他都是虚儿子
// 实边：父节点与实儿子之间的边，是双向边，是一个 Splay 树中的父子关系
// 虚边：由虚儿子指向父节点的边，是单向边（认父不认子），在森林中是连接不同 Splay 树的边
// 实链：由实边构成的链，每条实链的节点深度的严格递增的
// 也就是
// 每个节点最多有一条实边（连接到它的一个孩子），其他边都是虚边。
// 实边构成了 Splay 树，而虚边则用于连接不同的 Splay 树。

// 辅助树：由若干splay构成，与原树等价，所以我们只需要维护辅助树

// 每条实链用一个splay维护，中序遍历按深度严格递增（辅助树实链的中序遍历在原树的表现是深度递增的）
// Splay 树的中序遍历对应实链的节点顺序。
// 1 区分树根（原树的根节点）和splay的根（Splay 树的根：Splay树自己的根，Splay 树的节点：表示实链中的节点）
// 2 虚边从下面splay的根指向上面splay中的父亲（下面的splay根通过虚边指向的父亲是也是其原树的父亲，是唯一的）（父亲唯一，儿子变）
// 3 实边和虚边是动态变化的

// 一些辅助树构成了LCT，其维护的是整个动态森林

// 虚边的具体实现
// 如果 x 是某个 Splay 树的根节点，那么 fa(x) 指向的是虚边的父亲节点。（沿其虚边指向该点的父节点）
// 如果 x 不是根节点，那么 fa(x) 指向的是实边的父亲节点（跟关系2连接起来了）

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
    static final int N = 100010;
    static Node[] tree = new Node[N];

    private class Node{
        int[] ch = new int[2];   // 左右孩子
        int fa;   // 父节点
        int v;    // 节点值
        int sum;  // 子树异或和
        int tag;  // 翻转标记
        public Node() {
            ch[0] = ch[1] = fa = v = sum = tag = 0;
        }
    }

    // 判断点x是不是其所在splay树的根节点
    private boolean not_root(int x){
        // 如果一个节点是其父节点的左孩子或右孩子，那么它一定不是根节点。
        return tree[tree[x].fa].ch[0] == x || tree[tree[x].fa].ch[1] == x;
    }

    // 上传
    private void push_up(int x){
        // 询问的是从 x 到 y 的路径上的点的权值的 xor 和，上传肯定是左右孩子加自身的异或和
        tree[x].sum = tree[tree[x].ch[0]].sum ^ tree[x].v ^ tree[tree[x].ch[1]].sum;
    }

    // 下传
    private void push_down(int x){
        // 懒标记为0，不需要翻转下面的两个儿子，1需要
        if(tree[x].tag != 0){
            // 先进行左右子树翻转
            int temp = tree[x].ch[0];
            tree[x].ch[0] = tree[x].ch[1];
            tree[x].ch[1] = temp;

            // 给两个儿子打上懒标记
            tree[tree[x].ch[0]].tag ^= 1;
            tree[tree[x].ch[1]].tag ^= 1;
            // 清空自己的标记
            tree[x].tag = 0;
        }
    }

    // 递归下传
    private void push_all(int x){
        // 只要不是根，就向上找
        if(not_root(x)) push_all(x);
        // 然后一路下传
        push_down(x);
    }

    // 旋转
    // 节点 x 提升到其父节点 y 的位置
    private void rotate(int x){
        int y = tree[x].fa, z = tree[y].fa;
        int k = tree[y].ch[1] == x ? 1 : 0;   // x是y的右儿子吗(k指明x是y的那一边儿子)

        // 加判断y是不是所在splay树的根节点
        // 为了保持两个splay之间的虚边性质，在转的时候需要保持是虚边
        // 也就是虚边从下面splay的根需要指向上面splay中的父亲
        // 这里改变x和y的父子关系是和文艺一样不变的
        // 而且这里连边顺序必须是第一个，要不然y必然有父亲
        if(not_root(y)) tree[z].ch[tree[z].ch[1] == y ? 1 : 0] = x;
        tree[x].fa = z;

        /*
              y
             / \
            x   C
           / \
          A   B

      x
     / \
    A   y       x 的右孩子 B 挂到 y 的左孩子位置。
       / \
      B   C
         */
        // 把和x的异侧的儿子挂到y上，这里的异侧是相对于y和x的关系的异侧
        tree[y].ch[k] = tree[x].ch[k^1];
        // 修改x的异侧子树的父亲为y
        tree[tree[x].ch[k^1]].fa = y;

        // 把y挂到x的异侧，修改y的父亲为x，完成旋转
        tree[x].ch[k^1] = y;
        tree[y].fa = x;
    }

    // 将x伸展到根
    private void splay(int x){
        push_all(x);
        while(not_root(x)){
            int y = tree[x].fa;
            int z = tree[y].fa;
            if(not_root(y)){
                // x，y，z在一个方向上，转x，否则转y
                if ((tree[y].ch[1] == x) ^ (tree[z].ch[1] == y)) rotate(x);
                else rotate(y);
            }
            rotate(x);
        }
    }

    // 打通x到根的路径，把路径变成一条实链（放在一个splay内维护）
    // 其中，在路过一个splay树的时候，不需要的点，要排除（实-虚）
    private void access(int x) {
        // x不断沿着父关系走，y记录后继
        for(int y = 0; x != 0; y = x, x = tree[x].fa){
            // 当前点转到所在splay的根
            splay(x);
            tree[x].ch[1] = y;   // 将 x 的右孩子设置为 y（那么x原来的右儿子就会变成虚边。把不在路径上的节点排除）
            push_up(x);        // 更新 x 的信息
        }
    }

    // 换根
    // 把指定点x变成树根，把x提起来，让x称为深度最小的点
    private void change_root(int x){
        access(x);   // 需要先打通到11
        splay(x);   // 转到根上去
        tree[x].tag ^= 1;   // 打标记
    }

    // 分离x到y的路径
    // 也就是把x到y的路径变成一条实链，把y转到根
    private void split(int x, int y){
        change_root(x);   // 先把x换到根
        access(y);       // 然后打通y到根(x)
        splay(y);      // 最好把y转到根
    }

    // 输出x到y路径的异或和
    private void output(int x, int y) {
        split(x, y);
        System.out.println(tree[y].sum);
    }

    // 查找x的根
    // 把一个splay树的根找到并且转到根上去
    private int findroot(int x){
        access(x);   // 先打通
        splay(x);    // 再伸展到根上
        // 不断去找左儿子
        while(tree[x].ch[0] != 0){
            push_down(x);
            x = tree[x].ch[0];
        }

        splay(x); // 防止卡链
        return x;
    }

    // 连接x和y的边
    /*假设我们要连接2 - 5
    树 1:      树 2:          1转完后
         1         4             2
        / \       / \           / \
       2   3     5   6         1   3
     连接：
        4
       /|\
      5 1 6
       \
        2
         \
          3
     */
    private void link(int x, int y){
        // 先将 x 提升到其所在树的根节点
        // 每个节点的父节点指针 fa 可能指向虚边，其fa可能指向另一个节点
        change_root(x);
        // 再将 x 的父节点指向 y，前提肯定是不在一棵树上
        if(findroot(y) != x) tree[x].fa = y;
    }

    // 断边
    private void cut(int x, int y){
        // 先换到根节点上去
        change_root(x);
        // 先保证x和y在一棵树上，再保证y的父亲为x，才能断
        // 还要y的左儿子是0，这样保证y是x的直接后继
        if (findroot(y) == x && tree[y].fa == x && tree[y].ch[0] == 0) {
            tree[y].fa = 0;
            push_up(x);
        }
    }

    // 修改x的值为y
    private void change(int x, int y) {
        splay(x);     // 先伸展到根
        tree[x].v = y;  // 再修改
        push_up(x);   // 再把影响下传
    }


    public Solutions(){
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        int m = sc.nextInt();

        // 初始化
        for(int i = 1; i <= n; i++){
            tree[i] = new Node();
            tree[i].v = sc.nextInt();
            push_up(i);
        }

        while (m-- > 0) {
            int t = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();
            if (t == 0) output(x, y);
            else if (t == 1) link(x, y);
            else if (t == 2) cut(x, y);
            else if (t == 3) change(x, y);
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
