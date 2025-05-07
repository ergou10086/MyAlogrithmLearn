package 搜索.BFS.subject.P12322_蓝桥杯2024国JavaC_瞬移;

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

// 发现在预处理时候，把距离模上 L，最多就只有 L 个值
// 1 2 3 l=3
// (1+1+2-1) mod 3 + 1 = 1

class Solutions{
    private int n, l;
    private int[] a = new int[2005];
    private boolean[] vis = new boolean[2005]; // 标记每个点是否访问过
    private boolean[] to = new boolean[2005]; // 标记哪些步长是可用的

    class Pos{
        int x;      // 当前所在位置
        int step;   // 已经走的步数
        public Pos(){}
        public Pos(int x, int step){
            this.x = x;
            this.step = step;
        }
    }

    private int bfs(){
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(1,0));   // 秘境的入口为 1
        vis[1] = true;

        while(!q.isEmpty()){
            Pos p = q.poll();
            // 到达 l 位置，说明到终点
            if(p.x == l){
                return p.step;
            }

            for (int i = 1; i <= l; i++) {
                // 可到达
                if(to[i]){
                    // 计算下一个点
                    int nx = (p.x + i - 1) % l + 1;
                    if (!vis[nx]) {
                        vis[nx] = true;
                        q.offer(new Pos(nx, p.step + 1));
                    }
                }
            }
        }
        return -1;
    }

    public Solutions(){
        FastReader sc = new FastReader();
        n = sc.nextInt();
        l = sc.nextInt();  // 模数
        for(int i = 1; i <= n; i++) a[i] = sc.nextInt();

        Arrays.fill(vis, false);
        // 预处理出 x + y
        for(int i = 1; i <= n; i++){
            for(int j = i + 1; j <= n; j++){
                int step = (a[i] + a[j]) % l;   // 处理时候，把距离模上 L，最多就只有 L 个值
                if (step == 0) step = l; // 保证步长为1~l
                to[step] = true;
            }
        }

        System.out.println(bfs());
    }


    class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        String next(){
            while(!st.hasMoreElements()){
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }
        int nextInt(){
            return Integer.parseInt(next());
        }
    }
}