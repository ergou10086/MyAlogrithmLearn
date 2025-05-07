package 搜索.DFS.template.DFS剪枝.P1731_NOI1999_生日蛋糕;

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
    private final int INF = 10000001;
    private final int N = 22;
    private int n, m;
    private int ans = INF;
    private int[] minv;
    private int[] mins;

    // 当前层是第u层，第u+1层的半径为R、高度为H
    // m~u+1层的体积和为v、面积和为s
    private void dfs(int u, int R, int H, int v, int s){
        // 处理完所有层
        if (u == 0) {
            if(v == n){   // 检查体积是否符合要求
                ans = Math.min(ans, s);
                return;  // 边界
            }
        }

        if(v + minv[u] > n) return;    // 你再加下一层体积直接不够用了，剪枝
        if (s + mins[u] >= ans) return;   // 你再加下一层面积已经不够优秀了，剪枝
        // 此估计值是剩余侧面积的理论下限，实际侧面积只能更大（因半径必须递减）
        if(s + 2 * (n - v) / R >= ans) return;  // n-可以用来估计1-u层的最小侧面积和，2 * R * h_avg → 总侧面积：u * 2 * R * h_avg

        // 前层半径必须小于上一层的半径 R-1，剩余体积 n-v 至少要能容纳当前层的最小高度
        int maxR = Math.min(R - 1, (int) Math.sqrt(n - v));
        // 优先尝试更大的半径，更快的填充体积
        for (int r = maxR; r >= u; r--) {
            // 该层高度必须小于上一层的高度（H-1），剩余体积 n-v 需要满足 r² * h ≤ n-v，因此最大允许高度为 (n-v)/(r²)
            int maxH = Math.min(H - 1, (n - v) / (r * r));
            // 优先尝试更大的高度，以更快接近体积目标
            for (int h = maxH; h >= u; h--) {
                int nV = v + r * r * h;
                int nS = s + 2 * r * h + (u == m ? r * r : 0);
                dfs(u - 1, r, h, nV, nS);
            }
        }

    }

    public Solutions() {
        FastReader sc = new FastReader();
        n = sc.nextInt();   // n pi
        m = sc.nextInt();   // 层数
        minv = new int[N];
        mins = new int[N];
        // 体积就是圆柱体积之和
        // 面积就是每个上表面积相加，pi被约掉了
        // 枚举每层的半径和高度

        // 预处理
        for (int i = 1; i <= m; i++) {
            minv[i] = minv[i - 1] + i * i * i;   // 1~i层的最小体积和
            mins[i] = mins[i - 1] + 2 * i * i;   // 1~i层的最小侧面积和
        }

        dfs(m,INF,INF,0,0);
        if(ans == INF) ans = -1;
        System.out.println(ans);
    }


    private class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        String next(){
            while(!st.hasMoreElements()){
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        int nextInt(){
            return Integer.parseInt(next());
        }
    }
}