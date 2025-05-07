package 动态规划.template.背包.分组背包.P1064_NOIP2006提高组_金明的预算方案;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        // 五种情况
        // 不要，要主件，要主件+附件1，要主件+附件2，要主件+附件1+附件2
        new Solutions();
    }
}

class Solutions {

    static final int N = 33000;

    public Solutions() {
        FastReader sc = new FastReader();
        // 总钱数 n 和希望购买的物品个数 m。
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] mwei = new int[N];   // 主件重量
        int[] mval = new int[N];  // 主件价值
        int[][] fwei = new int[N][3];  // 附件重量
        int[][] fval = new int[N][3];  // 附件价值

        int[] f = new int[N];

        // 修正：循环条件改为 i <= m
        for (int i = 1; i <= m; i++) {
            // 第 i 件物品的价格、重要度以及它对应的的主件。
            int w = sc.nextInt();
            int p = sc.nextInt();
            int q = sc.nextInt();
            // 主件
            if (q == 0) {
                mwei[i] = w;
                mval[i] = w * p;
            } else {
                fwei[q][0]++;           // 附件个数
                fwei[q][fwei[q][0]] = w;    // fw附件重量
                fval[q][fwei[q][0]] = w * p;  // fv附件价值
            }
        }

        // 修正：循环条件改为 i <= m
        for (int i = 1; i <= m; i++) {    // 枚举物品
            for (int j = n; j >= mwei[i]; j--) {  // 枚举体积
                f[j] = Math.max(f[j], f[j - mwei[i]] + mval[i]);   // 只要主件
                if (j >= mwei[i] + fwei[i][1]) {   // 要主件+附件1
                    f[j] = Math.max(f[j], f[j - mwei[i] - fwei[i][1]] + mval[i] + fval[i][1]);
                }
                if (j >= mwei[i] + fwei[i][2]) {   // 要主件+附件2
                    f[j] = Math.max(f[j], f[j - mwei[i] - fwei[i][2]] + mval[i] + fval[i][2]);
                }
                if (j >= mwei[i] + fwei[i][1] + fwei[i][2]) {   // 要主件+附件1,2
                    f[j] = Math.max(f[j], f[j - mwei[i] - fwei[i][2] - fwei[i][1]] + mval[i] + fval[i][2] + fval[i][1]);
                }
            }
        }
        System.out.println(f[n]);
    }

    class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        String next() {
            while (!st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}