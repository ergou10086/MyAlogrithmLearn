package 动态规划.subject.线性dp.P1006_NOIP2008提高组_传纸条;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 快读类
class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader() {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer("");
    }

    String next() {
        while (!st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }
}

// 快写类
class FastWriter {
    BufferedWriter bw;

    public FastWriter() {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    void write(int num) throws IOException {
        bw.write(num + " ");
    }

    void newLine() throws IOException {
        bw.newLine();
    }

    void flush() throws IOException {
        bw.flush();
    }
}

class Solution {
    // 用f[i][j][p][q]表示第一张纸条传到(i,j),第二张纸条传到(p,q)所累计下来的好心程度和
    // 第一张纸条向下传，第二张纸条向下传；
    // 第一张纸条向下传，第二张纸条向右传；
    // 第一张纸条向右传，第二张纸条向下传；
    // 第一张纸条向右传，第二张纸条向右传；
    // f[i][j][p][q]=max(f[i-1][j][p-1][q],f[i-1][j][p][q-1],f[i][j-1][p-1][q],f[i-1][j][p][q-1])+v[i][j]+v[p][q];

    private int remax(int i, int j, int k, int l) {
        int m = Math.max(i, j);
        int n = Math.max(k, l);
        return Math.max(m, n);
    }

    public Solution() throws IOException {
        int[][] a = new int[51][51];
        int[][][][] f = new int[51][51][51][51];
        FastReader sc = new FastReader();
        int m = sc.nextInt();
        int n = sc.nextInt();
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                a[i][j] = sc.nextInt();
            }
        }
        f[1][1][1][1] = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = i + 1; k <= m; k++) {
                    for (int l = 1; l < j; l++) {
                        f[i][j][k][l] = remax(
                                f[i][j - 1][k][l - 1],
                                f[i][j - 1][k - 1][l],
                                f[i - 1][j][k - 1][l],
                                f[i - 1][j][k][l - 1]
                        ) + a[i][j] + a[k][l];
                    }
                }
            }
        }

        System.out.println(f[m - 1][n][m][n - 1]);

    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution();
    }
}