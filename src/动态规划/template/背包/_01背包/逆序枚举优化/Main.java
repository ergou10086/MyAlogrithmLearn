package 动态规划.template.背包._01背包.逆序枚举优化;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] val = new int[n + 1];
        int[] wei = new int[n + 1];
        // 滚动数组优化掉一维，逆序枚举
        int[] dp = new int[m + 1];

        for (int i = 1; i <= n; i++) {
            wei[i] = sc.nextInt();
            val[i] = sc.nextInt();
        }

        for(int i = 1; i <= n; i++){
            // 逆序循环，用旧值f[j-w[i]]更新f[j]，相当于用上一行的取更新
            for(int j = m; j >= wei[i]; j--){   // 背包容量逆序循环，避免顺序循环时候f[j-w[i]]先于f[j]更新
                //if(j < wei[i]){
                //dp[j] = dp[j];
                //}else{
                dp[j] = Math.max(dp[j], dp[j - wei[i]] + val[i]);

            }
        }

        System.out.println(dp[m]);

        sc.close();
    }
}
