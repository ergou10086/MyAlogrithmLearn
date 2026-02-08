package 动态规划.subject.背包.P2925_USACO08DECHay_For_Sale_S;

import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int c = sc.nextInt();
        int h = sc.nextInt();

        // 设dp[j]表示容积为j的马车能装载的最大稻草体积
        int[] v = new int[h + 1];
        int[] dp = new int[c + 1];

        for (int i = 1; i <= h; i++) {
            v[i] = sc.nextInt();
            // 兼容极端情况：若某捆稻草体积等于c，直接输出c（无需后续计算）
            if (v[i] == c) {
                System.out.println(c);
                sc.close(); // 关闭资源
                return;
            }
        }

        // 遍历每捆稻草
        for(int i = 1; i <= h; i++){
            // 逆序遍历体积
            for(int j = c; j >= v[i]; j--){
                dp[j] = Math.max(dp[j], dp[j-v[i]] + v[i]);
                if(dp[c] == c){
                    System.out.println(dp[c]);
                    sc.close();
                    return;
                }
            }
        }

        System.out.println(dp[c]);
        sc.close();
    }
}
