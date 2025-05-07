package 动态规划.template.背包.二维费用背包.LuoguP1855榨取kkksc03;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int M = sc.nextInt();
        int T = sc.nextInt();
        int[][] f = new int[210][210];

        for(int i = 1; i <= n; i++){
            int mi = sc.nextInt();
            int ti = sc.nextInt();
            for(int j = M; j >= mi; j--){
                for(int k = T; k >= ti; k--){
                    f[j][k] = Math.max(f[j][k], f[j - mi][k - ti] + 1);
                }
            }
        }
        System.out.println(f[M][T]);
        sc.close();
    }
}
