package 动态规划.template.背包.求方案数.恰好装满的方案数._01背包;

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

        for (int i = 0; i <= m; i++){
            f[i] = -1000;
        }
        // 不装入物品的方案是1，价值为0
        f[0] = 0;
        c[0] = 1;

        // 这里之后c数组只用能装满的方案（c[0]开始转移）去更新，f数组也一样
        // 剩下就是方案数的更新

        // 物品
        for(int i = 1; i <= n; i++){
            // 体积
            for(int j = m; j >= vi[i]; j--){
                //更优，装
                if(f[j - vi[i]] + wi[i] > f[j]) {
                    f[j] = f[j - vi[i]] + wi[i];
                    // 容量从j - vi[i]增加到j，只是多装入了一件物品，方案数并没有增加
                    c[j] = c[j - vi[i]];
                // 装新物品总价值相等
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
    }
}
