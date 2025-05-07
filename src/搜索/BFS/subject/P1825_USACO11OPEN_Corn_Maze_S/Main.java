package 搜索.BFS.subject.P1825_USACO11OPEN_Corn_Maze_S;

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

class Solutions {
    private final int N = 350;
    private char[][] a = new char[N][N];    // 地图
    private boolean[][] vis = new boolean[N][N];   // 判重
    private int n, m, sx, sy;
    private int[] dx = {1, 0, -1, 0};
    private int[] dy = {0, 1, 0, -1};

    class Point {
        int x, y, step;
        Point(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }

    private Point transmission(Point pos) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a[i][j] == a[pos.x][pos.y] && (i != pos.x || j != pos.y)) {
                    return new Point(i, j, pos.step);
                }
            }
        }
        return pos;
    }

    private void bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(sx, sy, 0));
        vis[sx][sy] = true;

        while (!queue.isEmpty()) {
            Point f = queue.poll();
            
            if (a[f.x][f.y] == '=') {
                System.out.println(f.step);
                return;
            }
            
            if (a[f.x][f.y] >= 'A' && a[f.x][f.y] <= 'Z') {
                Point next = transmission(f);
                if (!vis[next.x][next.y]) {
                    vis[next.x][next.y] = true;
                    queue.add(next);
                }
                continue;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = f.x + dx[i];
                int ny = f.y + dy[i];
                
                if (nx >= 1 && nx <= n && ny >= 1 && ny <= m && 
                    a[nx][ny] != '#' && !vis[nx][ny]) {
                    vis[nx][ny] = true;
                    queue.add(new Point(nx, ny, f.step + 1));
                }
            }
        }
    }

    public Solutions() {
        FastReader sc = new FastReader();
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            String s = sc.next();
            for (int j = 1; j <= m; j++) {
                // charAt从0开始
                a[i][j] = s.charAt(j - 1);
                // 起点标记
                if (a[i][j] == '@') {
                    sx = i;
                    sy = j;
                }
            }
        }

        bfs();
    }

}

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}
