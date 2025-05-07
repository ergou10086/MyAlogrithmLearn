package 字符串.template.字符串哈希.P3370_字符串哈希;

// 字符串哈希就是把不同的字符映射成不同的整数
// 把字符串p映射成一个p进制数字，对于一个长度为n的字符串s
// 这样定义hash函数 h(s) = s[i] * p^n-i mod M
// 例如字符串abc，其哈希函数值是 ap^2 + bp + c
// 如何解决哈希碰撞
// 解决哈希碰撞，设置p与m的值，保证p与m互质，这里没有使用m，是靠的溢出实现的取模

import java.io.*;
import java.util.*;

class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
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

class StringHashTemplate {
    private static final int N = 10010;
    private static final int P = 131; // 哈希基数
    private static long[] h = new long[N];

    // 计算串的哈希值，利用了前缀和
    public long calcHash(String s){
        h[0] = 0;
        for(int i = 0; i < s.length(); i++){
            h[i + 1] = h[i] * P + s.charAt(i);
        }
        return h[s.length()];
    }

    public StringHashTemplate(){
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        Set<Long> ans = new HashSet<>();

        for(int i = 0; i < n; i++){
            String s = sc.next();
            long hashValue = calcHash(s);
            ans.add(hashValue);
        }

        System.out.println(ans.size());
    }
}

public class Main {
    public static void main(String[] args) {
        new StringHashTemplate();
    }
}
