package 搜索.DFS.subject.P8662_蓝桥杯2018省AB_全球变暖;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args){
        new Solutions();
    }
}


class Solutions {
    private char[][] map = new char[1010][1010];
    private boolean[][] vis = new boolean[1010][1010];
    private int[] dx = {-1, 0, 1, 0};
    private int[] dy = {0, 1, 0, -1};
    private int ans, sum;

    // 判断这一块#是否会被.淹没
    private boolean check(int x, int y){
        for(int sp = 0; sp < 4; sp++){
            int xx = x + dx[sp];
            int yy = y + dy[sp];

            if(map[xx][yy] == '.'){
                return false;
            }
        }
        return true;
    }

    private void dfs(int x,int y){
        vis[x][y] = true;

        if(check(x,y)){
            sum++;
        }

        for(int k = 0; k < 4; k++){
            int xx = x + dx[k];
            int yy = y + dy[k];
            if(map[xx][yy] == '#' && !vis[xx][yy]){
                dfs(xx,yy);
            }
        }
    }


    public Solutions(){
        FastReader sc = new FastReader();
        int n = sc.nextInt();

        for(int i = 0; i < n; i++){
            map[i] = sc.next().toCharArray();
        }

        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == '#' && !vis[i][j]) {
                    sum = 0;
                    // 一块陆地像素与海洋相邻(上下左右四个相邻像素中有海洋),就会被淹没。
                    dfs(i, j);
                    if(sum == 0){
                        ans++;
                    }
                }
            }
        }
        System.out.println(ans);
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