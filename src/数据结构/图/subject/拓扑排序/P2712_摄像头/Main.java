package 数据结构.图.subject.拓扑排序.P2712_摄像头;

// 一个摄像头只有在没有被其他摄像头看到的情况下才能被销毁
// 也就是找到第一个入度为0的点，开始找DAG
// 拓扑排序

import java.beans.Beans;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}



class Solutions {
    private final int MAX_N = 200002;  // 图中节点的最大数量
    // 邻接表存图
    private int[] to = new int[MAX_N];
    private int[] next = new int[MAX_N];
    private int[] head = new int[10002];
    private int[] inDegree = new int[10002];   // 入度数组
    private int[] a = new int[10002];     // 记录摄像头的位置
    private int tot = 0;
    private int n;
    private boolean[] visited = new boolean[50002];   // 哪个位置有摄像头

    // 有向图加边
    private void addEdge(int u, int v) {
        to[++tot] = v;
        next[tot] = head[u];
        head[u] = tot;
        inDegree[v]++;
    }


    public Solutions(){
        FastReader sc = new FastReader();
        Queue<Integer> queue = new LinkedList<>();
        int sum = 0;

        n = sc.nextInt();
        for(int i = 1; i <= n; i++){
            a[i] = sc.nextInt();
            int m = sc.nextInt();
            visited[a[i]] = true;   //记录a[i]处有摄像头
            for(int j = 1; j <= m; j++){
                int y = sc.nextInt();
                addEdge(a[i], y);    // 建有向图
            }
        }

        // 找到所有入度为0的节点
        for (int i = 1; i <= n; i++) {
            if(inDegree[a[i]] == 0){
                queue.add(a[i]);
            }
        }


        // 开始拓扑排序，Kahn
        while (!queue.isEmpty()){
            int node = queue.poll();
            sum++;
            for(int i = head[node]; i != 0; i = next[i]){
                int sp = to[i];
                inDegree[sp]--;
                // 如果这个地方有摄像头且入度为0,题解伟大
                if(inDegree[sp] == 0 && visited[sp]){
                    queue.add(sp);
                }
            }
        }
        if(sum == n) System.out.println("YES");
        else System.out.println(n - sum);
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