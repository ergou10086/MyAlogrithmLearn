package 基础算法和其他.差分.subject.luoguP8228模块化核熔炉;

import javax.xml.xpath.XPathExpression;
import java.util.Scanner;

public class Main {
    // xyz的表示
    // 把六边形的原点移动到左下那个角落
    // 这样往原来的x方向走一格就是使得x+1，y-1
    // 往y方向走一格就是使得y+1
    // 往z方向走一格就是x-1
    // 这样原来坐标系的(x, y, z)就是斜二维坐标系的(x - z + n, y - x + n)
    static final int N = 1608;
    static int n, m, d;
    static int[][] a = new int[N][N];
    static int[][] b = new int[N][N];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        d = 2 * n - 1;
        for (int i = 0; i < m; i++) {
            //  (xi,yi,zi)不超过 r 的所有点能量增加 ki。
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int z = scanner.nextInt();
            int r = scanner.nextInt();
            int k = scanner.nextInt();

            int xp = x - z + n;
            int yp = y - x + n;

            a[xp][Math.min(d, yp + r - 1)] -= k;
            a[Math.max(1, xp - r + 1)][Math.min(d, yp + r - 1)] += k;
            a[xp][Math.max(0, yp - r)] -= k;
            a[Math.min(d + 1, xp + r)][Math.max(0, yp - r)] += k;

            int xq = x - z + n;
            int yq = y - z + n;

            b[xq][Math.min(d, yq + r - 1)] += k;
            b[Math.min(d + 1, xq + r)][Math.min(d, yq + r - 1)] -= k;
            b[xq][Math.max(0, yq - r)] += k;
            b[Math.max(1, xq - r + 1)][Math.max(0, yq - r)] -= k;
        }

        for (int i = 1; i <= d; i++) {
            for (int j = 1; j <= d; j++) {
                a[i][j] += a[i - 1][j];
            }
        }

        for (int i = 1; i <= d; i++) {
            for (int j = d; j >= 1; j--) {
                a[i][j] += a[i][j + 1];
            }
        }

        for (int i = 1; i <= d; i++) {
            for (int j = 1; j <= d; j++) {
                b[i][j] += b[i - 1][j];
            }
        }

        for (int i = 1; i <= d; i++) {
            for (int j = d; j >= 1; j--) {
                b[i][j] += b[i][j + 1];
            }
        }

        for (int i = -n + 1; i <= n - 1; i++) {
            for (int j = n - 1; j >= 1 - n + Math.abs(i); j--) {
                int x = 0, y = j, z = 0;
                if (i < 0) {
                    z -= i;
                } else {
                    x = i;
                }
                System.out.print((a[x - z + n][y - x + n] + b[x - z + n][y - z + n]) + " ");
            }
        }

        scanner.close();
    }
}
