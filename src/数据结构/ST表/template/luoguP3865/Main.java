package 数据结构.ST表.template.luoguP3865;

// ST表，稀疏表
// ST表解决可重复贡献的查询问题  例如：区间最值，区间按位和，区间gcd
// 修改用线段树

// 预处理ST表
// 倍增法递推：两个登场的小区间拼成一个大区间
// f[i][j]以第i个数为起点，长度为2^j的区间中的最大值
// 把2^j长度的区间分成两个等长的区间，长度为2^(j-1)
// f[i][j] = max(f[i][j - 1], f[i + 2^(j-1)][j - 1]

// 处理询问
// 对查询区间[l,r]分割，拼凑，区间长度的指数k = log2(r - l + 1)
// 区间[l, r]可以用两个长度为2^k的区间重叠拼成，因为各种情况，会在中间有重叠，不影响
// max(f[l][k], f[r - (1<<k) + 1][k])

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] f = new int[n + 1][22];

        for (int i = 1; i <= n; i++)  f[i][0] = scanner.nextInt();

        // 枚举区间长度（2的j次方）
        for (int j = 1; j <= 20; j++) {
            // 枚举起点
            for (int i = 1; i + (1 << j) - 1 <= n; i++) {
                f[i][j] = Math.max(f[i][j - 1], f[i + (1 << (j - 1))][j - 1]);
            }
        }

        // 处理查询操作
        for (int i = 1; i <= m; i++) {
            int l = scanner.nextInt();
            int r = scanner.nextInt();

            // java没有log2，换底公式
            int k = (int) (Math.log(r - l + 1) / Math.log(2));

            System.out.println(Math.max(f[l][k], f[r - (1 << k) + 1][k]));
        }

        scanner.close();
    }
}
