package 基础算法和其他.差分.template.二维差分.LuoguP3397地毯;
import java.util.Scanner;

// 二维前缀和： sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + nl[i][j]
// 二维差分数组：diff[i][j] = nl[i][j] - nl[i-1][j] - nl[j][i-1] + nl[i-1][j-1]

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        // 差分数组
        int[][] a = new int[n + 1][m + 1];

        while (m > 0) {
            m--;
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();
            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();

            for (int i = x1; i <= x2; i++) {
                a[i][y1]++;
                a[i][y2 + 1]--; // 逐行差分
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                a[i][j] += a[i][j - 1]; // 逐行还原
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }

        scanner.close();
    }
}