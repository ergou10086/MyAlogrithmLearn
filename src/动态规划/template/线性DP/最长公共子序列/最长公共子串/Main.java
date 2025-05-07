package 动态规划.template.线性DP.最长公共子序列.最长公共子串;

public class Main {
    public static void main(String[] args) {
        // 定义两个字符串
        String a = "BCCABCCB";
        String b = "AACCAB";

        // 定义 DP 数组
        int[][] f = new int[a.length() + 1][b.length() + 1];
        int ans = 0;

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    f[i][j] = f[i - 1][j - 1] + 1;   // 如果字符相等，长度加 1
                    ans = Math.max(ans, f[i][j]);   // 更新最大值
                }else {
                    f[i][j] = 0;  // 如果字符不等，重置为 0
                }
            }
        }

        // 输出结果
        System.out.println("ans=" + ans);


    }
}
