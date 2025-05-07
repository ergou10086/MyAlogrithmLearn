package 基础算法和其他.双指针.subject.P12257_蓝桥杯_2024_国_JavaB分组;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

// 分为尽可能多的小组，且满足每个小组中的同学分数的最大值至少是最小值的两倍
// 贪心
// 能分就分
// 双指针
// 先给数组排个序
// 前指针从前往后被动走动
// 后指针从后开始往前扫，如果出现了这个位置的前一个位置的数 不满足最大值至少是最小值的两倍 就把这两个数分成一组
// 但是最多只能有 2/n 组，把枚举的最大值指针放到 2/n + 1 的位置开始向前找就可以了

class Solutions {
    public Solutions(){
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        long[] a = new long[n + 1];
        for (int i = 1; i <= n; i++) a[i] = sc.nextLong();
        Arrays.sort(a,1,n + 1);
        int p1 = 1;
        int p2 = n / 2 + 1;

        long res = 0;
        while(p1 <= n / 2 && p2 <= n){
            if(a[p2] >= 2 * a[p1]){
                // 可以组成一组
                res++;
                p1++;
                p2++;
            }else{
                // p2太小，继续找更大的
                p2++;
            }
        }
        System.out.println(res);
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
        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
