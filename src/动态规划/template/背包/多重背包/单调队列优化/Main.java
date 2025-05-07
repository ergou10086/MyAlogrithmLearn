package 动态规划.template.背包.多重背包.单调队列优化;

import java.util.Scanner;

// dp数组是按类更新的，可以按照f[0..m]按体积v的余数拆分成v个类
// f[j]  f[j+v]  f[j+2v]....f[j+kv]   其中j是v的余数，0<=j<=v-1
// 可以看出每一类中的差是v的整数倍（放物品总数一个个放）
// f[j]就是由前面不超过数量s的同类值递推得到的，这就相当于从前面宽度为s的窗口挑选最大值来更新当前值
// 所以，用单调队列来维护最大值，从而把更新f[j]的次数缩减为1次

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 种类n,容量W
        int n = scanner.nextInt();
        int W = scanner.nextInt();
        final int N = 40005;
        int[] q = new int[N];
        int[] f = new int[N];    // f[k] = 窗口中的最大价值 + 还能放入物品的价值
        int[] g = new int[N];   // 备份f的数组


        while (n-- > 0) {
            System.arraycopy(f, 0, g, 0, f.length);  // 备份f数组到g
            int v = scanner.nextInt(); // 物品价值
            int w = scanner.nextInt(); // 物品重量
            int s = scanner.nextInt(); // 物品数量

            // 枚举余数w(w个类)
            for (int j = 0; j < w; j++) {
                int h = 1, t = 0; // 单调队列的头和尾
                // 对每个类使用单调队列
                // 对于每个余数 j，枚举所有 k = j,j+w,j+2w（同类中枚举）
                for (int k = j; k <= W; k += w) {
                    // q[h]不在窗口[k-s*w, k-w]内，维护单调队列，移除超出范围的元素
                    // 因为在背包容量 k 的限制下，减去最多能选的s个物品乘重量为左边界，当然右边界就是选择 1 个当前物品
                    if (h <= t && q[h] < k - s * w) h++;
                    // 窗口是在g数组上滑动的
                    // (k - q[t]) / w是从状态 q[t] 到状态 k 还可以放入多少个当前物品
                    // 如果新值g[k]比队尾g[q[t]]状态更优，转换过去
                    while (h <= t && g[k] >= g[q[t]] + (k - q[t]) / w * v) t--;
                    q[++t] = k;   // 队头出队
                    // f[k] = 窗口中的最大价值 + 还能放入物品的价值
                    f[k] = g[q[h]] + (k - q[h]) / w * v; // 更新f[k]

                }
            }
        }

        System.out.println(f[W]); // 输出最大价值
        scanner.close();
    }
}
