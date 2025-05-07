package 基础算法和其他.模拟.P10905_蓝桥杯2024省C_回文字符串;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

// 什么情况下能回文，加入l q b
// 本来就回文，和只包含l q b 肯定能变成回文
// 其中一部分回文，另一部分只包含l q b
// 把左边或者右边连续的l q b 拿走，判断剩下的回不回文就行
// 而且只能在开头放
// 那么左边的l q b比其右边的长度长，就不行了
// llqgmscllqbb gmgqlq

class Solutions {
    // 获取字符串开头连续的 'l'、'q' 或 'b' 的长度
    private int getLenFront(String s) {
        int sum = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == 'l' || c == 'q' || c == 'b') {
                sum++;
            } else {
                break;
            }
        }
        return sum;
    }

    // 获取字符串结尾连续的 'l'、'q' 或 'b' 的长度
    public static int getLenBack(String s) {
        int sum = 0;
        int len = s.length();
        for (int i = len - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == 'l' || c == 'q' || c == 'b') {
                sum++;
            } else {
                break;
            }
        }
        return sum;
    }

    public Solutions() {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        while (n-- > 0) {
            String s = sc.next();
            int len = s.length();
            int lenF = getLenFront(s);
            int lenB = getLenBack(s);

            // 全为特殊字符而且前后l q b连续的长度相等
            if (lenF == lenB && lenF == len) {
                System.out.println("Yes");
                continue;
            }

            boolean flag = true;
            if(lenF <= lenB){
                for (int j = 0; j < len - lenB + lenF - 1; j++) {
                    // 中间不回文，不行
                    if (s.charAt(j) != s.charAt(len - lenB + lenF - 1 - j)) {
                        System.out.println("No");
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    System.out.println("Yes");
                }
            // 那么左边的l q b比其右边的长度长，就不行了
            } else {
                System.out.println("No");
            }

            /*
            // 左右铲车开铲
            while (p2 > 1 && s.charAt(p2 - 1) == 'q' || s.charAt(p2 - 1) == 'l' || s.charAt(p2 - 1) == 'b') p2--;
            while (p1 < p2 - 1 && s.charAt(p1 + 1) == 'l' || s.charAt(p1 + 1) == 'q' || s.charAt(p1 + 1) == 'b') p1++;

            // 把这段字符串截取下来判断是否回文
            for (int i = p1 + 1, j = p2 - 1; i < j; i++, j--) {
                if (s.charAt(i) != s.charAt(j)) {
                    flag = false;
                    break;
                }
            }

            if (p2 + p1 - 1 > n){
                flag = false;
            }else{

            }
             */
        }
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
                    // TODO 自动生成的 catch 块
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
