package 基础算法和其他.差分.template.普通差分.lanqiao3291区间更新;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();

            int[] a = new int[n];
            int[] diff = new int[n + 1];

            // 读取数组a的元素
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }

            // 初始化差分数组diff
            diff[0] = a[0];
            for (int i = 1; i < n; i++) {
                diff[i] = a[i] - a[i - 1];
            }

            // 进行m次差分操作
            for (int i = 0; i < m; i++) {
                int x = scanner.nextInt() - 1;
                int y = scanner.nextInt() - 1;
                int z = scanner.nextInt();

                diff[x] += z;
                diff[y + 1] -= z;
            }

            // 将差分数组还原为原数组a
            a[0] = diff[0];
            for (int i = 1; i < n; i++) {
                a[i] = a[i - 1] + diff[i];
            }

            // 输出还原后的数组a
            for (int i = 0; i < n; i++) {
                System.out.print(a[i]);
                if (i < n - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();

        }
        scanner.close();
    }
}
