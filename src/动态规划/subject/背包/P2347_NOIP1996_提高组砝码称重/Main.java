package 动态规划.subject.背包.P2347_NOIP1996_提高组砝码称重;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 砝码重量
        int[] weights = {1, 2, 3, 5, 10, 20};
        // 每种砝码的数量
        int[] counts = new int[6];

        // 读入数据，计算总重量
        int totalWeight = 0;
        for (int i = 0; i < 6; i++) {
            counts[i] = sc.nextInt();
            totalWeight += weights[i] * counts[i];
        }

        // dp[w] 表示能否凑出重量 w
        boolean[] dp = new boolean[totalWeight + 1];
        dp[0] = true; // 初始状态：0 重量可以凑出（不选任何砝码）

        // 枚举每种砝码
        for(int i = 0; i < 6; i++){
            int weight = weights[i];
            int count = counts[i];

            for (int w = totalWeight; w >= weight; w--) {
                // 枚举当前使用的砝码是多重
                for(int k = 1; k < count && k * weight <= w; k++){
                    // 可以被称出加上这个砝码前的重量，那么这个也可以被称出
                    if (dp[w - k * weight]) {
                        dp[w] = true;
                    }
                }
            }
        }
    }
}
