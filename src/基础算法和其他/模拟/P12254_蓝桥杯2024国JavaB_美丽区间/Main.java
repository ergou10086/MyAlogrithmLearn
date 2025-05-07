package 基础算法和其他.模拟.P12254_蓝桥杯2024国JavaB_美丽区间;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions{
    private final int N = 1000005;
    // 把美丽区间的左右端点预处理，然后二分查找
    private int gcd(int a, int b){
        if(b == 0) return a;
        return gcd(b, a % b);
    }

    public Solutions(){
        FastReader fs = new FastReader();
        PrintWriter pw = new PrintWriter(System.out);

        int k = fs.nextInt();
        int T = fs.nextInt();

        int[] a = new int[N];
        int[] l = new int[N];
        int[] r = new int[N];

        int maxp = -78648923;
        for (int i = 1; i <= T; i++) {
            a[i] = fs.nextInt();
            maxp = Math.max(maxp, a[i]);
        }

        l[1] = 1;   // 定义1
        int len = 0;

        // 定义2
        for(int i = 1; l[i] < maxp; i++){
            for(int j = l[i] + k; j <= maxp + k; j++){   // 定义3
                if(gcd(l[i], j) == 1){   // 定义5
                    r[i] = j;
                    l[i + 1] = r[i] + 1;     // 定义4
                    break;
                }
            }
            len = i;
        }

        for(int i = 1; i <= T; i++){
            int temp = Arrays.binarySearch(r, 1, len + 1, a[i]);
            // 如果没有找到，返回一个负值，表示目标值应该插入的位置，取负-1为正确插入值
            // 正好就是属于第几个美丽区间
            if (temp < 0) {
                temp = -temp - 1;
            }
            pw.println(temp);
        }
        pw.flush();
    }

    class FastReader{
        BufferedReader br;
        StringTokenizer st;
        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }
        String next(){
            while(!st.hasMoreElements()){
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }
        int nextInt(){
            return Integer.parseInt(next());
        }
    }
}
