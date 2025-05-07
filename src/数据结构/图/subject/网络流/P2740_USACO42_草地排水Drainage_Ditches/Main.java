package 数据结构.图.subject.网络流.P2740_USACO42_草地排水Drainage_Ditches;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}


class Solutions{
    private static final int MAX_N = 205;
    private static final int MAX_M = 2005;
    private int n, m;
    private int S, T;
    private class Edge{
        int v, c, ne;

        Edge(int v, int c, int ne) {
            this.v = v;     // 从v出发
            this.c = c;     // 初始容量
            this.ne = ne;   // 出边
        }
    }
    private Edge[] edges = new Edge[MAX_M];     // 第i条出边
    private int[] h = new int[MAX_N];
    private int[] mf = new int[MAX_N];    // 存s到v的路径上的流量限制，肯定收到这条路径上的最小容量的那个边限制
    private int[] pre = new int[MAX_N];     // 存v的前驱边，到达v点这个边的编号
    private int idx = 1;   // 从1���始是因为是配对反向边

    private void add(int a, int b, int c) {
        // 编号从2开始存，2，3配对，这样
        // 0是作为边界的，一旦i=0结束
        edges[++idx] = new Edge(b, c, h[a]);
        h[a] = idx;
    }


    // bfs找增广路,最短步数内从源点走到汇点
    private boolean bfs() {
        Arrays.fill(mf, 0);
        Queue<Integer> q = new LinkedList<>();
        q.offer(S);
        mf[S] = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            int v = q.poll();
            for(int i = h[v]; i != 0; i = edges[i].ne) {
                int sta = edges[i].v;
                if (mf[sta] == 0 && edges[i].c > 0) {
                    mf[sta] = Math.min(mf[v], edges[i].c);
                    pre[sta] = i;
                    q.offer(sta);
                }
            }
        }
        return mf[T] != 0;
    }


    private long ek(){
        long flow = 0;
        // 找到增广路了
        while (bfs()) {
            int v = T;
            while (v != S) {
                int i = pre[v];
                edges[i].c -= mf[T];
                edges[i ^ 1].c += mf[T];
                v = edges[i ^ 1].v;
            }
            // 答案增加
            flow += mf[T];
        }
        return flow;
    }


    public Solutions() {
        FastReader sc = new FastReader();
        n = sc.nextInt();    // 挖好的排水沟的数量
        m = sc.nextInt();    // 排水沟交叉点的数量
        for (int i = 1; i <= n; i++) {
            int si = sc.nextInt();
            int ei = sc.nextInt();
            int ci = sc.nextInt();
            add(si, ei, ci);
            add(ei, si, 0);
        }

        S = 1;
        T = m;

        System.out.println(ek());

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

/*
5 4
1 2 40
1 4 20
2 4 20
2 3 30
3 4 10
 */