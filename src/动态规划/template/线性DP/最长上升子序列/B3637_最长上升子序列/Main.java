package 动态规划.template.线性DP.最长上升子序列.B3637_最长上升子序列;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n + 1];
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) a[i] = scanner.nextInt();

        for(int i = 1; i <= n; i++) dp[i] = 1;

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= i; j++){
                // 在i的前面找到小于a[i]的数字a[j]
                // 用对于的dp值更新dp[i]
                if(a[i] > a[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int res = 0;
        for(int i = 1; i <= n; i++){
            res = Math.max(res, dp[i]);
        }
        System.out.println(res);

        scanner.close();
    }
}
