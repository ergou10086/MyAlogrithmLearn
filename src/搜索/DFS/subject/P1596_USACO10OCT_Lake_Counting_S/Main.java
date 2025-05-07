package 搜索.DFS.subject.P1596_USACO10OCT_Lake_Counting_S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}



class Solutions{
    private final static int N = 1010;
    private char[][] map = new char[N][N];
    private int[] dx = {-1,-1,-1,0,1,1,1,0};
    private int[] dy = {-1,0,1,1,1,0,-1,-1};
    private int n, m;


    private void dfs(int x, int y) {
        map[x][y] = '.';
        for (int i = 0; i < 8; i++) {
            int ax = x + dx[i];
            int ay = y + dy[i];
            if (ax < 0 || ax >= n || ay < 0 || ay >= m) continue;
            if (map[ax][ay] == '.') continue;  // 如果已经是 '.'，则跳过
            dfs(ax, ay);  // 递归调用
        }
    }


    public Solutions(){
        FastReader sc = new FastReader();
        n = sc.nextInt();
        m = sc.nextInt();
        for(int i = 0; i < n; i++){
            map[i] = sc.next().toCharArray();
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

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}