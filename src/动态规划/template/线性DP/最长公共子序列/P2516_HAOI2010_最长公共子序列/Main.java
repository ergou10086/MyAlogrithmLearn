package 动态规划.template.线性DP.最长公共子序列.P2516_HAOI2010_最长公共子序列;

import java.util.Scanner;
import java.io.*;

public class Main {

    private static final int N = 5010;
    private static final int MOD = 100000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 读取两个字符串（自动忽略空格，直接读整行）
        String s1 = br.readLine().trim();
        String s2 = br.readLine().trim();

        // 处理字符串：去掉末尾的'.'，从索引1开始存储（方便DP边界处理）
        char[] a = new char[N];
        char[] b = new char[N];
        int n = 0, m = 0;

        // 填充 a 数组（第一个序列）
        for (char c : s1.toCharArray()) {
            if (c == '.') break;
            a[++n] = c; // a[1..n] 为有效字符
        }

        // 填充 b 数组（第二个序列）
        for (char c : s2.toCharArray()) {
            if (c == '.') break;
            b[++m] = c; // b[1..m] 为有效字符
        }

        // 滚动数组：f[0][j] 和 f[1][j] 交替存储当前行和上一行的LCS长度
        int[][] f = new int[2][N];
        // g[0][j] 和 g[1][j] 交替存储当前行和上一行的LCS个数
        int[][] g = new int[2][N];

        // 初始化边界：空序列与任何序列的LCS个数为1
        for (int k = 0; k <= m; k++) {
            g[0][k] = 1;
        }
        g[1][0] = 1;

        int u = 0; // 当前行标记（0或1），u^1 表示上一行
        for(int i = 1; i <= n; i++){
            // 切换行（0 <-> 1）
            u ^= 1;

            for(int j = 1; j <= m; j++){
                // 更新LCS长度（传统LCS逻辑）
                if(a[i] == b[j]){
                    f[u][j] = f[u ^ 1][j - 1] + 1;
                } else {
                    f[u][j] = Math.max(f[u ^ 1][j], f[u][j - 1]);
                }

                // 更新LCS个数（带容斥去重）
                g[u][j] = 0;

                // 情况1：字符匹配，累加左上角计数
                if(a[i] == b[j] && f[u][j] == f[u ^ 1][j - 1] + 1){
                    g[u][j] = (g[u][j] + g[u ^ 1][j - 1]) % MOD;
                }

                // 情况2：长度来自上方，累加上方计数
                if(f[u][j] == f[u ^ 1][j]){
                    g[u][j] = (g[u][j] + g[u ^ 1][j]) % MOD;
                }

                // 情况3：长度来自左方，累加左方计数
                if (f[u][j] == f[u][j - 1]) {
                    g[u][j] = (g[u][j] + g[u][j - 1]) % MOD;
                }

                // 减去重复计数的部分（避免负数）
                if (f[u][j] == f[u ^ 1][j - 1]) {
                    g[u][j] = (g[u][j] - g[u ^ 1][j - 1] + MOD) % MOD;
                }
            }
        }

        // 输出结果（使用 BufferedWriter 提升效率）
        bw.write(f[u][m] + "\n");
        bw.write(g[u][m] + "\n");
        // 刷新缓冲区（必须调用，否则可能不输出）
        bw.flush();

        // 关闭资源
        br.close();
        bw.close();
    }
}
