package 动态规划.template.线性DP.编辑距离.P2758_编辑距离;

import java.util.Scanner;

public class Main {
    // 设 A 和 B 是两个字符串。我们要用最少的字符操作次数，将字符串 A 转换为字符串 B。这里所说的字符操作共有三种：
    //    删除一个字符；
    //    插入一个字符；
    //    将一个字符改为另一个字符。

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String a = scanner.next();
        String b = scanner.next();
        int la = a.length();
        int lb = b.length();

        // dp[i][j]表示从a[1...i]到b[1...j]的编辑距离
        // 若a[i] = b[j],则无需编辑，f[i][j] = f[i - 1][j - 1]
        // 若a[i] != b[j],需要考察修改
        // 修改：a[i]改为b[j]   f[i][j] = f[i - 1][j - 1] + 1
        // 插入:a[i]后插入b[j]，f[i][j] = f[i][j - 1] + 1，a的前i个字符，改造成b的前j-1个字符加插入的
        // 删除：删除a[i]，所以f[i][j] = f[i - 1][j] + 1
        int[][] f = new int[la + 1][lb + 1];

        // 初始化 f[i][0] 和 f[0][i]，表示将一个字符串转换为空字符串所需的操作次数。
        for (int i = 1; i <= la; i++) f[i][0] = i;
        for (int i = 1; i <= lb; i++) f[0][i] = i;

        for (int i = 1; i <= la; i++) {
            for (int j = 1; j <= lb; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    f[i][j] = f[i - 1][j - 1];
                } else {
                    f[i][j] = Math.min(Math.min(f[i - 1][j], f[i][j - 1]), f[i - 1][j - 1]) + 1;
                }
            }
        }

        System.out.println(f[la][lb]);
    }
}
