package 基础算法和其他.二分.subject.P10909_蓝桥杯2024国B_立定跳远;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

// 二分

class Solutions{
    private int n, m, mid;
    private int[] dist, qzh;

    private boolean check(int a) {
        int cnt = 0;
        // 可以考虑，在使用爆发技能时候，相当于是在设置时候多设置一个检查点
        for(int i = 1; i <= n; i++) {
            int di = qzh[i];
            while(di > a) {
                di -= a;
                cnt++;
            }
        }

        return cnt <= m + 1;
    }


    public Solutions() {
        FastReader sc = new FastReader();
        n = sc.nextInt();
        m = sc.nextInt();
        dist = new int[n + 1];
        qzh = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            dist[i] = sc.nextInt();
            qzh[i] = dist[i] - dist[i - 1];
        }

        int l = 1, r = dist[n];

        while(l < r) {
            mid = (l + r) / 2;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        System.out.println(l);
    }


    class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        String next() {
            while(!st.hasMoreElements()) {
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