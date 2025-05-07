package 搜索.DFS.template.深搜.P1596_USACO10OCT_LakeCountingS;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions{
    private final int N = 1010;
    private int n, m;
    private char[][] map = new char[N][N];
    private int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    private int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};

    private void dfs(int x, int y){
        map[x][y] = '.';
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                int dex = x + dx[i];
                int dey = y + dy[j];
                if(dex < 0 || dex >= n || dey < 0 || dey >= m) continue;
                if(map[dex][dey] == '.') continue;
                dfs(dex, dey);
            }
        }
    }

    public Solutions(){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i < n; i++){
            String line = sc.nextLine();
            for(int j = 0; j < m; j++){
                map[i][j] = line.charAt(j);
            }
        }

        int ans = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(map[i][j] == 'W'){
                    ans++;
                    dfs(i, j);
                }
            }
        }

        System.out.println(ans);
        sc.close();
    }
}