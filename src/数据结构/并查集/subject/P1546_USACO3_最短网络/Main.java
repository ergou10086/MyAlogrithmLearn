package 数据结构.并查集.subject.P1546_USACO3_最短网络;

import java.io.*;
import java.util.*;

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


class Solutions{
    private class node{
        int x, y, d;
        node(int x, int y, int d){
            this.x = x;   // 村庄自己的编号（第几个村庄与其他村庄的距离）
            this.y = y;   // 相对哪个村庄的距离
            this.d = d;   // 边权
        }
    }

    private int[] fa;

    private int find(int x) {
        // 如果 x 是自己的父节点，返回 x
        if (x == fa[x]) {
            return x;
        } else {
            // 路径压缩：将 p[x] 的根节点直接指向 x 的根节点
            fa[x] = find(fa[x]);
            return fa[x];
        }
    }


    public Solutions(){
        FastReader sc = new FastReader();

        int n = sc.nextInt();

        fa = new int[n + 2];
        List<node> ap = new ArrayList<>();
        int tot = 0;

        for(int i = 1; i <= n; i++){
            fa[i] = i;
            for(int j = 1; j <= n; j++){
                int dis = sc.nextInt();
                // 如果没有重复出现，就建立边，这里边长都是上下文相关的，要不然n维空间？
                if(j > i){
                    tot++;
                    ap.add(new node(i, j, dis));
                }
            }
        }
        // 按照长度排序
        ap.sort(Comparator.comparingInt(o -> o.d));

        int ans = 0;    // 边长
        int p = 1;    // 记录当前在哪个村庄拉线

        // Kruskal算法实现最小生成树
        for (node edge : ap) {
            if (find(edge.x) != find(edge.y)) {
                ans += edge.d;
                fa[find(edge.x)] = find(edge.y); // 合并两个集合
                p++;
                if (p == n) break; // 已经选完n-1条边
            }
        }

        System.out.println(ans);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        new Solutions();
    }
}
