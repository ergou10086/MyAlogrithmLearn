package 数学.subject.P12256_蓝桥杯_2024_国_Java_B_数据库;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

// 共有 N 次 INSERT 和 DELETE 操作，保证 INSERT 一定在 DELETE 前
// 且有 DELETE 必定有 INSERT，而 INSERT 可以单独存在。求合法方案的顺序数。

class Solutions {
    public Solutions(){

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
