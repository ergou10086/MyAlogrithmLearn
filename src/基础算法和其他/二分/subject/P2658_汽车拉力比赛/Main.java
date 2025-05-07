package 基础算法和其他.二分.subject.P2658_汽车拉力比赛;

import java.util.*;

public class Main {
    static final int N = 502;
    static int n, m, stx, sty, cnt;
    static Node[][] a = new Node[N][N];
    static int[] dex = {0,1,0,-1};
    static int[] dey = {1,0,-1,0};
    static boolean[][] vis = new boolean[N][N];
    static int[][] point = new int[N][N];
    static int[][] arena = new int[N][N];

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean bfs(int mid){
        Queue<Node> q = new LinkedList<>();

        for (boolean[] vi : vis) {
            // 初始化
            Arrays.fill(vi, false);
        }
        vis[stx][sty] = true;
        q.add(a[stx][sty]);
        int sum = 1; // 第一个点已经走过了

        while(!q.isEmpty()){
            Node cur = q.poll();

            // 四个方向
            for(int px = 0; px < 4; px++){
                int dx = cur.x + dex[px];
                int dy = cur.y + dey[px];

                if(dx < 1 || dx > m || dy < 1 || dy > n || vis[dx][dy] || Math.abs(arena[dx][dy] - arena[cur.x][cur.y]) > mid){
                    continue;
                }

                // 如果合法
                vis[dx][dy] = true;
                sum += point[dx][dy]; // 统计到达的路标数
                q.add(a[dx][dy]);

                // 如果走到了所用的路标
                if (sum == cnt) return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        m = scanner.nextInt();
        n = scanner.nextInt();
        int left = 0, right = 0, mid, ans = 0;

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                arena[i][j] = scanner.nextInt();
                a[i][j] = new Node(i, j);
                right = Math.max(right, arena[i][j]);
            }
        }

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                point[i][j] = scanner.nextInt();
                // 统计路标数量
                if(point[i][j] > 0){
                    cnt++;
                    // 起始路标
                    if(cnt == 1){
                        stx = i;
                        sty = j;
                    }
                }
            }
        }


        // 二分
        while(left <= right){
            mid = (left + right) / 2;
            if (bfs(mid)) {
                // 难度可被提升，更新
                ans = mid;
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }

        System.out.println(ans);
        scanner.close();
    }
}
