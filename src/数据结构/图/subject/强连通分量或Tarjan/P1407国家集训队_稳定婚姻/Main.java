package 数据结构.图.subject.强连通分量或Tarjan.P1407国家集训队_稳定婚姻;

// 若在 Bi 和 Gi 离婚的前提下，这 2n 个人最终依然能够结合成 n 对情侣，那么我们称婚姻 i 为不安全的，否则婚姻 i 就是安全的。
// 建图
// 那么我们将所有 现在或曾经交往过的 男孩和女孩连接起来，可以发现出现了一些环
// 那么这些环就是不安全的婚姻，判断哪些夫妻处在环中即可

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions{
    private static final int N = 10005;
    private static final int M = 300005;
    private int n, m;
    private Map<String, Integer> coup = new HashMap<>();
    // 有向图节点
    private class Node {
        int v, nex;
    }
    private Node[] e = new Node[M];
    private int tot;
    private int[] head = new int[N];

    private boolean[] instk = new boolean[N];
    private int[] dfn = new int[N];
    private int[] low = new int[N];
    private int[] stk = new int[N];
    private int[] scc = new int[N];
    private int[] siz = new int[N];
    private int idx, top, cnt;

    private void addE(int u, int v) {
        e[++tot] = new Node();
        e[tot].v = v;
        e[tot].nex = head[u];
        head[u] = tot;
    }


    private void Tarjan(int u) {
        dfn[u] = low[u] = ++idx;
        stk[++top] = u;
        instk[u] = true;

        for(int i = head[u]; i != 0; i = e[i].nex) {
            int v = e[i].v;
            if(dfn[v] == 0) {
                Tarjan(v);
                low[u] = Math.min(low[u], low[v]);
            }else if(instk[v]){
                low[u] = Math.min(low[u], dfn[v]);
            }
        }

        if(dfn[u] == low[u]) {
            ++cnt;
            while(true) {
                int node = stk[top--];
                instk[node] = false;
                scc[node] = cnt;
                if(node == u) break;
            }
        }
    }


    public Solutions(){
        FastReader fr = new FastReader();

        n = fr.nextInt();
        String gir, boy;
        // 夫妻之间，girl to boy
        for (int i = 1; i <= n; i++) {
            gir = fr.next();
            boy = fr.next();
            coup.put(gir, i);
            coup.put(boy, i + n);
            addE(i, i + n);
        }

        m = fr.nextInt();
        // 情人之间，boy to girl
        for (int i = 1; i <= m; i++) {
            gir = fr.next();
            boy = fr.next();
            addE(coup.get(boy), coup.get(gir));
        }

        for (int i = 1; i <= n * 2; ++i) {
            if (dfn[i] == 0) {
                Tarjan(i);
            }
        }

        for (int i = 1; i <= n; ++i) {
            if (scc[i] == scc[i + n]) {
                System.out.println("Unsafe");
            }else{
                System.out.println("Safe");
            }
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