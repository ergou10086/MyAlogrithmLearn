package 动态规划.subject.线性dp.P1279_子串距离;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String A = br.readLine();
        String B = br.readLine();
        int K = Integer.parseInt(br.readLine());

        int n = A.length();
        int m = B.length();

        int[][] dp = new int[n + 1][m + 1];

        // 初始化边界
        // A的前i个字符对齐空串B（全空格）
        for (int i = 1; i <= n; i++) dp[i][0] = dp[i - 1][0] + K;

        // B的前j个字符对齐空串A（全空格）
        for (int i = 1; i <= m; i++) dp[0][i] = dp[0][i - 1] + K;

        for (int i = 1; i <= n; i++) {
            char aChar = A.charAt(i - 1);
            for (int j = 1; j <= m; j++) {
                char bChar = B.charAt(j - 1);

                // 情况1：A[i-1]与B[j-1]对齐（非空格对非空格）
                int costChar = dp[i - 1][j - 1] + Math.abs(aChar - bChar);

                // 情况2：A[i-1]与空格对齐（给B插空格）
                int costASpace = dp[i - 1][j] + K;

                // 情况3：空格与B[j-1]对齐（给A插空格）
                int costBSpace = dp[i][j - 1] + K;

                dp[i][j] = Math.min(Math.min(costChar, costASpace), costBSpace);
            }
        }

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(dp[n][m]);
        pw.flush();
    }
}
