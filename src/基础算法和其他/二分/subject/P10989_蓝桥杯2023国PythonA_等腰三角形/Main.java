package 基础算法和其他.二分.subject.P10989_蓝桥杯2023国PythonA_等腰三角形;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.sql.rowset.JoinRowSet;

// 回忆等腰三角形的性质，至少有两边相等，且满足三角形不等式
// 等边三角形也是等腰三角形，也就是说我们序列里的数只能有两种，一种是x，一种是y，或者只有一种
// 排序后，选择分界点再计算x,y即可，可以按照贪心思路
// 将序列分为两部分：前 i 个数和后 n−i 个数。而这个位置一定是左右两部分的中位数
// 前 i 个数的目标值为 x，后 n−i 个数的目标值为 y。
// 通过枚举分界点 i，计算每种情况下的最小操作次数。


public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions{
    private final int N = 200007;
    private int n;
    private int[] num = new int[N];
    private int[] qzh = new int[N];   // 前缀和

    // 对于 [l,r]内所有数增加或减少到目标值所需操作次数
    private int opT1(int l, int r, int target) {
        int pul = qzh[r] - qzh[l - 1];  // 区间 [l,r] 内元素的当前和
        int len = r - l + 1;    // 区间长度
        return Math.abs(len * target - pul);
    }

    // 计算将区间 [l,r] 内的数调整为目标值所需的总操作次数，有加有减
    private int opT2(int l, int r, int target) {
        int mid = lowerBound(num, l, r + 1, target);
        return opT1(l, mid - 1, target) + opT1(mid, r, target);
    }

    // nmd java没有lower_bound，还得手写一个
    private int lowerBound(int[] a, int low, int high, int target) {
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (a[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public Solutions() {
        FastReader sc = new FastReader();
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) num[i] = sc.nextInt();
        // 排序满足二分，而且使得x更小
        Arrays.sort(num, 1, n + 1);

        // 这里用前缀和是等到计算贡献的时候更方便
        for (int i = 1; i <= n; i++) {
            qzh[i] = qzh[i - 1] + num[i];
        }

        int ans = opT2(2, n, num[(2 + n) / 2]);
        // 枚举分界点i
        for (int i = 2; i < n; i++) {
            // x 是前 i 个数的中位数,y 是后 n−i 个数的中位数，保证代价最小
            int x = num[(1 + i) / 2];
            int y = num[(n + i) / 2];
            // 如果 2x≤y，则需要调整 x 和 y，使得 2x>y
            if(2 * x <= y) {
                int d = y - 2 * x + 1;   // 需要调整的总量，需要将 xx 增加 l，同时将 y 减少 d−2l。
                int l = 0;
                int r = (d + 1) / 2;  // d−2*l ≥ 0，所以 l <= d/2向上取整
                // 三分
                while(l < r) {
                    int mid1 = l + (r - l) / 3;
                    int mid2 = r - (r - l) / 3;
                    // 计算调整量为 mid1和mid2 时的总操作次数
                    long c1 = opT2(1, i, x + mid1) + opT2(i + 1, n, y - Math.max(0, d - 2 * mid1));
                    long c2 = opT2(1, i, x + mid2) + opT2(i + 1, n, y - Math.max(0, d - 2 * mid2));

                    if(c1 <= c2) {
                        r = mid2 - 1;   // mid1更优
                    }else {
                        l = mid1 + 1;   // mid2更优
                    }
                }
                x += l;
                y -= Math.max(0, d - 2 * l);
            }
            ans = Math.min(ans, opT2(1, i, x) + opT2(i + 1, n, y));
        }
        System.out.println(ans);
    }

    class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        String next() {
            while(!st.hasMoreElements()) {
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}