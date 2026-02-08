package 动态规划.template.单调队列.连续子序列的最大和.P10059_Choose;

import java.io.*;
import java.util.StringTokenizer;

public class Main2 {
    private static int[] a;
    private static int n, k;

    // 使用数组模拟双端队列，避免ArrayDeque的额外开销
    private static int[] maxQ, minQ;
    private static int maxHead, maxTail, minHead, minTail;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        // 预分配队列数组
        maxQ = new int[100005];
        minQ = new int[100005];

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            a = new int[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }

            // 计算最优的X
            int X = computeMinDiff(n - k + 1);

            // 二分查找最小的L
            int left = 1, right = n - k + 1;
            int bestL = n - k + 1;

            while (left <= right) {
                int mid = (left + right) >> 1;

                if (hasKWindowsWithDiff(mid, X)) {
                    bestL = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            bw.write(String.valueOf(X));
            bw.write(' ');
            bw.write(String.valueOf(bestL));
            bw.write('\n');
        }

        bw.flush();
    }

    /**
     * 计算所有长度为windowLen的窗口中，极差的最小值
     */
    private static int computeMinDiff(int windowLen) {
        maxHead = maxTail = 0;
        minHead = minTail = 0;

        int minDiff = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            // 移除超出窗口范围的元素（最大值队列）
            while (maxHead < maxTail && maxQ[maxHead] < i - windowLen + 1) {
                maxHead++;
            }

            // 移除超出窗口范围的元素（最小值队列）
            while (minHead < minTail && minQ[minHead] < i - windowLen + 1) {
                minHead++;
            }

            // 维护单调递减队列（最大值）
            while (maxHead < maxTail && a[i] > a[maxQ[maxTail - 1]]) {
                maxTail--;
            }
            maxQ[maxTail++] = i;

            // 维护单调递增队列（最小值）
            while (minHead < minTail && a[i] < a[minQ[minTail - 1]]) {
                minTail--;
            }
            minQ[minTail++] = i;

            // 当窗口形成后，计算极差
            if (i >= windowLen) {
                int diff = a[maxQ[maxHead]] - a[minQ[minHead]];
                if (diff < minDiff) {
                    minDiff = diff;
                }
            }
        }

        return minDiff;
    }

    /**
     * 检查是否存在至少k个长度为windowLen且极差≥minDiff的窗口
     * 找到k个就立即返回
     */
    private static boolean hasKWindowsWithDiff(int windowLen, int minDiff) {
        maxHead = maxTail = 0;
        minHead = minTail = 0;

        int count = 0;

        for (int i = 1; i <= n; i++) {
            // 移除超出窗口范围的元素（最大值队列）
            while (maxHead < maxTail && maxQ[maxHead] < i - windowLen + 1) {
                maxHead++;
            }

            // 移除超出窗口范围的元素（最小值队列）
            while (minHead < minTail && minQ[minHead] < i - windowLen + 1) {
                minHead++;
            }

            // 维护单调递减队列（最大值）
            while (maxHead < maxTail && a[i] > a[maxQ[maxTail - 1]]) {
                maxTail--;
            }
            maxQ[maxTail++] = i;

            // 维护单调递增队列（最小值）
            while (minHead < minTail && a[i] < a[minQ[minTail - 1]]) {
                minTail--;
            }
            minQ[minTail++] = i;

            // 当窗口形成后，检查极差
            if (i >= windowLen) {
                if (a[maxQ[maxHead]] - a[minQ[minHead]] >= minDiff) {
                    count++;
                    if (count >= k) {
                        return true;  // 提前退出
                    }
                }
            }
        }

        return false;
    }
}
