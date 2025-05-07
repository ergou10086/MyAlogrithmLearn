package 基础算法和其他.贪心.subject.P12161_蓝桥杯2025省JavaB_研发资源分配;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions2();
    }
}
// 1 2 3    2 3 1  1+2-3=0
// 1 4 3 2   2 1 4 3  1-2+3+4=6
// 1 3 2 4   2 4 3 1  1+2+3-4=2
// 2 3 1 5 4   3 4 2 1 5   1+2+3-4+5
// 经过了几个案例的打表，我们发现，总是存在这样一种排列，会使得资源份额的差值最大
// 那么就是 对面最多只能拿到一天的分数，而且拿到的只能是等级最高的那天

class Solutions2{
    public Solutions2(){
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        int[] b = new int[n + 1];
        for (int i = 1; i <= n; i++) b[i] = sc.nextInt();

        if (n == 1) {
            System.out.println(0);
            return;
        }

        long res = 0;
        for (int i = 1; i <= n; i++) {
            // 对面出动了最大的等级，我们使用最下等的去换取，这天的不要
            if(b[i] == n){
                res -= i;
            }else{
                // 别的情况我们都能凑出一种排列比他大，因为我们用最小的把他最大的给抵出去了
                res += i;
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

        public String next() {
            while (!st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
