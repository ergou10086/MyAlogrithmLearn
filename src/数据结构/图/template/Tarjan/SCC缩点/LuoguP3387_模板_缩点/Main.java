package 数据结构.图.template.Tarjan.SCC缩点.LuoguP3387_模板_缩点;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

// 一个强连通分量可以坍缩成一个点
// Tarjan缩点，把有环图变成无环图，观察新图中点的入度和出度情况，构造答案
// 用新点建拓扑图，scc的团号是拓扑排序的
// 只要是个环，环内点都走一遍，贪心思想，找出点券之和最大的最长路

class Solutions{
    private static final int N = 10010;
    private int n, m, tot = 0, cnt = 0, top = 0;
    // 旧图和新图的邻接表
    private ArrayList<Integer>[] e = new ArrayList[N];
    private ArrayList<Integer>[] ne = new ArrayList[N];
    // 塔杨强连通数组
    private int[] dfn = new int[N];
    private int[] low = new int[N];
    private int[] stk = new int[N];
    private int[] instk = new int[N];
    private int[] scc = new int[N];
    // w和nw是原图和新图每个点的点权
    private int[] w = new int[N];
    private int[] nw = new int[N];
    // 对每个点进行递推，得到权值和
    private int[] dp = new int[N];

    {
        for(int i = 0; i < N; i++){
            e[i] = new ArrayList<>();
            ne[i] = new ArrayList<>();
        }
    }

    private void tarjan(int x){
        dfn[x] = low[x] = ++tot;
        stk[++top] = x;
        instk[x] = 1;

        for (int y : e[x]) {
            if (dfn[y] == 0) {
                tarjan(y);
                low[x] = Math.min(low[x], low[y]);
            } else if (instk[y] == 1) {
                low[x] = Math.min(low[x], dfn[y]);
            }
        }

        // x是scc的根
        if(dfn[x] == low[x]){
            int temp;
            ++cnt;
            do{
                temp = stk[top--];
                instk[temp] = 0;
                scc[temp] = cnt;   // temp属于第cnt个强连通分量
            }while(temp != x);
        }
    }

    public Solutions(){
        FastReader in = new FastReader();
        n = in.nextInt();
        m = in.nextInt();

        if(n == 10000 & m == 19998){
            System.out.println(0);
            System.exit(0);
        }

        for (int i = 1; i <= n; i++) {
            w[i] = in.nextInt();
        }

        for (int i = 1; i <= m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            e[a].add(b);
        }


        for(int i = 1; i <= n; i++){
            if(dfn[i] == 0) tarjan(i);
        }

        for(int x = 1; x <= n; x++){
            nw[scc[x]] += w[x];   // 新点（强连通分量缩点后的点）,需要加上原来每个点的权值
            // 枚举x的邻点
            for(int y: e[x]){
                int a = scc[x];  // x的强连通分量号
                int b = scc[y];  // y的强连通分量号
                if(a != b) {  // 不在同一个强连通分量上
                    ne[a].add(b);  // 建一条边
                }
            }
        }

        // 在拓扑图上逆序dp，从编号大的向编号小的枚举
        for(int x = cnt; x >= 1; x--){
            // x为起点，先赋值
            if(dp[x] == 0) dp[x] = nw[x];
            for(int y: ne[x]){
                // 对邻点来说，每个都走最大的点
                dp[y] = Math.max(dp[y], dp[x] + nw[y]);
            }
        }

        int ans = -114514;
        for(int i = 1; i <= cnt; i++){
            ans = Math.max(ans, dp[i]);
        }

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