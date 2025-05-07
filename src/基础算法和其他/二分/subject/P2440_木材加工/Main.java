package 基础算法和其他.二分.subject.P2440_木材加工;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 快读
class FastReader {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer("");

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

class MoodProcessing{
    // n根木头，分成k段
    private long n, k;
    private long[] mood = new long[1000005];

    private boolean check(long x){
        long apk = 0;
        for(int i = 1; i <= n; i++){
            apk += mood[i] / x;
        }
        return apk >= k;
    }

    public MoodProcessing(){
        FastReader sc = new FastReader();
        n = sc.nextInt();
        k = sc.nextInt();
        for (int i = 1; i <= n; i++) mood[i] = sc.nextInt();

        long l = 0, r = 100000001;

        while (l + 1 < r){
            long mid = (l + r) >> 1;
            if(check(mid)){
                // 段多，每一段应该短一点，左移
                l = mid;
            }else{
                // 段少，每一段应该长一点，右移
                r = mid;
            }
        }
        System.out.println(l);
    }
}


public class Main {
    public static void main(String[] args) throws IOException {
        new MoodProcessing();
    }
}
