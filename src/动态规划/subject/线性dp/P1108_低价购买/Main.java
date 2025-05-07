package 动态规划.subject.线性dp.P1108_低价购买;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solutions();
    }
}

// 每次你购买一支股票，你必须用低于你上次购买它的价格购买它。买的次数越多越好！
// 连续下降子序列？
// f[i]存的是第 i 天最长下降子序列的长度
// cnt[i]存的是以 i 结尾的最长下降子序列的种类数

class Solutions {
    public Solutions() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out), true);

        int n = Integer.parseInt(st.nextToken());
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (!st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }
            a[i] = Integer.parseInt(st.nextToken());
        }

        int[] f = new int[n + 1];
        int[] cnt = new int[n + 1];
        int res = 0;    // 种类数
        int maxL = -1;   // 最长下降子序列的长度
        for (int i = 1; i <= n; i++) {
            f[i] = 1;
            // 统计最长下降子序列
            for (int j = 1; j < i; j++) {
                if (a[j] > a[i]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }

            maxL = Math.max(maxL, f[i]);

            for (int j = 1; j < i; j++) {
                // 如果一个数列的第一个数与另一个数列的第一个数相同，而且最长不下降长度相等
                // 可以认作相等，而序列长度可以相接的时候后面又不一样了，所以把这位清空掉
                if (f[i] == f[j] && a[i] == a[j]) {
                    cnt[j] = 0;
                // 序列长度可以直接相接
                }else if(f[i] == f[j] + 1 && a[i] < a[j]){
                    cnt[i] += cnt[j];
                }
            }
            // 如果当前的数是目前为止最大的,则是初始方案
            if (cnt[i] == 0) cnt[i] = 1;
        }

        for(int i = 1; i <= n; i++){
            if(f[i] == maxL) res += cnt[i];
        }
        System.out.println(maxL + " " + res);
    }
}
