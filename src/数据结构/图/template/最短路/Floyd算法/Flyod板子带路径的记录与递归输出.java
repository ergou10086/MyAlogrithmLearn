package 数据结构.图.template.最短路.Floyd算法;


import javax.management.MBeanRegistration;
import java.io.*;
import java.util.*;

public class Flyod板子带路径的记录与递归输出 {
    public static void main(String[] args) {
        new Solutions();
    }
}



class Solution{
    private int n, m;
    private int[][] dp = new int[210][210];
    private int[][] path = new int[210][210];   // 记录路径

    private void floyd(){
        // 循环时候k必须在最外层，计算k层的dp[i,j]时候，是把k-1层的二维表向第k层的二维表投射
        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    // 路径不经过k点，第k-1层的p[i,j]直接投射
                    // 路径经过k点，第k-1层的p[i,k]，dp[k,j]都不经过k点，可以直接投射到对应位置加起来
                    // 如果更短,以k为桥更新旧的路径，两类计算与k围观，三维变二维
                    dp[i][j] = Math.min(dp[i][j],dp[i][k] + dp[k][j]);
                    path[i][j] = k;   // 记录插点
                }
            }
        }
    }

    // 递归输出路径，类似中序遍历
    private void GetPath(int i, int j){
        if(path[i][j] == 0) return;
        int k = path[i][j];
        GetPath(i, k);
        System.out.println(k + " ");
        GetPath(k, j);
    }


    public Solution() {
        FastReader_ sc = new FastReader_();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = sc.nextInt();
        m = sc.nextInt();
        // 初始化
        // 无边dp[i][j] = 正无穷,有边dp[i][j] = 边权，i=j时，dp[i][j]=0
        for(int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], (int) 1e9 + 7);
        }

        for(int i=1; i<=n; i++) dp[i][i] = 0;

        for(int i=0; i<m; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            dp[a][b] = Math.min(dp[a][b], c); // 重边处理
        }

        floyd();

        int start = sc.nextInt();
        int end = sc.nextInt();
        System.out.println(start + " ");
        GetPath(start, end);
        System.out.println(end + " ");

        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                try {
                    bw.write(dp[i][j]);
                    bw.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                bw.write("\n");
                bw.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}



class FastReader_ {
    BufferedReader br;
    StringTokenizer st;

    public FastReader_() {
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

