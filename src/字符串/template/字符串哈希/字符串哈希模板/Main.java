package 字符串.template.字符串哈希.字符串哈希模板;

// 字符串哈希就是把不同的字符映射成不同的整数
// 把字符串p映射成一个p进制数字，对于一个长度为n的字符串s
// 这样定义hash函数 h(s) = s[i] * p^n-i mod M
// 例如字符串abc，其哈希函数值是 ap^2 + bp + c
// 如何解决哈希碰撞
// 解决哈希碰撞，设置p与m的值，保证p与m互质

// 求一个字符串的哈希值相当于求前缀和
// h[i] = h[i-] * p + s[i], h[0] = 0；
// 求其字串的哈希值就是相当于区间和
// h[l, r] = h[r] - h[l-1] * p^(r-l+1)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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

class StringHash{
    private static final int N = 1000010;
    private static final long P = 131;
    private static long[] p = new long[N];    // 记录P的几次幂
    private static long[] h = new long[N];    // 哈希函数表
    private static String s;

    // 预处理hash函数的前缀和
    private static void init(int n) {
        p[0] = 1;
        h[0] = 0;
        for (int i = 1; i <= n; i++) {
            p[i] = p[i - 1] * P;
            h[i] = h[i - 1] * P + s.charAt(i - 1);
        }
    }

    // 计算hash[l,r]的函数值
    private static long get(int l, int r) {
        return h[r] - h[l - 1] * p[r - l + 1];
    }

    // 判断两子串是否相同
    private static boolean substr(int l1, int r1, int l2, int r2) {
        return get(l1, r1) == get(l2, r2);
    }

    public StringHash(){
        FastReader scanner = new FastReader();
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        s = scanner.next();

        init(n);
        while (m-- > 0) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            int d = scanner.nextInt();
            if (substr(a, b, c, d)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        new StringHash();
    }
}
