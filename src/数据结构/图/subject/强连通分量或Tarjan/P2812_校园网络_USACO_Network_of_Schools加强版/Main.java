package 数据结构.图.subject.强连通分量或Tarjan.P2812_校园网络_USACO_Network_of_Schools加强版;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solution();
    }
}

// 一个强连通分量可以坍缩成一个点
// Tarjan缩点，把有环图变成无环图，观察新图中点的入度和出度情况，构造答案
// 第一问问的是缩点后入度为0的情况
// 第二问答案是max(入度为0的点数，出度为0的点数)
// 加边后构成一个环，所有点的入度不为0且出度不为0，缩点后分别统计入度为0，出度为0的点的个数，取max

class Solution {
    private static final int N = 10010;
    private int n, tot, cnt, top;
    private int[] dfn = new int[N];
    private int[] low = new int[N];
    private int[] stk = new int[N];
    private int[] instk = new int[N];
    private int[] scc = new int[N];
    private int[] din = new int[N];   // scc的入度
    private int[] dout = new int[N];  // scc的出度
    private ArrayList<Integer>[] e = new ArrayList[N];

    private void tarjan(int x) {
        dfn[x] = low[x] = ++tot;
        stk[++top] = x;
        instk[x] = 1;

        for (int y : e[x]) {
            if (dfn[y] == 0) {
                tarjan(y);
                low[x] = Math.min(low[x], low[y]);
            }else if(instk[y] == 1) {
                low[x] = Math.min(low[x], dfn[y]);
            }
        }

        if (dfn[x] == low[x]) {
            int y;
            cnt++;
            do {
                y = stk[top--];
                instk[y] = 0;
                scc[y] = cnt;
            }while(y != x);
        }
    }



    public Solution() {
        FastReader sc = new FastReader();
        n = sc.nextInt();

        // 初始化邻接表
        for (int i = 1; i <= n; i++) {
            e[i] = new ArrayList<>();
            while (true) {
                int a = sc.nextInt();
                if (a == 0) break;
                e[i].add(a);
            }
        }

        // 对每个未访问的节点进行Tarjan算法
        for (int i = 1; i <= n; i++) {
            if (dfn[i] == 0) {
                tarjan(i);
            }
        }

        // 计算SCC的入度和出度
        for (int x = 1; x <= n; x++) {
            for (int y : e[x]) {
                if (scc[x] != scc[y]) {
                    din[scc[y]]++;
                    dout[scc[x]]++;
                }
            }
        }

        int a = 0, b = 0;
        for (int i = 1; i <= cnt; i++) {
            if (din[i] == 0) a++;
            if (dout[i] == 0) b++;
        }

        System.out.println(a);
        if (cnt == 1) {
            System.out.println(0);
        } else {
            System.out.println(Math.max(a, b));
        }
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
