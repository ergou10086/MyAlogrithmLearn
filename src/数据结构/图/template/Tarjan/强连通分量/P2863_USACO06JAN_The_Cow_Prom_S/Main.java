package 数据结构.图.template.Tarjan.强连通分量.P2863_USACO06JAN_The_Cow_Prom_S;

// 强连通：一张有向图的节点两两互相可达，则称这张图是连通的
// 强连通分量：极大的强连通子图

// 对图深搜时候，每个节点只访问一次，被访问过的节点与边构成搜索树

// 有向边按访问情况分4类
// 树边：访问节点走过的边
// 返祖边：指向祖先节点的边
// 横叉边：右子树指向左子树的边
// 前向边：指向子树中节点的边

// 返祖边与树边必定构成环，横叉边可能与树边构成环

// 如果节点x是某个强连通分量在搜索树中遇到的第一个节点，那么这个强连通分量的其余节点肯定是在搜索树中以x为根的子树中
// 节点x被称为这个强连通分量的根

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}



class Solutions{
    private static final int N = 10010;
    private int n, m, a, b;
    private List<Integer>[] e = new ArrayList[N];
    private int[] dfn = new int[N];   // 时间戳：节点x第一次被访问的顺序
    private int[] low = new int[N];   // 追溯值：从节点x出发，所能访问到的最早时间戳
    private int[] stk = new int[N];   // 栈
    private int[] instk = new int[N];  // 是否在栈中
    private int[] scc = new int[N];   // 每个节点所属的强连通分量的编号。
    private int[] siz = new int[N];   // 每一组强连通的节点的数量
    private int tot, top, cnt;   // tot是时间戳编号器，top是栈的指针

    // 塔杨算法：
    private void tarjan(int x) {
        // 入x时，盖戳，入栈
        dfn[x] = low[x] = ++tot;
        stk[++top] = x;
        instk[x] = 1;

        // 遍历x的所有邻接点
        for (int y : e[x]) {
            // 若y未被访问过，则递归访问
            if (dfn[y] == 0) {
                tarjan(y);
                // 回到x时候，更新x的追溯值
                // x是y的父节点，y能访问的到，x一定也能
                low[x] = Math.min(low[x], low[y]);
            // y已访问且在栈中
            }else if(instk[y] == 1){
                // y是祖先节点或左子树节点，用dfn[y]更新low[x]
                low[x] = Math.min(low[x], dfn[y]);
            }
        }

        // 离开x的时候，记录SCC
        if(dfn[x] == low[x]){
            // 只有遍历完一个SCC，才可以出栈，更新low值的意义：避免SCC节点提前出栈
            int y;
            ++cnt;
            do{
                y = stk[top--];
                instk[y] = 0;
                scc[y] = cnt;  // SCC编号
                ++siz[cnt];   // SCC大小
            }while (y != x);
        }
    }


    public Solutions(){
        FastReader in = new FastReader();
        n = in.nextInt();
        m = in.nextInt();

        for(int i = 1; i <= n; i++) e[i] = new ArrayList<>();

        // 读取图的边
        for (int i = 0; i < m; i++) {
            a = in.nextInt();
            b = in.nextInt();
            e[a].add(b);
        }

        // 执行 Tarjan 算法
        for (int i = 1; i <= n; i++) {
            if (dfn[i] == 0) {
                tarjan(i);
            }
        }

        // 统计强连通分量中大小大于 1 的个数
        int ans = 0;
        for (int i = 1; i <= cnt; i++) {
            if (siz[i] > 1) {
                ans++;
            }
        }

        // 输出答案
        System.out.println(ans);
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