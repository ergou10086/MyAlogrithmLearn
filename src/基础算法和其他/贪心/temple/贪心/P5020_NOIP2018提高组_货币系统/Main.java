package 基础算法和其他.贪心.temple.贪心.P5020_NOIP2018提高组_货币系统;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

// 一个数拆解成几个数的和，要求需要的数最少
// 完全背包
// 排序，方便从小到大拼凑
// 两个货币系统 (n,a) 和 (m,b) 等价，
// 当且仅当 (n,a) 中每个种类的货币都能被 (m,b) 表示且 (m,b) 中每个种类的货币都能被 (n,a) 表示

class Solutions {
    int[] dp = new int[25010];   // dp[i]表示数字i能被拼凑出的方案数
    int[] a = new int[105];

    public Solutions() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            for (int i = 1; i <= n; i++) a[i] = sc.nextInt();
            Arrays.sort(a, 1, n + 1);

            Arrays.fill(dp, 0);
            dp[0] = 1;     // 某个数字自身表示的

            // 货币的面额有几种
            for (int i = 1; i <= n; i++) {
                // 枚举面额（背包体积）
                for (int j = a[i]; j <= a[n]; j++) {
                    // 拆表示是只能从dp[0]转移过来才行，所以看拆面额能否拆的开
                    dp[j] += dp[j - a[i]];
                }
            }

            int ans = 0;
            for (int i = 1; i <= n; i++)
                // 要求只能用自身来表示的，因为是求面额
                if (dp[a[i]] == 1) ans++;
            System.out.println(ans);
        }
        sc.close();
    }
}