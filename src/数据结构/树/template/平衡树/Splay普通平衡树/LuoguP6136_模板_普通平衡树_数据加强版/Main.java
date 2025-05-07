package 数据结构.树.template.平衡树.Splay普通平衡树.LuoguP6136_模板_普通平衡树_数据加强版;

import com.sun.source.tree.DeconstructionPatternTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.cert.CertificateNotYetValidException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions{
    private class Node{
        int[] ch = new int[2];  // 左右儿子
        int fa;
        int val;
        int cnt;
        int siz;

        private void init(int p1, int v1){
            fa = p1;
            val = v1;
            cnt = 1;
            siz = 1;
        }
    }
    private final int INF = (1 << 30) + 1, N = 1100010;
    private final Node[] tree = new Node[N];
    private int root, tot;  // 表示根节点和当前节点的总数

    {
        for (int i = 0; i < N; i++) {
            tree[i] = new Node();
        }
    }

    // 上传
    private void push_up(int x){
        tree[x].siz = tree[tree[x].ch[0]].siz + tree[tree[x].ch[1]].siz + tree[x].cnt;
    }

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


    private void insert(int v){
        int x = root, p = 0;
        while(x != 0 && tree[x].val != v){
            p = x;
            // v大于x，右子树，小于就是左子树
            x = tree[x].ch[v > tree[x].val ? 1 : 0];
        }
        if(x != 0){
            tree[x].cnt++;
        }else{
            x = ++tot;
            if(p != 0){
                // judgement v on left or right
                tree[p].ch[v > tree[p].val ? 1 : 0] = x;
            }
            tree[x].init(p, v);
        }
        splay(x, 0);
    }

    private void find(int v) {
        int x = root;
        while (tree[x].ch[v > tree[x].val ? 1 : 0] != 0 && v != tree[x].val) {
            x = tree[x].ch[v > tree[x].val ? 1 : 0];
        }
        splay(x, 0);
    }

    private int get_pre(int v){
        find(v);
        int x = root;
        if(tree[x].val < v){
            return x;
        }
        x = tree[x].ch[0];
        while(tree[x].ch[1] != 0){
            x = tree[x].ch[1];
        }
        splay(x, 0);
        return x;
    }

    private int get_suc(int v){
        find(v);
        int x = root;
        if(tree[x].val > v){
            return x;
        }
        x = tree[x].ch[1];
        while(tree[x].ch[0] != 0){
            x = tree[x].ch[0];
        }
        splay(x, 0);
        return x;
    }

    private void del(int v){
        int pre = get_pre(v);
        int suc = get_suc(v);
        splay(pre, 0);
        splay(suc, pre);
        int del = tree[suc].ch[0];
        if(tree[del].cnt > 1){
            tree[del].cnt--;
            splay(del, 0);
        }else{
            tree[suc].ch[0] = 0;
            splay(suc, 0);
        }
    }

    private int get_rank(int v){
        // 求排名，先插入再删除
        insert(v);
        int res = tree[tree[root].ch[0]].siz;
        del(v);
        return res;
    }

    private int get_val(int k){
        int x = root;
        while(true){
            if(k <= tree[tree[x].ch[0]].siz){
                x = tree[x].ch[0];
            }else if(k <= tree[tree[x].ch[0]].siz + tree[x].cnt){
                break;
            }else{
                k -= tree[tree[x].ch[0]].siz + tree[x].cnt;
                x = tree[x].ch[1];
            }
        }
        splay(x, 0);
        return tree[x].val;
    }


    public Solutions(){
        FastReader sc = new FastReader();
        insert(-INF);
        insert(INF);

        int n = sc.nextInt();
        int m = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            int x = sc.nextInt();
            insert(x);
        }

        int res = 0, last = 0;
        while(m-- > 0){
            int op = sc.nextInt();
            int x = sc.nextInt();
            x ^= last;  // 每次操作的 x′ 都要异或上 last 才是真实的 x。

            if (op == 1) insert(x);
            if (op == 2) del(x);
            if (op == 3) res ^= (last = get_rank(x));
            if (op == 4) res ^= (last = get_val(x + 1));
            if (op == 5) res ^= (last = tree[get_pre(x)].val);
            if (op == 6) res ^= (last = tree[get_suc(x)].val);
        }

        System.out.println(res);
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
