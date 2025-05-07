package 字符串.template.Manacher.luogu_P3805_manacher算法;

// 马拉车算法只有一个用途
// 可以在0(n)时间内求出一个字符串中的最长回文串

// 预处理
// 回文字符串按照长度的奇偶性，可以分为奇回文和偶回文，一般情况下需要分这两种情况来寻找回文
// 马拉车算法进行了简化，在每一个字符的左右两边都加上一个特殊字符（该字符不同于在字符串的任一字符），保证回文子串中只存在奇回文

// 算法流程
// 计算完前i-1个d函数，维护盒子[l,r]
// 如果i <= r,在盒子内，i的对称点为r - i + l
//     若d[r - i + l] < r - i + 1, 则d[i] = d[i - l + 1](对称点的回文半径也就是i的回文半径比本身的长度小，则直接转移)
//     若d[r - i + l] >= r - i + 1,则d[i] = r - i + 1，从r + 1往后暴力枚举（比盒子外长的部分需要暴力解决）
// 如果i > r,在盒子外，则从i开始暴力枚举
// 求出d[i]后，如果i + d[i] - 1 > r, 更新盒子 l = i - d[i] + 1, r = i + d[i] - 1（d[i]求出后需要看新的回文半径长度大于原本盒子右端点，需要更新）


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N = 11000012;
    static int n;
    static char[] a = new char[N << 1];
    static int[] d = new int[N << 1];  //回文半径函数，以i为中心的最长回文串的长度的一半，是回文半径

    // 使用BufferedReader读取输入字符串的方法
    static String readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

    static void get_d() {
        d[1] = 1;
        for (int i = 2, l = 0, r = 1; i <= n; i++) {
            // 如果i在当前已处理的回文串范围内（即i <= r）,两种情况取最小值
            if (i <= r) {
                d[i] = Math.min(d[r - i + l], r - i + 1);
            }

            // 优化扩展回文半径的循环，避免不必要的比较
            int maxExpand = Math.min(i, n - i + 1); // 最多能扩展的长度
            while (d[i] < maxExpand && a[i - d[i]] == a[i + d[i]]) {
                d[i]++;
            }

            // 如果新的回文串右端点超过了当前已处理的回文串右端点r，更新边界
            if (i + d[i] - 1 > r) {
                l = i - d[i] + 1;
                r = i + d[i] - 1;
            }
        }
    }


    public static void main(String[] args) throws IOException {
        String input = readInput();

        // 预处理
        // 回文字符串按照长度的奇偶性，可以分为奇回文和偶回文，一般情况下需要分这两种情况来寻找回文
        // 马拉车算法进行了简化，在每一个字符的左右两边都加上一个特殊字符（该字符不同于在字符串的任一字符），保证回文子串中只存在奇回文
        int cnt = 0;
        a[0] = '$';    // 哨兵
        a[++cnt] = '#';
        for (int i = 0; i < input.length(); i++) {
            a[++cnt] = input.charAt(i);
            a[++cnt] = '#';
        }
        n = cnt;


        get_d();

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, d[i]);
        }

        System.out.println(ans - 1);

    }
}