package 数据结构.图.template.生成树.最小生成树.Kruskal算法.P3366_模板_最小生成树;

import java.io.*;
import java.util.*;

// Kruskal算法利用并查集求最小生成树
// 定义无向连通图的 最小生成树（Minimum Spanning Tree，MST）为边权和最小的生成树
// 该算法的基本思想是从小到大加入边，是个贪心算法。
// 维护一堆 集合，查询两个元素是否属于同一集合，合并两个集合。
// 所以说kruskal算法需要并查集
// 为了造出一棵最小生成树，我们从最小边权的边开始，按边权从小到大依次加入，如果某次加边产生了环，就扔掉这条边，直到加入了 [n-1] 条边，即形成了一棵树。、
// 复杂度O(mlogm)

class FastReader {
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


class Solutions {
    private final int N = 200006;
    private class Edge{
        private int u, v, w;
        private Edge(int u, int v, int w){
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
    // 存第i条边的起点，终点与边权
    private Edge[] edges = new Edge[N];
    // fa存x点的父节点
    private int[] fa = new int[N];
    private int n, m, ans, cnt;

    private int find(int x){
        if(fa[x] == x) return x;
        return fa[x] = find(fa[x]);
    }

    // 算法思路
    // 初始化并查集，把n个点放在n个集合
    // 将所有的边按边权从小到大排序（贪心）
    // 按顺序连接每一条边，如果这条边连接的两个点不在同一集合，就把这条边加入最小生成树，并且合并这两个集合
    // 如果这条边连接的两个点在同一集合，跳过
    // 重复执行上述，直到选取了n-1条边
    private boolean Kruskal(){
        // edges结构体数组按边权排序
        Arrays.sort(edges, 0, m, Comparator.comparingInt(o -> o.w));
        for(int i = 1; i <= n; i++){
            fa[i] = i;
        }
        // 按顺序连接每一条边,并求这两个点是否在同一集合
        for(int i = 0; i < m; i++){
            int x = find(edges[i].u);
            int y = find(edges[i].v);
            if(x != y){
                fa[x] = y;
                ans += edges[i].w;
                cnt++;
            }
        }
        return cnt == n-1;
    }

    public Solutions(){
        FastReader fr = new FastReader();
        n = fr.nextInt();
        m = fr.nextInt();
        for(int i = 0; i < m; i++){
            int u = fr.nextInt();
            int v = fr.nextInt();
            int w = fr.nextInt();
            edges[i] = new Edge(u, v, w);
        }
        if(!Kruskal()) System.out.println("orz");
        else System.out.println(ans);
    }
}

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}
