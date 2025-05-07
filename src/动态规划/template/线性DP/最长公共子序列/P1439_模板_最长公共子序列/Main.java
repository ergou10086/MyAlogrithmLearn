package 动态规划.template.线性DP.最长公共子序列.P1439_模板_最长公共子序列;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine();  // 消耗换行符
        String a = " " + scanner.nextLine();  // 字符串 a，前面加一个空格方便从 1 开始索引
        String b = " " + scanner.nextLine();  // 字符串 b，前面加一个空格方便从 1 开始索引

        // 状态dp[i][j] a数组前i个，b数组前j个的最长公共子序列长度
        int[][] f = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    f[i][j] = f[i - 1][j - 1] + 1;    // 如果字符相等，长度加 1
                }else{
                    // 如果字符不等，考虑继承，就是两个数组回到上一个位置的时候，谁的最长公共子序列长，用谁的
                    f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                }
            }
        }

        // 输出结果
        System.out.println(f[n][m]);

        scanner.close();
    }
}
