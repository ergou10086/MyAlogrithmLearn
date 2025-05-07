package 搜索.BFS.template.双端队列.P4667_BalticOI2011_SwitchTheLampOn电路维修_Day1_;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

// 类似走迷宫，搜索从入口到出口操作代价最少的路径，所走必须是对角线
// 每个格点周围有四个对角格点，出界和不优的不走
// 可走有两类：连通和不连通
// 连通的一定比不连通的更优，所以入队的格点有 0 1 性，使用双端队列维护
// 更优的从队头入队，不优的从队尾入队，保证了更优的先出队，先扩展

// 队头出队扩展，同时，连通从队头入队，不通从队尾入队

class Solutions{
    private class Pair{
        int first, second;
        public Pair(){}
        public Pair(int first, int second){
            this.first = first;
            this.second = second;
        }
    }

    private final int N = 510;
    private int n, m;
    private char[][] g = new char[N][N]; // 存储格内斜边
    private int[][] dis = new int[N][N]; // 存储操作步数
    private boolean[][] vis = new boolean[N][N]; // 判重，保证只扩展一次
    // 判断对角格点是否连通，以田字格中心点为例子
    // 格点(1,1)的四对角格点位置 (0,0)  (0,2) (2,0) (2,2)
    // 格点(1,1)的四周格子内线条的左上角位置 (0,0)  (0,1) (1,0) (1,1)
    private char[] es = "\\/\\/".toCharArray(); // 格内斜边，左上角开始顺时针记录，注意转义字符
    private int[] dx = {-1, -1, 1, 1}, dy = {-1, 1, 1, -1};   // 四对角线格点增量
    private int[] ex = {-1, -1, 0, 0}, ey = {-1, 0, 0, -1};   // 代表格子内线段的方向数组的偏移量

    private int bfs() {
        for (int i = 0; i < N; i++) {
            Arrays.fill(dis[i], 0x3f3f3f3f); // 初始化距离为无穷大
            Arrays.fill(vis[i], false); // 初始化访问标记为 false
        }

        // 标记七点
        dis[0][0] = 0;
        // 双端队列
        Deque<Pair> q = new ArrayDeque<>();
        // 起点入队列
        q.addLast(new Pair(0, 0));

        while (!q.isEmpty()) {
            Pair u = q.pollFirst();
            int x = u.first, y = u.second; // 父格点
            if (vis[x][y]) continue; // 已出过队，已最小
            vis[x][y] = true; // 出队标记，保证只扩展一次

            for (int i = 0; i < 4; i++) {
                int a = x + dx[i], b = y + dy[i];
                // 判越界
                if (a < 0 || a > n || b < 0 || b > m) continue;

                int ea = x + ex[i], eb = y + ey[i];
                // dis[x][y]父格点，不连通需要操作一次，操作次数+1
                int d = dis[x][y] + (g[ea][eb] != es[i] ? 1 : 0);

                if(d < dis[a][b]){
                    dis[a][b] = d;
                    // 不连通，队尾入队
                    if(g[ea][eb] != es[i]) q.addLast(new Pair(a, b));
                    // 连通，队头入队
                    else q.addFirst(new Pair(a, b));
                }
            }
        }
        return dis[n][m];
    }

    public Solutions(){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            g[i] = sc.next().toCharArray();
        }
        int d = bfs();
        if (d == 0x3f3f3f3f) {
            System.out.println("NO SOLUTION");
        } else {
            System.out.println(d);
        }
        sc.close();
    }
}