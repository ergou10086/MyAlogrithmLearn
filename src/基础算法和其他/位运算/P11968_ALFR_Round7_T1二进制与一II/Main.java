package 基础算法和其他.位运算.P11968_ALFR_Round7_T1二进制与一II;

// 给定一个整数 n 和一个整数 k，要求找到一个与 n 最接近的数 m
// 使得 m 的二进制表示中恰好有 k 个 1。输出 ∣n−m∣ 的最小值。

// 所以能找到的数就情况如下
// 比 n 小的、二进制表示中有 k 个 1 的最大数 a。
// 比 n 大的、二进制表示中有 k 个 1 的最小数 b。
// 最终答案是 min(n−a,b−n)

// 那么如何构造a，从最高位开始，找到n的二进制表示中第一个1，将其变为0，低位填充尽可能多的1
// 相对于，构造b，就是最低位开始，找到 nn 的二进制表示中第一个 0，将其变为 1，在高位尽可能少地填充 1

// 这里体现出贪心，在构造 a 和 b 时，尽量让 1 集中在低位（对于 a）或高位（对于 b），以保证 a 尽可能大、b 尽可能小。

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solution();
    }
}

class Solution {
    long[] a = new long[75];   // 取long避免转型
    private int d;

    // 计算 n 的二进制表示中 1 的个数
    private long sum(long x) {
        long ans = 0;
        while (x > 0) {
            ans += x % 2;
            x /= 2;
        }
        return ans;
    }

    public Solution() {
        FastReader sc = new FastReader();
        int T = sc.nextInt();
        while (T-- > 0) {
            long x = sc.nextLong();  // 使用 nextLong() 读取 long 类型
            int k = sc.nextInt();   // m恰有k个1
            // x的二进制中有k个1，相等
            if (sum(x) == k) {
                System.out.println(0);
                continue;
            }
            // 存x的二进制位到a[]
            d = 0;    // 位数
            long n = x;
            while (n > 0) {
                a[++d] = n % 2;
                n /= 2;
            }

            // 二进制位小于等于k，只能会比x小，那么就只需要构造最大全1就行
            if (d <= k) {
                System.out.println((1L << k) - 1 - x);
            } else {
                // 构造a，记录差
                long current = 0;   // 记录当前构造的数
                long one_num = 0;   // 已经使用的 1 的数量
                long ans = (1L << d) + (1L << (k - 1)) - 1 - x;  // 构造出一个最大值
                // 开始构造a和b，逆序从高位开始构造
                for (int i = d; i >= 1; i--) {
                    boolean yep = (x & (1L << (i - 1))) != 0;   // 检查第 i 位是否为 1
                    // 是1，为了让a比x小，需要变成0，然后在低位尽可能多地填充 1
                    if (yep) {
                        long tmp = current * 2; // 当前构造的数的左移
                        // 剩余位数不足
                        if (i - 1 < k - one_num || one_num > k)
                            continue;
                        // 将当前数左移 i−1 位，为填充低位的 1 腾出空间
                        tmp *= (1L << (i - 1));
                        // 先将低i-1位全填1，再根据把多用了的1给还原
                        tmp += (1L << (i - 1)) - 1 - ((1L << (i - 1 - (k - one_num))) - 1);
                        one_num++;    // 记录已经使用的 1 的数量
                        ans = Math.min(ans, x - tmp);
                    }
                    current = current * 2 + (yep ? 1 : 0);
                }

                // 构造b，记录差
                current = 0;
                one_num = 0;
                for (int i = d; i >= 1; i--) {
                    boolean yep = (x & (1L << (i - 1))) != 0;   // 检查第 i 位是否为 1
                    if (yep) {
                        one_num++;    // 这个1也得算进去
                    }else {
                        long tmp = (current << 1) + 1; // 将第 i 位变为 1
                        // 剩余的位不够填充 k 个 1，那么就是填完了
                        if (i - 1 < k - one_num - 1 || one_num >= k)
                            continue;
                        tmp *= (1L << (i - 1));   // 左移i-1位，为高位填1留出位置
                        if (k - one_num - 1 > 0)
                            tmp += (1L << (k - one_num - 1)) - 1; // 在高位填充 1
                        ans = Math.min(ans, tmp - n);
                    }
                    current = current * 2 + (yep ? 1 : 0);
                }
                System.out.println(ans);
            }
        }

    }

    private class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

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

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}
