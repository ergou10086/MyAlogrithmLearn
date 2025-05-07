package 数据结构.图.subject.生成树.P1340_兽径管理;

import java.beans.Beans;
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}



class Solutions{
    private int n, w;
    private class Edge{
        // u, v: 两个端点，w: 边的权重，p: 该边的参与生成树的周数
        private int u, v, w, p;
        private Edge(int u, int v, int w, int p){
            this.u = u;
            this.v = v;
            this.w = w;
            this.p = p;  // 该边是第几轮构造生成树用到的边
        }
    }
    private Edge[] edges = new Edge[6006];
    private int[] fa = new int[209];
    private int[] res = new int[6006];


    private int find(int x){
        if(fa[x] == x) return x;
        return fa[x] = find(fa[x]);
    }

    private void merge(int x, int y) {
        fa[find(x)] = find(y);
    }

    private int Kruskal(int k){
        int cnt = 0, ans = 0;
        // 每次kruskal时对于并查集用到的fa[]数组都要先初始化一次
        for(int i = 0; i <= n; i++){
            fa[i] = i;
        }

        for(int i = 1; i <= w; i++){
            if(cnt == n - 1) break;  // 已经生成了n-1条边，就不用再生成了
            if (find(edges[i].u) != find(edges[i].v) && edges[i].p <= k) {
                merge(edges[i].u, edges[i].v);
                cnt++;
                ans += edges[i].w;
            }
        }
        if (cnt < n - 1) return -1;
        return ans;
    }


    public Solutions(){
        FastReader sc = new FastReader(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = sc.nextInt();
        w = sc.nextInt();

        for(int i = 1; i <= n; i++){
            fa[i] = i;
        }

        for(int i = 1; i <= w; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            edges[i] = new Edge(u, v, w, i);
        }

        // 按边权从小到大排序，只能用那一周及之前的兽径建树
        Arrays.sort(edges, 1, w + 1, new Comparator<Edge>() {
            public int compare(Edge a, Edge b) {
                return a.w - b.w;
            }
        });

        // 从最后一周开始跑，后面的周的边完备，更容易得到信息
        for (int i = w; i >= 1; i--) {
            res[i] = Kruskal(i);
            // 一旦生成不了生成树，前面的周也不可能生成树
            if(res[i] == -1){
                for(int j = i; j >= 1; j--){
                    res[j] = -1;    // 前面的周都没有生成树
                }
                break;
            }
        }


        for (int i = 1; i <= w; i++) {
            try {
                bw.write(res[i] + "\n");
                bw.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private class FastReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public FastReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}