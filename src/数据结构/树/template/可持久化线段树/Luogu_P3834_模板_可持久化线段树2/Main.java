package 数据结构.树.template.可持久化线段树.Luogu_P3834_模板_可持久化线段树2;

// 普通线段树
// 节点区间是序列的下标区间
// 节点维护的信息是区间最值，区间和什么的

// 权值线段树
// 节点区间是序列的值域
// 节点维护的信息是值域内出现次数

// 可持久化线段树：支持回退，访问之前版本的线段树
// 保存每次插入操作时的历史版本

// 每次只更改logn+1个节点，故只增加更改的节点数量的点（动态开点），主席树 = n颗线段树的叠合
// 动态开点：
// 对于每个节点，应该保存左右儿子的编号
// 对于每个历史版本，还应该保存根节点的编号

// 每次插入一个新值时，只修改受影响的节点，而不是重建整棵树。
// 通过 动态开点 的方式，为每个新版本创建一个新的节点，并复用旧版本中未受影响的节点。

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
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

class Solutions{
    private static final int N = 200005;
    private class Node{
        int l, r, s;  // s: 节点值域中有多少个数
        public Node(){
            l = r = s = 0;
        }
    }
    private Node[] tree = new Node[N * 20];   // log2(200005)上取整差不多是19
    private int[] root = new int[N];   // 保存根节点的编号
    private int idx = 0, n, m;    // idx是对新开节点编号的计数器
    private int[] a = new int[N];
    private List<Integer> b = new ArrayList<>();

    // 建树
    // 建空树，递归创建值域[1,n]内的各个节点
    // x是当前节点的编号，通过子空间的x值，给父函数的tree[x].l和tree[x].r赋值
    private void build(int x, int l, int r){
        tree[x] = new Node();   // 插入点的个数依旧是0
        if(l == r) return;   // 也是到叶子返回
        int mid = (l + r) / 2;
        // idx在每次递归共享x的空间时候也同时为节点做编号
        tree[x].l = ++idx; // 分配左子节点
        build(tree[x].l, l, mid);
        tree[x].r = ++idx;  // 分配右子节点
        build(tree[x].r, mid + 1, r);
    }

    // 建主席树
    // x为前一个版本的结点指针，y为当前版本的节点指针,在主席树中插入一个新值 k
    // 创建一个新版本（由 y 表示），并基于旧版本（由 x 表示）进行修改
    private void insert(int x, int y, int l, int r, int k) {
        tree[y] = new Node(); // 初始化新节点
        tree[y].l = tree[x].l; // 复制左子节点
        tree[y].r = tree[x].r; // 复制右子节点
        tree[y].s = tree[x].s + 1; // 更新节点值
        if(l == r) return;
        int m = l + r >> 1;
        // 双指针同步搜索
        // 主席树的每个节点表示一个值域区间 [l, r]。k <= m，说明 k 属于当前区间的左子树还是右子树
        if(k <= m){
            tree[y].l = ++idx;   // 分配新的左子节点
            insert(tree[x].l, tree[y].l, l, m, k);
        } else{
            tree[y].r = ++idx;   // 分配新的右子节点
            insert(tree[x].r, tree[y].r, m + 1, r, k);
        }
    }

    // 查询
    // 在主席树上查询第k小
    // 简化：求[l, r]区间内的第k小，找到插入r时的历史版本，在权值线段树上二分搜索
    // 也就是回到插入r时候的版本，根据左右子树各自有几个数，判断进入哪一边，这样不断二分找到第k小
    // 根据前缀和思路
    // [l,r]信息 = [1,r]的信息-[1,l-1]的信息
    private int query(int x, int y, int l, int r, int k) {
        if(l == r) return l;
        int mid = l + r >> 1;
        // 左右子树存的数字数量之差
        int s = tree[tree[y].l].s - tree[tree[x].l].s;
        // 再和要查询的第k小，比较，得到进入哪一边
        if (k <= s) return query(tree[x].l, tree[y].l, l, mid, k);
        else return query(tree[x].r, tree[y].r, mid + 1, r, k - s);
    }

    public Solutions() throws IOException {
        FastReader sc = new FastReader();
        // 序列的长度 n 和查询的个数 m
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
            b.add(a[i]);
        }

        // 开始离散化。排序，去重，二分找下标
        // 排序并去重
        Collections.sort(b);
        int bn = 1;   // bn 表示去重后的值的个数
        for (int i = 1; i < b.size(); i++) {
            // 当前位置和第i个不相等，将不重复的值依次放到数组的前面。
            if (!b.get(i).equals(b.get(bn - 1))) {
                b.set(bn++, b.get(i));
            }
        }
        // 截取去重后的部分。
        b = b.subList(0, bn);

        // 初始化根节点，建空树
        root[0] = ++idx;
        build(root[0], 1, bn);

        // 插入节点，建主席树
        for (int i = 1; i <= n; i++) {
            // 对于a[i]，二分在b队列中找位置
            int id = Collections.binarySearch(b, a[i]);
            // 如果 a[i] 不存在于 b 中，通过 -id - 1 可以将其还原。
            if (id < 0) id = -id - 1;
            // 对于每个历史版本，还应该保存根节点的编号
            root[i] = ++idx;
            insert(root[i - 1], root[i], 1, bn, id + 1);
        }

        // 处理查询
        while (m-- > 0) {
            int l = sc.nextInt();
            int r = sc.nextInt();
            int k = sc.nextInt();
            int id = query(root[l - 1], root[r], 1, bn, k) - 1;
            System.out.println(b.get(id));
        }
    }

    class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        String next() throws IOException {
            while (!st.hasMoreElements()) {
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}