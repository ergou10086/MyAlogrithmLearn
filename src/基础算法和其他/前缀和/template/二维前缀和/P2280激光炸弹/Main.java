package 基础算法和其他.前缀和.template.二维前缀和.P2280激光炸弹;

import java.util.*;

public class Main {
    // 二维前缀和
    static final int INT = 5005;
    static int n,  m;  // n个目标, 边长为m的正方形
    static int[][] s = new int[INT][INT]; // 前缀和数组
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();

        for(int i = 0; i < n; i++){
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int v = scanner.nextInt();
            x++; y++;   // 坐标偏移
            s[x][y] += v;
        }

        for(int i = 1; i <= 5001; i++){
            for(int j = 1; j <= 5001; j++){
                // 二维前缀和求和公式
                s[i][j] += s[i-1][j] + s[i][j-1] - s[i-1][j-1];
            }
        }

        // (x1,y1)-(x2,y2)之和 = sum[x2,y2] - sum[x1-1,y2] - sum[x2,y1-1] + sum[x1-1,y1-1]
        int res = 0;
        for(int i = m; i <= 5001; i++){
            for(int j = m; j <= 5001; j++){
                res = Math.max(res, s[i][j] - s[i-m][j] - s[i][j-m] + s[i-m][j-m]);
            }
        }
        System.out.println(res);
    }
}
