package 基础算法和其他.差分.template.二维差分.基本二维差分diff数组;

import java.util.Scanner;

// 二维差分数组：diff[i][j] = nl[i][j] - nl[i-1][j] - nl[j][i-1] + nl[i-1][j-1]

public class Main {

    // 对差分数组进行区域修改操作的函数
    public static void areaAdd(int[][] diffList, int x1, int y1, int x2, int y2, int x) {
        diffList[x1][y1] += x;
        diffList[x2 + 1][y1] -= x;
        diffList[x1][y2 + 1] -= x;
        diffList[x2][y2] += x;
    }

    // 输出二维数组
    public static void output(int[][] a, int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < a[i].length; j++) {
                System.out.print(a[i][j]);
                if (j < a[i].length - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取矩阵的行数n和列数m
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // 初始化二维数组a和差分数组diff
        int[][] a = new int[n + 1][m + 1];
        int[][] diff = new int[n + 1][m + 1];

        // 输入二维矩阵
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                a[i][j] = scanner.nextInt();
            }
        }

        // 输出原始二维矩阵a
        output(a, n);

        // 计算差分数组diff
        // 二维差分数组：diff[i][j] = nl[i][j] - nl[i-1][j] - nl[j][i-1] + nl[i-1][j-1]
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                diff[i][j] = a[i][j] - a[i][j - 1] - a[i - 1][j] + a[i - 1][j - 1];
            }
        }

        // 输出差分数组diff
        output(diff, n);

        // 读取需要增加元素的矩阵区域和增加的元素值x
        int x1 = scanner.nextInt();
        int y1 = scanner.nextInt();
        int x2 = scanner.nextInt();
        int y2 = scanner.nextInt();
        int x = scanner.nextInt();

        // 对差分数组进行区域增加操作
        areaAdd(diff, x1, y1, x2, y2, x);

        // 重新计算原始数组a
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                a[i][j] = a[i][j - 1] + a[i - 1][j] - a[i - 1][j - 1] + diff[i][j];
            }
        }

        // 输出更新后的矩阵a
        output(a, n);

        scanner.close();
    }
}
