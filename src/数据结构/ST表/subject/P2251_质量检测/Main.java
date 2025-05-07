package 数据结构.ST表.subject.P2251_质量检测;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] f = new int[n + 1][22];

        for (int i = 1; i <= n; i++) f[i][0] = scanner.nextInt();

        // 枚举区间长度（2的j次方）
        for (int j = 1; j <= 20; j++) {
            // 枚举起点
            for (int i = 1; i + (1 << j) - 1 <= n; i++) {
                f[i][j] = Math.min(f[i][j - 1], f[i + (1 << (j - 1))][j - 1]);
            }
        }

        int l = 1, r = m;
        while (l <= n - m + 1 && r <= n) {
            // java没有log2，换底公式
            int k = (int) (Math.log(r - l + 1) / Math.log(2));

            System.out.println(Math.min(f[l][k], f[r - (1 << k) + 1][k]));

            l += 1;
            r += 1;
        }
        scanner.close();
    }
}
