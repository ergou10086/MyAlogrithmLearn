package 数据结构.图.template.网络流.最大流EK.P3376_模板_网络最大流;

// 网络是指一个有向图G=(V,E),有两个特殊节点，源点S和汇点T
// 每条有向边(x,y)∈E都有一个权值c(x,y),称为边的容量，若(x,y)不属于E，c(x,y) = 0
// 用f(x,y)表示边（x，y）上的流量，c(x,y) - f(x,y)称为边的剩余容量，通常用f(x,y)/c(x,y)形式标记边上的流量与容量
// 可行流应该满足：
// 容量限制：f(x,y) <= c(x,y)
// 流量守恒：流向x点的所有流量之和，所有从x点流出的流量之和，不包含源点和汇点

// 最大流：从源点流向汇点的最大流量
// 增广路：一条从源点到汇点的所有边的剩余容量>=0的路径
// 残留网：由网络中所有结点和剩余容量大于0的边构成的子图，这里的边包括有向边和反向边

// 建图时候每条有向边（x，y）都构建一条反向边（y，x），初始容量c(x,y)=0，构建反向边的目的是构建一个退流管道
// 一旦前面的增广路堵死可行流，可以通过退流管道退流，后悔机制

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
    private static final int MAX_N = 10010;
    private static final int MAX_M = 200010;
    private int n, m, S, T;
    private Edge[] edges = new Edge[MAX_M];     // 第i条出边
    private int[] h = new int[MAX_N];
    private long[] mf = new long[MAX_N];    // 存s到v的路径上的流量限制，肯定收到这条路径上的最小容量的那个边限制
    private int[] pre = new int[MAX_N];     // 存v的前驱边，到达v点这个边的编号
    private int idx = 1;   // 从1开始是因为是配对反向边

    private class Edge{
        long v, c, ne;

        Edge(long v, long c, long ne) {
            this.v = v;     // 从v出发
            this.c = c;     // 初始容量
            this.ne = ne;   // 出边
        }
    }

    private void add(int a, int b, int c) {
        // 编号从2开始存，2，3配对，这样
        // 0是作为边界的，一旦i=0结束
        edges[++idx] = new Edge(b, c, h[a]);
        h[a] = idx;
    }


    // bfs找增广路,最短步数内从源点走到汇点
    private boolean bfs() {
        // 初始化每个点流量上限初始化为0
        Arrays.fill(mf, 0);
        //源点入队
        Queue<Integer> q = new LinkedList<>();
        q.offer(S);
        // 初始化源点流量上限为无限大
        mf[S] = Long.MAX_VALUE;

        // 正常的bfs宽搜
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int i = h[u]; i != 0; i = (int) edges[i].ne) {
                long v = edges[i].v;
                // 更新流量上限
                if (mf[(int) v] == 0 && edges[i].c > 0) {
                    mf[(int) v] = Math.min(mf[u], edges[i].c);
                    pre[(int) v] = i;    // 存前驱边
                    q.offer((int) v);
                    if (v == T) return true;    // 到达汇点返回，已经找到了一条增广路
                }
            }
        }
        return false;
    }


    // 累加可行流
    private long EK(){
        long flow = 0;
        // 找到增广路了
        while (bfs()) {
            // 从汇点开始
            int v = T;
            // 更新残留网，只要v还没有到达源点
            while (v != S) {
                // 取v点前驱边
                int i = pre[v];
                // 正向边减去流量上限
                edges[i].c -= mf[T];
                // 反向边加上流量上限
                edges[i ^ 1].c += mf[T];
                // 接着按反向边往回走
                v = (int) edges[i ^ 1].v;
            }
            // 答案增加
            flow += mf[T];
        }
        return flow;
    }


    public Solutions(){
        FastReader scanner = new FastReader();
        n = scanner.nextInt();
        m = scanner.nextInt();
        S = scanner.nextInt();
        T = scanner.nextInt();

        while (m-- > 0) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            add(a, b, c);
            add(b, a, 0); // reverse edge with 0 capacity
        }

        System.out.println(EK());
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