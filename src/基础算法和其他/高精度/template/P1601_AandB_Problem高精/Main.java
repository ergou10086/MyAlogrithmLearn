package 基础算法和其他.高精度.template.P1601_AandB_Problem高精;

// 传说之我在java学高精度
// 实际上，高精度计算就是用数组存储和模拟运算
// 步骤
// 高精度数字利用字符串读入
// 把字符串翻转存入两个整型数组A,B
// 从低位到高位，累加，进位，存余
// 数组C从高位到低位依次输出

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

class HighPrecisionAdd {
    private final int N = 505;  // 数组大小
    private int la, lb, lc;

    private void add(int[] a, int[] b, int[] c) {
        // 从最低位开始逐位相加
        for (int i = 1; i <= lc; i++) {
            c[i] += a[i] + b[i];  // 累加
            c[i + 1] += c[i] / 10; // 进位到更高一位
            c[i] %= 10;            // 当前位的值取模10
        }
        if (c[lc + 1] != 0) lc++; // 如果最高位有进位，更新 lc
    }

    public HighPrecisionAdd() {
        // 从输入读取两个大数
        String as, bs;
        FastReader sc = new FastReader();
        as = sc.next();
        bs = sc.next();
        la = as.length();  // 数字a的长度
        lb = bs.length();  // 数字b的长度
        lc = Math.max(la, lb);  // 结果的最大长度，取a和b的最大长度

        // 反转输入的字符串并存入数组a和b，便于按位加法
        int[] a = new int[N];
        for (int i = 0; i < la; i++) a[i + 1] = as.charAt(la - i - 1) - '0';  // 注意：这里从1开始存储
        int[] b = new int[N];
        for (int i = 0; i < lb; i++) b[i + 1] = bs.charAt(lb - i - 1) - '0';  // 同上
        int[] c = new int[N];  // 存放结果的数组

        // 执行加法操作
        add(a, b, c);

        // 输出结果，去掉前导零
        boolean leadingZero = true;
        for (int i = lc; i >= 1; i--) {
            if (c[i] != 0 || !leadingZero) {
                System.out.print(c[i]);
                leadingZero = false;
            }
        }
        if (leadingZero) System.out.print(0);  // 如果结果是0
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        new HighPrecisionAdd();
    }
}

