package 数据结构.图.template.Tarjan.割点.P3388_模板_割点_割顶;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions().solve();
    }
}

// 对图深搜时，每个节点只访问一次，被访问过的节点与边构成搜索树
// 时间戳dfn[x]:节点x第一次被访问的顺序
// low[x]:节点x出发，能访问到的最早的节点的时间戳

// 割点：对于一个无向图，如果把一个点删除后，连通块的个数增加了，这个点就是割点
// 割点判定法则：
// 如果x不是根节点，当搜索树上存在x的一个子节点y，满足low[y] >= dfn[x]，那么x就是割点
// 如果x是根节点，当搜索树上存在x至少两个子节点y1，y2，满足low[y1 || y2] > dfn[x]，那么x就是割点

// low[y] >= dfn[x]，说明从y出发，在不通过x点的前提下，不管走哪条边，都无法到达比x更早访问的节点
// 删除x点后，以y为根的子树断开了，环顶的点割得掉
// 反之，low[y] < dfn[x],说明y能绕行到达比x更早访问的节点，x就不是割点了下
// 环内的点割不掉

class Solutions {
    private static final int N = 20010;
    private int n, m, tot, root;
    private int[] dfn = new int[N];
    private int[] low = new int[N];
    private boolean[] cut = new boolean[N];
    private ArrayList<Integer>[] e;

    @SuppressWarnings("unchecked")
    public Solutions() {
        e = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            e[i] = new ArrayList<>();
        }
    }

    // 构建图，添加边
    private void addEdge(int a, int b) {
        e[a].add(b);
        e[b].add(a);
    }


    private void tarjan(int x){
        // 入x盖戳
        dfn[x] = low[x] = ++tot;
        int child = 0;  // 子树个数

        for(int y : e[x]){
            if(dfn[y] == 0){
                tarjan(y);
                // 回到x时候，更新low，判断割点
                low[x] = Math.min(low[x], low[y]);
                // 子节点访问到的最早的节点的时间戳 大于 父节点的时间戳
                if(low[y] >= dfn[x]){
                    // 记录子树个数，对于根节点来说子树个数必须大于1
                    child++;
                    // 判断二个情况
                    if(x != root || child > 1){
                        cut[x] = true;
                    }
                }
            }else{  // y被访问
                // 更新lowx,只能用lowx和dfny
                low[x] = Math.min(low[x], dfn[y]);
            }
        }
    }


    public void solve() {
        FastReader reader = new FastReader();
        n = reader.nextInt();
        m = reader.nextInt();
        for (int i = 1; i <= m; i++) {
            int a = reader.nextInt(), b = reader.nextInt();
            addEdge(a, b);
        }

        for (root = 1; root <= n; root++) {
            if (dfn[root] == 0) {
                tarjan(root);
            }
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (cut[i]) {
                ans++;
            }
        }

        System.out.println(ans);

        for (int i = 1; i <= n; i++) {
            if (cut[i]) {
                System.out.print(i + " ");
            }
        }
    }

    private class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next(){
            while(st == null || !st.hasMoreElements()){
                try {
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
}