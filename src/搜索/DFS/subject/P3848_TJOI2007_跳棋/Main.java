package 搜索.DFS.subject.P3848_TJOI2007_跳棋;


import java.util.Scanner;

public class Main {
    static int n, stax, stay, ans = 0;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static void dfs(int x, int y, int step) {
        ans = Math.max(ans, step);

        for(int i = 0; i < 4; i++) {
            int nx = x;
            int ny = y;
            int temp = 0;
            // 不超图
            while(nx + dx[i] > 0 && nx + dx[i] <= n && ny + dy[i] > 0 && ny + dy[i] <= n){
                nx += dx[i];
                ny += dy[i];
                temp++;
                // 向上，下，左，右4个方向连续越过若干个1格后跳入0格子了
                if(map[nx][ny] == 0) break;
            }
            // 不超图，没走过，从0跳到下一个0了，且两个0不连续
            if(nx > 0 && nx <= n && ny > 0 && ny <= n && !visited[nx][ny] && map[nx][ny] == 0 && temp != 1){
                visited[nx][ny] = true;    // 标记
                dfs(nx, ny, step + temp);   // 继续走
                visited[nx][ny] = false;   // 回溯
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        stax = sc.nextInt();
        stay = sc.nextInt();
        map = new int[n + 1][n + 1];
        visited = new boolean[n + 1][n + 1];

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                map[i][j] = sc.nextInt();
                visited[i][j] = false;
            }
        }
        visited[stax][stay] = true;

        dfs(stax, stay, 0);

        System.out.println(ans);
        sc.close();
    }
}
