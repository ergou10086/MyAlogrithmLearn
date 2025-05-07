package 动态规划.subject.线性dp.P12175_蓝桥杯2025_省_PythonB_园艺;

import java.awt.print.Printable;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] tree = new int[n];
        for (int i = 0; i < n; i++) tree[i] = sc.nextInt();

        int[][] dp = new int[n][n];   // dp[i][d]表示以i结尾，间隔d的最长序列长度
        int max = 1; // 至少可以选一个

        // 每棵树可以单独形成一个子序列
        for (int i = 0; i < n; i++) dp[i][0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // 高度递增，更新
                if(tree[j] < tree[i]){
                    int diff = i - j;    // 计算间隔
                    // 从树 j 转移到树 i 的基础长度
                    int prev = dp[j][diff];   // 树 j 结尾、间隔为 diff 的最长子序列长度，而这是前驱
                    if(prev == 0){   // 树 j 单独存在
                        prev = 1;
                    }
                    // 发现更优的就更新
                    if(dp[i][diff] < prev + 1){
                        dp[i][diff] = prev + 1;
                        if(dp[i][diff] > max){
                            max = dp[i][diff];
                        }
                    }
                }
            }
        }

        System.out.println(max);
        sc.close();
    }
}
