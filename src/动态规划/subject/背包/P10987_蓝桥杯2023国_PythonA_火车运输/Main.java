package 动态规划.subject.背包.P10987_蓝桥杯2023国_PythonA_火车运输;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}



class Solutions{

    public Solutions(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        int[] w = new int[n + 1];
        for(int i = 1; i <= n; i++){
            w[i] = sc.nextInt();
        }
        int[][] dp = new int[2005][2005];

        int res = -114514;
        for(int i = 1; i <= n; i++){
            for(int j = a; j >= 0; j--){
                for(int k = b; k >= 0; k--){
                    if(j >= w[i]) dp[j][k] = Math.max(dp[j][k], dp[j - w[i]][k] + w[i]);
                    if(k >= w[i]) dp[j][k] = Math.max(dp[j][k], dp[j][k - w[i]] + w[i]);
                    res = Math.max(res, dp[j][k]);
                }
            }
        }
        System.out.println(res);
    }
}