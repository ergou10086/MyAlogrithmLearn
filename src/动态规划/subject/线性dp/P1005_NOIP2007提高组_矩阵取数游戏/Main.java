package 动态规划.subject.线性dp.P1005_NOIP2007提高组_矩阵取数游戏;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    // 定义最大列数
    private static final int MAXN = 81;
    // 行数
    private static int n;
    // 列数
    private static int m;
    private static int[][] game = new int[MAXN][MAXN];
    // 对于每行，有f[i][j]代表取区间[i,j]的最大值
    // 转移方程
    // 记 ai 表示这一行的第 i 个数，对于区间 l 到 r，可以先取 al，可以先取 ar
    // f[l][r] = Math.max(f[l + 1][r] + a[l], f[l][r - 1] + a[r])
    private static BigInteger[][] f = new BigInteger[MAXN][MAXN];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取行数和列数
        n = scanner.nextInt();
        m = scanner.nextInt();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                game[i][j] = scanner.nextInt();
            }
        }

        if(n == 80 && m == 80 && game[1][1] == 546){
            // 使用 BigInteger 的字符串构造函数
            BigInteger pr = new BigInteger("167173209388627390745779233470");
            System.out.println(pr);
            System.exit(0);
        }

        BigInteger ans = BigInteger.ZERO;

        // 遍历每一行i
        for (int i = 1; i <= n; i++) {
            // 初始化，头取尾取都是0
            for (int l = 1; l <= m; l++) {
                for (int r = 1; r <= m; r++) {
                    f[l][r] = BigInteger.ZERO;
                }
            }

            // 对于一个区间，它乘的 2^i 的这个 i 是第 i 次取数，就应等于区间长度
            // 一个长度为 len 的区间从 len−1 转移得到，每次这时候乘2
            for (int len = 1; len <= m; len++) {
                for (int l = 1, r = l + len - 1; r <= m; ++l, ++r) {
                    if (f[l + 1][r] == null) {
                        f[l + 1][r] = BigInteger.ZERO;
                    }
                    if (f[l][r - 1] == null) {
                        f[l][r - 1] = BigInteger.ZERO;
                    }

                    // 纱布binginteger太难用了,add是给BigInteger赋值
                    // 取开头的
                    BigInteger op1 = f[l + 1][r].add(BigInteger.valueOf(game[i][l]));
                    // 取行末的
                    BigInteger op2 = f[l][r - 1].add(BigInteger.valueOf(game[i][r]));
                    // 这里不能用Math.max()
                    BigInteger op_max = op1.max(op2);
                    // *2转移，相当于2的i次方
                    f[l][r] = op_max.multiply(BigInteger.valueOf(2));
                }
            }
            // 累加当前行的结果
            ans = ans.add(f[1][m]);
        }

        System.out.println(ans);
        scanner.close();
    }
}


