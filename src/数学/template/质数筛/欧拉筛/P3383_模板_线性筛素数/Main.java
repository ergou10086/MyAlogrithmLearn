package 数学.template.质数筛.欧拉筛.P3383_模板_线性筛素数;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// 算法流程：
// 假设要筛出n以内的素数。我们先把所有数标记为素数。从2到n枚举i，因为i是从小到大的，如果i没有被比它小的数标记为合数，那i就是素数
// 然后用这个选出的素数i来筛选后面的数，枚举已经筛出来的素数prime[j]，，标记i * prime[j]为合数，当i是prime[j]的倍数时退出循环，i++。

class PrimeQuery {
    private boolean[] isPrime = new boolean[100000010];   // 标记素数
    private int[] Prime = new int[6000010];   // 现在已经筛出的素数列表

    private void GetPrime(int n) {
        // 初始化先全部标记为素数
        Arrays.fill(isPrime, false);
        // 0和1都不是素数
        isPrime[0] = isPrime[1] = true;

        int t = 0;    // 已经筛出的素数个数
        Prime[t++] = 2;  // 把2放进素数表

        // i从2循环到n（外层循环）
        for (int i = 2; i <= n; i++) {
            // 如果i没有被前面的数筛掉，则i是素数
            if (!isPrime[i]) {   // 没筛掉
                Prime[t++] = i;   // i成为下一个素数
            }
            // 筛掉i的素数倍，即i的prime[j]倍，标记为非素数
            // j循环枚举现在已经筛出的素数（内层循环）
            for (int j = 0; j < t && i * Prime[j] <= n; j++) {
                // 倍数标记为合数，也就是i用prime[j]把i * prime[j]筛掉了
                isPrime[i * Prime[j]] = true;
                // 如果i整除prime[j]，退出循环
                // 这句话成立，意味着，i可以表示为Prime[j]乘以某个整数
                // 对于后续更大的素数Prime[j + 1]等，如果继续用它们去筛除i * Prime[j + 1]等这些数，是没有必要的。
                // 对于任何大于Prime[j]的素数Prime[m]（m > j），i * Prime[m]这个合数的最小质因数应该是Prime[j]而不是Prime[m]
                // 假设i = 6（此时Prime[j] = 2，满足6 % 2 == 0），那么对于更大的素数如Prime[j + 1] = 3，虽然可以计算出6 * 3 = 18这个合数
                // 但18的最小质因数是2，应该在处理2的倍数时就被筛除（也就是当处理到i = 9（9 % 3 == 0），j对应Prime[j] = 3时，通过9 * 2 = 18来筛除18才是符合让每个合数被其最小质因数筛除一次的原则
                if (i % Prime[j] == 0) break;
            }
        }
    }

    public PrimeQuery() {
        FastReader reader = new FastReader();
        int n = reader.nextInt();     // 上限，即筛出<=n的素数
        int q = reader.nextInt();
        GetPrime(n);

        // 使用BufferedWriter进行快速输出
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringBuilder output = new StringBuilder();
            while (q-- > 0) {
                int k = reader.nextInt();
                output.append(Prime[k]).append("\n");
            }
            writer.write(output.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class FastReader {
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
}

public class Main {
    public static void main(String[] args) {
        new PrimeQuery();
    }
}

