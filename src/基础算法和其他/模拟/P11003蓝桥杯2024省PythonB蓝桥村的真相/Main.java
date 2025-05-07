package 基础算法和其他.模拟.P11003蓝桥杯2024省PythonB蓝桥村的真相;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

// 分析规律
// 当一个人为 1 时，后面两个人只能为 01 或 10
//   是01的情况
//     1011 10110 101101 1011011 10110110
//   是10的情况
//     1101 11011 110110 1101101 11011011
// 当一个人为 0 时，后面两个人只能为 00 或 11​。
//   是00的情况时
//     0000 00000 000000 0000000   也就是说全假一定存在
//   是11的情况时候
//     0110 01101 011011 0110110

class Solutions{
    public Solutions() {
        FastReader sc = new FastReader();
        long T = sc.nextLong();

        while(T-- > 0) {
            long n = sc.nextLong();
            // 都是在n整除3的时候存在四种情况的规律
            if(n % 3 == 0) {
                System.out.println(2 * n);
                // 不整除的话只存在全0的规律
            }else {
                System.out.println(n);
            }
        }

    }


    class FastReader{
        BufferedReader bReader;
        StringTokenizer stringTokenizer;

        public FastReader(){
            bReader = new BufferedReader(new InputStreamReader(System.in));
            stringTokenizer = new StringTokenizer("");
        }

        String next() {
            try {
                stringTokenizer = new StringTokenizer(bReader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringTokenizer.nextToken();
        }

        Long nextLong() {
            return Long.parseLong(next());
        }
    }
}