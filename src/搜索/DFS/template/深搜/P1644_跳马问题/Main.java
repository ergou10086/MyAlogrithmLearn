package 搜索.DFS.template.深搜.P1644_跳马问题;

import java.util.Scanner;

public class Main {
    static int n, m, ans;
    static int[] dx = {2,1,-1,-2};
    static int[] dy = {1,2, 2, 1};

    public static void dfs(int x, int y){
        if(x == n && y == m){
            ans += 1;
            return;
        }

        for(int i = 0; i < 4; i++){
            int newX = x + dx[i];
            int newY = y + dy[i];
            if(newX < 0 || newX > n || newY < 0 || newY > m) continue;
            dfs(newX, newY);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        dfs(0,0);
        System.out.println(ans);
        scanner.close();
    }
}
