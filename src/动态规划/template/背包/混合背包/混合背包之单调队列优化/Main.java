package 动态规划.template.背包.混合背包.混合背包之单调队列优化;

// 根据s的值，分别处理三种背包，三个函数，然后多重背包可以单调队列优化

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
    static int n, m;
    static int[] f = new int[1010];
    static int[] g = new int[1010];
    static int[] q = new int[1010];

    // 01背包函数
    public static void ZeroOnePack(int v, int w){
        for(int j = m; j >= v; j--){
            f[j] = Math.max(f[j], f[j - v] + w);
        }
    }

    // 完全背包函数
    public static void CompletePack(int v, int w) {
        for(int j = v; j <= m; j++){
            f[j] = Math.max(f[j], f[j - v] + w);
        }
    }

    // 多重背包函数
    public static void MultiplePack(int v, int w, int s) {
        // 复制f数组到g数组
        System.arraycopy(f, 0, g, 0, f.length);
        for (int j = 0; j < v; j++) {
            // 使用Deque
            Deque<Integer> q = new ArrayDeque<>();
            // 分类
            for (int k = j; k <= m; k += v) {
                // 移除超出范围的元素
                while(!q.isEmpty() && q.peekFirst() < k - s * v) q.pollFirst();
                // 更新f[k], f[k] = 窗口中的最大价值 + 还能放入物品的价值
                if (!q.isEmpty()) {
                    f[k] = Math.max(g[k], g[q.peekFirst()] + (k - q.peekFirst()) / v * w);
                }
                // 窗口是在g数组上滑动的
                // (k - q[t]) / w是从状态 q[t] 到状态 k 还可以放入多少个当前物品
                // 如果新值g[k]比队尾g[q.peekFirst()]加上该类能够放入的件数 状态更优，转换过去
                while(!q.isEmpty() && g[k] >= g[q.peekFirst()] + (k - q.peekLast() / v * w))  q.pollLast();
                // 将当前元素加入队列
                q.offerLast(k);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();

        for (int i = 1; i <= n; i++) {
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            int s = scanner.nextInt();
            if (s == -1) {
                ZeroOnePack(v, w); // 01 背包
            } else if (s == 0) {
                CompletePack(v, w); // 完全背包
            } else {
                MultiplePack(v, w, s); // 多重背包
            }
        }
        System.out.println(f[m]);
        scanner.close();
    }
}
