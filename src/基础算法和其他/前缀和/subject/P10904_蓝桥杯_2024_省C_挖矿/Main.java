package 基础算法和其他.前缀和.subject.P10904_蓝桥杯_2024_省C_挖矿;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 数轴上一共有 n 个矿洞，第 i 个矿洞的坐标为ai
// 也就是说需要加上0位置的矿洞
public class Main {
    // 前缀和？
    // 无非就三种情况
    // 左边走到头，右边走到头，左边走点右边走点
    public static void main(String[] args) {
        new Solutions();
    }

}

class Solutions {
    private static final int N = 2000006;
    private int n, m, ans;
    private int[] le = new int[N];
    private int[] re = new int[N];

    public Solutions() {
        FastReader sc = new FastReader();
        n = sc.nextInt();
        m = sc.nextInt();

        // 记录矿洞矿石分布
        for (int i = 1; i <= n; i++) {
            int x = sc.nextInt();
            if (x > 0)
                re[x]++;
            else if (x == 0)
                re[0] = le[0] = 1;
            else
                le[-x]++;
        }

        // 计算前缀和，求前i个位置一共多少矿石
        for (int i = 1; i <= N - 1; i++) {
            le[i] = le[i] + le[i - 1];
            re[i] = re[i] + re[i - 1];
        }

        // 一直往左走，一直往右走，和左边走x个，再去右边走m-2*x个（1<=x<=m/2）和相反
        for (int i = 0; i <= m; i++) {
            // 此时的情况只能是一直走到一个方向的头了
            if(i * 2 > m){
                ans = Math.max(ans, le[i] + re[0]);
                ans = Math.max(ans, re[i] + le[0]);
            }else{
                ans = Math.max(ans, le[i] + re[m - 2 * i]);
                ans = Math.max(ans, re[i] + le[m - i * 2]);
            }
        }
        System.out.println(ans - le[0]);   // 因为多加了一块
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
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
