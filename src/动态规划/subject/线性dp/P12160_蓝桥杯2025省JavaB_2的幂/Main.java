package 动态规划.subject.线性dp.P12160_蓝桥杯2025省JavaB_2的幂;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions{
    // 计算一个数是 2 的几次幂
    private int countTwos(int num) {
        int count = 0;
        while(num % 2 == 0 && num > 0){
            count++;
            num /= 2;
        }
        return count;
    }

    public Solutions(){
        FastReader fs = new FastReader();
        int n = fs.nextInt();
        int k = fs.nextInt();    // 目标 2 的幂次
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) a[i] = fs.nextInt();

        // 初始状态 2 的幂次和
        int total = 0;
        for(int num: a){
            total += countTwos(num);
        }

        if(total >= k){
            System.out.println(0);
            return;
        }

        // 需要新增的2的幂次
        int rp = k - total;  // 差2的rp次幂
        int[] dp = new int[rp + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;   // 初始化dp[0]为0，意思是1为初态，不加

        // 处理每个数
        // 数组中所有元素的乘积是 2 的 k 次方倍数。
        // 要做到这一点，数组中所有元素的 2 的幂次之和必须至少为 k。
        for (int i = 0; i < n; i++) {
            int num = a[i];
            int p = countTwos(num);    // 该数为2的几次幂

            // 临时 DP 数组，避免同一组重复选择
            int[] tempDp = dp.clone();

            // 生成增量选项，开到30差不多
            for (int q = p + 1; q <= 30; q++) {
                long mp = (long)Math.pow(2, q);    // 2^q
                if(mp > 100000) break;   // 注意最大值限制
                // 不小于 num 且是 2^q 倍数的最小数 sp
                long sp = ((long)num + mp - 1) / mp * mp;
                // 新数能被 2 的 q 次方整除的最小增量cost 和 能够获得的 2 的幂次增量 gain。
                int cost = (int)(sp - num);
                int gain = countTwos((int)sp) - p;
                if (gain <= 0) continue;

                // 更新dp
                for(int j = rp; j >= gain; j--){
                    // dp[j - gain] 不是无穷大，存在一种方式可以到达 j - gain 的 2 的幂次增量
                    if(dp[j - gain] != Integer.MAX_VALUE){
                        tempDp[j] = Math.min(tempDp[j], dp[j - gain] + cost);
                    }
                }
            }
        }
        System.out.println(dp[rp] == Integer.MAX_VALUE? -1 : dp[rp]);
    }

    class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        public String next() {
            while (!st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
