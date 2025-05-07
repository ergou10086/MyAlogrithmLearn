package 动态规划.template.背包.求方案数.不超背包容量的方案数_最优选法方案数._01背包;

// n件物品m容量，每个物品用一次，求出最有选法的方案数

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 物品数量
        int n = scanner.nextInt();
        // 背包容量
        int m = scanner.nextInt();
        int[] vi = new int[n + 1];   // 体积
        int[] wi = new int[n + 1];  // 价值

        for (int i = 1; i <= n; i++){
            vi[i] = scanner.nextInt();
            wi[i] = scanner.nextInt();
        }

        // f[i]表示背包容量为i时最优选法的总价值
        // c[i]表示背包容量为i时最优选法的方案数
        int[] f = new int[1010];
        int[] c = new int[1010];

        // 初始化，背包不装也是一种方案
        for (int i = 0; i <= m; i++){
            f[i] = 0;
            c[i] = 1;
        }

        // 物品
        for(int i = 1; i <= n; i++){
            // 体积
            for(int j = m; j >= vi[i]; j--){
                //更优，装
                if(f[j - vi[i]] + wi[i] > f[j]) {
                    f[j] = f[j - vi[i]] + wi[i];
                    // 容量从j - vi[i]增加到j，只是多装入了一件物品，方案数并没有增加
                    c[j] = c[j - vi[i]];
                // 存在了其他可能的方案
                }else if(f[j - vi[i]] + wi[i] == f[j]){
                    // 这里c[j]更新分为两部分
                    // c[j]是不装新物品的时候，容量为j的方案数
                    // 装入新物品时候，容量为j对应的方案数为c[j - vi[i]]
                    c[j] = (c[j] + c[j - vi[i]]) % 1000000007;
                }
            }
        }

        System.out.println(c[m]);
        scanner.close();

        // 如果是总价值最大需要看看别的方案
    }
}
