package 基础算法和其他.贪心.subject.P12159_蓝桥杯2025省JavaB_数组翻转;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions {

    public Solutions() {
        FastReader in = new FastReader();
        int n = in.nextInt();
        int[] a = new int[n];
        for (int a_i = 0; a_i < n; a_i++) {
            a[a_i] = in.nextInt();
        }
        // 桶，记录最大的两个连续值
        int[][] backet = new int[1000086][2];

        for(int i = 0; i < n; i++){
            int cp = 0;   // 出现次数
            cp++;
            // 发现了两个连续的相等的数
            while(i < n - 1 && a[i] == a[i + 1]){
                cp++;
                i++;   // 指针跳过相同的数
            }
            // 第一段出现的更长
            if(cp > backet[a[i]][0]){
                backet[a[i]][1] = backet[a[i]][0];
                backet[a[i]][0] = cp;
            // 第二段出现的更长
            }else if(cp > backet[a[i]][1]){
                backet[a[i]][1] = cp;
            }
        }

        long res = 0;
        for(int i = 0; i < 1000003; i++){
            // ai * 总段长 （backet[i][1] + backet[i][0]）
            long temp = (long) i * (backet[i][1] + backet[i][0]);
            if(temp > res){
                res = temp;
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
