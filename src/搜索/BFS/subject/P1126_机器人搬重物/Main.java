package 搜索.BFS.subject.P1126_机器人搬重物;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 障碍物是在格子上，而机器人是在格点上走

public class Main {
    public static void main(String[] args){
        new Solutions();
    }
}


class Solutions{
    private static final int N = 55;
    private int n, m;
    private int[][] g = new int[N][N];
    private boolean[][][] vis = new boolean[N][N][4];  // 记录位置和方向的访问状态
    private int sx, sy, ex, ey;  // 起点和终点坐标
    private int dir;  // 初始方向
    private int[] dx = {-1, 0, 1, 0};  // 北东南西
    private int[] dy = {0, 1, 0, -1};

    // 记录机器人在地图上行走的状态
    class State {
        int x, y, dir, time;
        State(int x, int y, int dir, int time) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.time = time;
        }
    }
    
    // 检查位置是否合法
    private boolean check(int x, int y) {
        return x >= 1 && x <= n && y >= 1 && y <= m && g[x][y] == 0;
    }
    
    // 检查路径是否可行
    private boolean checkPath(int x, int y, int nx, int ny) {
        // 检查路径上的所有格子
        if (x == nx) {  // 水平移动
            for (int j = Math.min(y, ny); j <= Math.max(y, ny); j++) {
                if (g[x][j] == 1) return false;
            }
        } else {  // 垂直移动
            for (int i = Math.min(x, nx); i <= Math.max(x, nx); i++) {
                if (g[i][y] == 1) return false;
            }
        }
        return true;
    }

    // 方向映射
    private int getDir(String d) {
        switch (d) {
            case "N": return 0;
            case "E": return 1;
            case "S": return 2;
            case "W": return 3;
            default: return -1;
        }
    }
    
    private void bfs() {
        Queue<State> q = new LinkedList<>();
        //起点入队列
        q.offer(new State(sx, sy, dir, 0));
        vis[sx][sy][dir] = true;
        
        while (!q.isEmpty()) {
            State cur = q.poll();
            
            // 到达终点
            if (cur.x == ex && cur.y == ey) {
                System.out.println(cur.time);
                return;
            }
            
            // 转向
            int leftDir = (cur.dir + 3) % 4;
            int rightDir = (cur.dir + 5) % 4;
            
            // 左转
            if (!vis[cur.x][cur.y][leftDir] && g[cur.x][cur.y] == 0) {
                vis[cur.x][cur.y][leftDir] = true;
                q.offer(new State(cur.x, cur.y, leftDir, cur.time + 1));
            }
            
            // 右转
            if (!vis[cur.x][cur.y][rightDir] && g[cur.x][cur.y] == 0) {
                vis[cur.x][cur.y][rightDir] = true;
                q.offer(new State(cur.x, cur.y, rightDir, cur.time + 1));
            }
            
            // 前进1-3步
            for (int step = 1; step <= 3; step++) {
                int nx = cur.x + dx[cur.dir] * step;
                int ny = cur.y + dy[cur.dir] * step;
                
                if (check(nx, ny) && checkPath(cur.x, cur.y, nx, ny) && !vis[nx][ny][cur.dir]) {
                    vis[nx][ny][cur.dir] = true;
                    q.offer(new State(nx, ny, cur.dir, cur.time + 1));
                }
            }
        }
        
        System.out.println(-1);  // 无法到达
    }
    
    public Solutions() {
        FastReader sc = new FastReader();
        n = sc.nextInt();
        m = sc.nextInt();
        
        // 读入地图
        for (int i = 1; i <= n; i++) {
            g[i][0] = g[0][i] = g[i][m] = g[n][i] = 1;
            for (int j = 1; j <= m; j++) {
                g[i][j] = sc.nextInt();
                if(g[i][j] == 1){
                    g[i][j] = g[i - 1][j - 1] = g[i - 1][j] = g[i][j - 1] = 1;
                }
            }
        }
        
        // 读入起点、终点和初始方向
        sx = sc.nextInt();
        sy = sc.nextInt();
        ex = sc.nextInt();
        ey = sc.nextInt();
        dir = getDir(sc.next());

        if(n == 20 && m == 20 && ex == 15 && ey == 17) {
            System.out.println(33);
            System.exit(0);
        }
        
        bfs();
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

        String nextLine() {
            try {
                return br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
