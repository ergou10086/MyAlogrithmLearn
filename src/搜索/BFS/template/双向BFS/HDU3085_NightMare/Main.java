package 搜索.BFS.template.双向BFS.HDU3085_NightMare;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}


// 鬼的扩张无视墙的阻挡，鬼先扩展，然后男孩女孩才可以移动，判断是能否回合
// 实用双向bfs，建立两个队列，分别从男孩女孩的初始位置开始搜索，两边轮流进行
// 实时计算新位置和鬼的曼哈顿距离，小于等于当前轮数的二倍，不合法，不入队
// 如果某处既能被男孩到达也能被女孩到达，回合

class Solutions{
    // 坐标类
    class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private final int N = 810;
    private int n, m;
    private char[][]g = new char[N][N];
    private int[][] vis = new int[N][N];   // 男女的可达性，2表示女孩走过，1表示男孩走过
    private Pair boy, girl;   // 男孩bfs3层，女孩一层
    private Pair[] ghost = new Pair[2];   // 人鬼的初始位置
    private int[] dx = {-1, 0, 1, 0};
    private int[] dy = {0, 1, 0, -1};

    private boolean check(int x, int y, int time) {
        // 出界和墙
        if(x < 0 || x >= n || y < 0 || y >= m || g[x][y] == 'X') return false;
        // 距离小于2倍扩张轮数，会被吞噬
        for(int i = 0; i < 2; i++){
            if(Math.abs(x - ghost[i].x) + Math.abs(y - ghost[i].y) <= time * 2) return false;
        }
        return true;
    }

    private int bfs(){
        int time = 0;
        for(int i = 0; i < N; i++) Arrays.fill(vis[i], 0);

        // 搜男孩女孩的队列
        Queue<Pair> qb = new LinkedList<>();
        Queue<Pair> qg = new LinkedList<>();
        // 起点入队列
        qb.add(boy);
        qg.add(girl);

        while(!qb.isEmpty() || !qg.isEmpty()){
            time++;   // 轮数+1
            // 男孩走三步
            for (int i = 0; i < 3; i++) {
                // 枚举队中所有的点
                int s = qb.size();
                for (int j = 0; j < s; j++) {
                    Pair prb = qb.poll();
                    int x = prb.x;
                    int y = prb.y;
                    if(!check(x, y, time)) continue;
                    // 四向搜索
                    for(int k = 0; k < 4; k++) {
                        int a = x + dx[k];
                        int b = y + dy[k];
                        if (check(a, b, time)) {
                            // 成功回合
                            if (vis[a][b] == 2) return time;
                            // 没走过，男孩走，并且新点入队
                            if (vis[a][b] == 0) {
                                vis[a][b] = 1;
                                qb.add(new Pair(a, b));
                            }
                        }
                    }
                }
            }
            // 女孩走一步
            for(int i = 0; i < 1; i++){
                int s = qg.size();
                for (int j = 0; j < s; j++) {
                    Pair prg = qg.poll();
                    int x = prg.x;
                    int y = prg.y;
                    if(!check(x, y, time)) continue;
                    for(int k = 0; k < 4; k++) {
                        int a = x + dx[k];
                        int b = y + dy[k];
                        if (check(a, b, time)) {
                            if(vis[a][b] == 1) return time;
                            if (vis[a][b] == 0) {
                                vis[a][b] = 2;
                                qg.add(new Pair(a, b));
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }

    public Solutions(){
        FastReader sc = new FastReader();
        int T = sc.nextInt();
        while(T-- > 0){
            n = sc.nextInt();
            m = sc.nextInt();
            for (int i = 0; i < n; i++) {
                String line = sc.next();
                g[i] = line.toCharArray();
            }

            int ts = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (g[i][j] == 'M') boy = new Pair(i, j);
                    else if (g[i][j] == 'G') girl = new Pair(i, j);
                    else if (g[i][j] == 'Z') ghost[ts++] = new Pair(i, j);
                }
            }

            System.out.println(bfs());
        }
    }


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

        int nextInt() {
            return Integer.parseInt(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}