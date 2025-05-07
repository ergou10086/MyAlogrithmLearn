package 数据结构.树.template.动态树.P3690_模板_动态树LCT;

import java.util.Scanner;

public class Main2 {
    static final int N = 100010;
    static Node[] tr = new Node[N]; // Splay树的节点数组

    static class Node {
        int[] ch = new int[2]; // 左右孩子
        int fa; // 父节点
        int v;  // 节点值
        int sum; // 子树异或和
        int tag; // 翻转懒标记

        Node() {
            ch[0] = ch[1] = fa = v = sum = tag = 0;
        }
    }

    // 工具函数
    static int fa(int x) { return tr[x].fa; }
    static int lc(int x) { return tr[x].ch[0]; }
    static int rc(int x) { return tr[x].ch[1]; }
    static boolean notroot(int x) { return lc(fa(x)) == x || rc(fa(x)) == x; }

    // 上传更新
    static void pushup(int x) {
        tr[x].sum = tr[lc(x)].sum ^ tr[x].v ^ tr[rc(x)].sum;
    }

    // 下传翻转标记
    static void pushdown(int x) {
        if (tr[x].tag != 0) {
            int temp = lc(x);
            tr[x].ch[0] = rc(x);
            tr[x].ch[1] = temp;
            tr[lc(x)].tag ^= 1;
            tr[rc(x)].tag ^= 1;
            tr[x].tag = 0;
        }
    }

    // 递归下传
    static void pushall(int x) {
        if (notroot(x)) pushall(fa(x));
        pushdown(x);
    }

    // 旋转操作
    static void rotate(int x) {
        int y = fa(x), z = fa(y);
        int k = rc(y) == x ? 1 : 0; // x是y的右儿子
        if (notroot(y)) tr[z].ch[rc(z) == y ? 1 : 0] = x;
        tr[x].fa = z;
        tr[y].ch[k] = tr[x].ch[k ^ 1];
        tr[tr[x].ch[k ^ 1]].fa = y;
        tr[x].ch[k ^ 1] = y;
        tr[y].fa = x;
        pushup(y);
        pushup(x);
    }

    // 将x伸展到根
    static void splay(int x) {
        pushall(x);
        while (notroot(x)) {
            int y = fa(x), z = fa(y);
            if (notroot(y)) {
                if ((rc(y) == x) ^ (rc(z) == y)) rotate(x);
                else rotate(y);
            }
            rotate(x);
        }
    }

    // 打通x到根的路径
    static void access(int x) {
        for (int y = 0; x != 0; y = x, x = fa(x)) {
            splay(x);
            tr[x].ch[1] = y;
            pushup(x);
        }
    }

    // 将x设为根
    static void makeroot(int x) {
        access(x);
        splay(x);
        tr[x].tag ^= 1;
    }

    // 分离x到y的路径
    static void split(int x, int y) {
        makeroot(x);
        access(y);
        splay(y);
    }

    // 输出x到y路径的异或和
    static void output(int x, int y) {
        split(x, y);
        System.out.println(tr[y].sum);
    }

    // 查找x的根
    static int findroot(int x) {
        access(x);
        splay(x);
        while (lc(x) != 0) {
            pushdown(x);
            x = lc(x);
        }
        splay(x); // 防止卡链
        return x;
    }

    // 连接x和y
    static void link(int x, int y) {
        makeroot(x);
        if (findroot(y) != x) tr[x].fa = y;
    }

    // 断开x和y
    static void cut(int x, int y) {
        makeroot(x);
        if (findroot(y) == x && fa(y) == x && lc(y) == 0) {
            tr[y].fa = 0;
            pushup(x);
        }
    }

    // 修改x的值为y
    static void change(int x, int y) {
        splay(x);
        tr[x].v = y;
        pushup(x);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        // 初始化节点
        for (int i = 1; i <= n; i++) {
            tr[i] = new Node();
            tr[i].v = sc.nextInt();
            pushup(i);
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
        sc.close();
    }
}
