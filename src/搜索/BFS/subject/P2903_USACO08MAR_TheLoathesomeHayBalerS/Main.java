package 搜索.BFS.subject.P2903_USACO08MAR_TheLoathesomeHayBalerS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions {
    class Cogs {
        int x, y, r;  // 坐标和半径

        public Cogs(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }
    private Cogs[] gears;
    private int n, xt, yt;
    private int st = -1, ed = -1;  // 驱动齿轮和工作齿轮的索引
    private double[] speed;  // 每个齿轮的转速
    private int[] parent;  // 记录路径
    private boolean[] vis;  // 记录是否访问过

    public Solutions() {
        FastReader sc = new FastReader();
        n = sc.nextInt();   // n个齿轮
        xt = sc.nextInt();   // 工作齿轮坐标
        yt = sc.nextInt();

        gears = new Cogs[n + 1];  // 齿轮从1开始编号
        speed = new double[n + 1];
        parent = new int[n + 1];
        vis = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int r = sc.nextInt();
            gears[i] = new Cogs(x, y, r);

            // 找到驱动齿轮和工作齿轮的索引
            if (x == 0 && y == 0) {
                st = i;  // 驱动齿轮
            }
            if (x == xt && y == yt) {
                ed = i;  // 工作齿轮
            }
        }

        // 如果未找到驱动齿轮，直接输出0
        if (st == -1) {
            System.out.println(0);
            return;
        }

        // BFS初始化
        Queue<Integer> q = new LinkedList<>();
        q.offer(st);
        vis[st] = true;
        speed[st] = 10000;  // 驱动齿轮的转速为10000

        // BFS遍历
        while (!q.isEmpty()) {
            int u = q.poll();

            // 遍历所有齿轮，找到与当前齿轮接触的齿轮
            for (int v = 1; v <= n; v++) {
                if (vis[v]) continue;  // 跳过已访问的齿轮

                // 判断是否接触
                double distanceSq = Math.pow(gears[u].x - gears[v].x, 2) + Math.pow(gears[u].y - gears[v].y, 2);
                double radiusSumSq = Math.pow(gears[u].r + gears[v].r, 2);

                if (Math.abs(distanceSq - radiusSumSq) < 1e-6) {  // 浮点数精度判断
                    vis[v] = true;
                    speed[v] = -speed[u] * gears[u].r / gears[v].r;  // 计算转速
                    parent[v] = u;  // 记录路径
                    if (v == ed) break;
                    q.offer(v);
                }
            }
        }

        // 如果未找到工作齿轮，输出0
        if (!vis[ed]) {
            System.out.println(0);
            return;
        }

        // 计算传动序列中所有齿轮转速的绝对值之和
        double ans = 0;
        for (int i = ed; i != 0; i = parent[i]) {
            ans += Math.abs(speed[i]);
        }

        // 输出整数结果
        System.out.println((int) ans);
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        String next() {
            while (!st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}