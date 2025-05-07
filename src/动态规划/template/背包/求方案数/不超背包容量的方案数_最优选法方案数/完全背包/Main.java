package 动态规划.template.背包.求方案数.不超背包容量的方案数_最优选法方案数.完全背包;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

    }
}

class Solutions {
    private int[] f = new int[1010];
    private int[] scheme = new int[1010];
    private int n, m;

    public Solutions() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        // 初始化
        Arrays.fill(f, -0x3f3f3f3f);
        // 一件物品不选时候的方案数为1
        f[0] = 0;
        scheme[0] = 1;

        for (int i = 1; i < n; i++) {
            int v, w;
            v = sc.nextInt();
            w = sc.nextInt();

            for (int j = v; j <= m; j++) {
                int cnt = 0;
                if (f[j] < f[j - v] + w) {
                    // 容量从j - vi[i]增加到j，只是多装入了一件物品，方案数并没有增加
                    cnt = scheme[j - v];
                } else if (f[j] == f[j - v] + w) {
                    // 出现了其他可选的方案，加上
                    cnt = scheme[j - v] + scheme[j];
                } else {
                    cnt = scheme[j];
                }
                scheme[j] = cnt % 1000007;
                f[j] = Math.max(f[j], f[j - v] + w);
            }
        }

        int res = 0;
        for (int i = 0; i <= m; i++) res = Math.max(res, f[i]);  // 找出最大价值
        int cnt = 0;
        for (int i = 0; i <= m; i++)   // 找出所有体积不同的最大价值，每个体积有不同的方案数，累加求和
            if (f[i] == res) cnt = (cnt + scheme[i]) % 1000007;
        System.out.println(cnt);
    }

}
