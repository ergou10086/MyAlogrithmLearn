package 数据结构.树.template.线段树.线段树_懒标记.LuoguP3372_模板_线段树1;

// 结构体版本

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 线段树是基于分治思想的二叉树，用于维护区间信息(区间和，区间最值，区间GCD等)
// 能以logn的时间执行区间修改和区间查询

public class Main {
    public static void main(String[] args) {
        new Solution();
    }
}

class Solution{
    private static final int N = 100005;
    private long[] w;  // 数组w，用来存储输入的值
    // 线段树中每个叶子节点存储元素本身，非叶子节点存储区间内元素统计值（因为区间长度为1）
    // 线段树结构体
    private static class TreeNode {  // 改为static class
        // 左右节点和代表的区间和还有增加值
        int l, r;
        long sum, add;
    }
    private TreeNode[] tree;   // 需要开4n空间


    // push_up 向上更新,进行区间和的修改
    private void push_up(int p) {
        tree[p].sum = tree[2 * p].sum + tree[2 * p + 1].sum;
    }


    // 向下更新，传递懒标记
    private void push_down(int p) {
        if (tree[p].add != 0) {
            // 需要给左子节点加上的是左子节点的宽度
            long leftLength = tree[2 * p].r - tree[2 * p].l + 1;
            // 需要给右子节点加上的是右子节点的宽度
            long rightLength = tree[2 * p + 1].r - tree[2 * p + 1].l + 1;

            // 给子节点加上懒标记的值
            tree[2 * p].sum += tree[p].add * leftLength;
            tree[2 * p + 1].sum += tree[p].add * rightLength;

            // 下传懒标记
            tree[2 * p].add += tree[p].add;
            tree[2 * p + 1].add += tree[p].add;

            // 清除当前节点的懒标记
            tree[p].add = 0;
        }
    }



    // 建树,深搜递归建树
    // p为根节点编号，l,r为左右端点
    private void build(int p, int l, int r) {
        // 存树
        tree[p].l = l;
        tree[p].r = r;
        tree[p].add = 0;
        // 叶子节点，存值
        if (l == r) {
            tree[p].sum = w[l];
            return;
        }
        // 不是叶子则裂开，左右接着递归建树
        int mid = (l + r) / 2;
        build(2 * p, l, mid);
        build(2 * p + 1, mid + 1, r);
        // 别忘了上传
        push_up(p);
    }


    // 点修改
    // 从根节点进入，递归往下找到叶子节点[x,x]
    // 把该节点的值增加k，然后从下往上更新其祖先节点上的统计值
    // p为起始节点，x为目标节点，k为增加值
    private void change(int p, long x, long k) {
        // 从根节点进入，递归往下找到叶子节点[x,x]
        if(tree[p].l == x && tree[p].r == x){
            tree[p].sum += k;
            return;
        }
        // 往回走，去更新上面没改的
        long m =(tree[p].l + tree[p].r) / 2;   // 不是叶子节点就裂开
        if(x <= m) change(2 * p, x, k);
        if(x > m) change(2 * p + 1, x, k);
        // 别忘了上传
        push_up(p);
    }



    // 区间查询
    // 拆分与拼凑的思想，从根节点进入，递归
    // 若查询区间[x,y]完全覆盖当前节点区间，则立即回溯，返回该节点的sum值
    // 若左子节点与[x,y]有重叠，则递归访问左子树
    // 若右子节点与[x,y]有重叠，则递归访问右子树
    private long query(int p, int x, int y) {
        // 完全覆盖，直接返回
        if (x <= tree[p].l && tree[p].r <= y) {
            return tree[p].sum;
        }
        push_down(p);  // 先下传懒标记
        // 不是叶子节点，裂开递归左右子树
        int mid = (tree[p].l + tree[p].r) / 2;
        long sum = 0;
        // 左子树有重叠
        if (x <= mid) sum += query(2 * p, x, y);
        // 右子树有重叠
        if (y > mid) sum += query(2 * p + 1, x, y);
        return sum;
    }


    // 区间修改
    // 当[x,y]完全覆盖节点区间[a,b]时候，先修改该区间的sum值
    // 然后打上一个懒标记（先加上，但是对下面该修改的叶子节点不修改，先欠着），然后立刻返回
    // 等下次需要时候，下传懒标记
    // p为根节点，x，y是区间左右端，k为修改值
    private void update(int p, int x, int y, long k) {
        // 完全覆盖，进行区间改变
        if (x <= tree[p].l && tree[p].r <= y) {
            // 先把sum加上，但是下面还没加，需要懒标记添加先欠着
            tree[p].sum += (tree[p].r - tree[p].l + 1) * k;
            // 懒标记标记，代表下面的每个叶子节点需要加k
            tree[p].add += k;
            return;
        }
        // 如果完全不覆盖，接着裂开
        push_down(p);  // 先下传懒标记
        // 不是叶子节点，裂开递归左右子树
        int mid = (tree[p].l + tree[p].r) / 2;
        // 左子树和区间有重叠，但并没有完全覆盖，接着分
        if (x <= mid) update(2 * p, x, y, k);
        // 右子树有重叠，但并没有完全覆盖，接着分
        if (y > mid) update(2 * p + 1, x, y, k);
        push_up(p);  // 更新当前节点的sum
    }


    public Solution(){
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        int m = sc.nextInt();
        w = new long[n + 1];
        tree = new TreeNode[4 * (n + 1)];
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new TreeNode();
        }

        for(int i = 1; i <= n; i++){
            w[i] = sc.nextLong();
        }

        // 建树
        build(1, 1, n);

        // 处理操作
        while (m-- > 0) {
            int op = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();
            if (op == 1) {
                long k = sc.nextLong();
                update(1, x, y, k);
            } else {
                System.out.println(query(1, x, y));
            }
        }
    }


    private class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        String next() {
            while(!st.hasMoreTokens()) {
                try{
                    st = new StringTokenizer(br.readLine());
                }catch (IOException e) {
                    e.printStackTrace();
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


/**
 5 5
 1 5 4 2 3
 2 2 4
 1 2 3 2
 2 3 4
 1 1 5 1
 2 1 4
 */
