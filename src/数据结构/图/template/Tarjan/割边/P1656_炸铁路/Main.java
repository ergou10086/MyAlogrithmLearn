package 数据结构.图.template.Tarjan.割边.P1656_炸铁路;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

// 对图深搜时，每个节点只访问一次，被访问过的节点与边构成搜索树
// 割边：对于一个无向图，如果删除一条边后图中的连通块的个数增加了，称这个边为桥或者割边
// 割边判定
// 当搜索树上存在x的一个节点y，满足low[y] > dfn[x]，则(x,y)这条边就是割边
// low[y] > dfn[x]，说明从y出发，在不经过(x,y)这条边的前提下，不管走哪条边，都无法到达x或更早的节点
// 删除(x,y)这条边，以y为根的子树也就断开了，环外的边能割得断
// 反之，low[y] <= dfn[x],说明y能绕行到达比x更早访问的节点，
// （x,y）不是割边了，环内的边割不断

// 割点判定：low[y] >= dfn[x]
// 因为允许走(x,y)的反边更新low值
// 割边判定：low[y] > dfn[x]
// 因为不允许走(x,y)的反边更新low值

class Solutions{
    private static final int N = 210;
    private static final int M = 10010;
    private int n, m, a, b, cnt, tot;
    // 由于要对边记录编号，选择链式前向星或者链式邻接表
    private class Edge{
        int u, v;   // 起点，终点
        public Edge(int u, int v){
            this.u = u;
            this.v = v;
        }
    }
    private ArrayList<Edge> edges = new ArrayList<Edge>();
    ArrayList<Integer>[] adj = new ArrayList[N];    // 出边的编号
    private int[] h = new int[N];

    // 塔杨用数组
    private int[] dfn = new int[N], low = new int[N];

    // 割边结构体
    private class Bridge implements Comparable<Bridge>{
        int x, y;   // 割边连接的两个端点
        public Bridge(int x, int y){
            this.x = x;
            this.y = y;
        }
        @Override
        public int compareTo(Bridge o) {
            if(this.x == o.x) return this.y - o.y;
            return this.x - o.x;
        }
    }
    private ArrayList<Bridge> bri = new ArrayList<>();


    private void add(int a, int b) {
        if (adj[a] == null) {
            adj[a] = new ArrayList<>();
        }
        edges.add(new Edge(a, b));
        adj[a].add(edges.size() - 1);
    }

    // in_edg为入边的编号
    private void tarjan(int x, int in_edg) {
        dfn[x] = low[x] = ++tot;

        for (int i = 0; i < adj[x].size(); i++) {
            int j = adj[x].get(i);   //  以x为起点的边
            int y = edges.get(j).v;   // 获取终点
            // 若y尚未访问
            if (dfn[y] == 0) {
                tarjan(y, j);    // y的入边为j
                low[x] = Math.min(low[x], low[y]);
                // 判断割边
                if(low[y] > dfn[x]){
                    bri.add(new Bridge(x, y));
                    ++cnt;
                }
            // 只有不是反边的时候，才更新
            }else if(j != (in_edg ^ 1)){
                low[x] = Math.min(low[x], dfn[y]);
            }
        }
    }


    public Solutions(){
        // 初始化
        FastReader fs = new FastReader();

        n = fs.nextInt();
        m = fs.nextInt();

        for(int i = 0; i < m; i++){
            a = fs.nextInt();
            b = fs.nextInt();
            add(a, b);
            add(b, a);
        }

        for(int i = 1; i <= n; i++){
            if(dfn[i] == 0){
                tarjan(i, 0);
            }
        }

        Collections.sort(bri);

        for (Bridge bridge : bri) {
            System.out.println(bridge.x + " " + bridge.y);
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