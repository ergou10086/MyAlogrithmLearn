package 搜索.DFS.template.深搜.P1605_迷宫;

import java.util.Scanner;

public class Main {
    static int n, m, t, sx, sy, fx, fy, ans = 0;
    static int[][] map;
    static int[] dx = {-1,0,1, 0};
    static int[] dy = { 0,1,0,-1};

    public static void dfs(int x, int y) {
        if(x == fx && y == fy) {
            ans += 1;
            return;
        }

        for(int i = 0; i < 4; i++){
            int a = x + dx[i], b = y + dy[i];
            if(a<1 || a>n || b<1 || b>m || map[a][b] == 1) continue;
            map[a][b] = 1; //锁定现场
            dfs(a, b);
            map[a][b] = 0; //恢复现场
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        t = sc.nextInt();
        sx = sc.nextInt();
        sy = sc.nextInt();
        fx = sc.nextInt();
        fy = sc.nextInt();
        map = new int[n+1][m+1];

        // 障碍物标记
        for (int i = 1; i <= t; i++){
            int tx = sc.nextInt();
            int ty = sc.nextInt();
            map[tx][ty] = 1;
        }

        map[sx][sy] = 1;
        dfs(sx, sy);
        System.out.println(ans);

        sc.close();
    }
}
