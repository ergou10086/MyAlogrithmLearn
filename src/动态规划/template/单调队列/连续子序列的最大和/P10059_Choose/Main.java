package 动态规划.template.单调队列.连续子序列的最大和.P10059_Choose;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    private static int[] a;
    private static int n, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            a = new int[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }

            // 二分查找最大的X
            int left = 0, right = getGlobalDiff();
            int bestX = 0, bestL = n;

            while (left <= right){
                int mid = left + (right - left) / 2;
                int[] res = check(mid);
                int minL = res[0];
                boolean feasible = res[1] == 1;

                if(feasible){
                    bestX = mid;
                    bestL = minL;
                    left = mid + 1;  // 继续搜索更大的X
                }else {
                    right = mid - 1;  // 搜索更小的X
                }
            }

            System.out.println(bestX + " " + bestL);
        }
    }

    // 计算全局极差
    private static int getGlobalDiff() {
        int minVal = Integer.MAX_VALUE, maxVal = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            minVal = Math.min(minVal, a[i]);
            maxVal = Math.max(maxVal, a[i]);
        }

        return maxVal - minVal;
    }


    // 检查X是否可行，返回[最小L, 是否可行]
    private static int[] check(int mid) {
        // maxLen[i]表示 以i结尾的 极差小于等于X的 最长窗口长度
        int[] maxLen = new int[n + 1];
        Deque<Integer> maxDeque = new ArrayDeque<>();
        Deque<Integer> minDeque = new ArrayDeque<>();
        int left = 1;

        // 遍历每个位置作为右边界
        for (int right = 1; right <= n; right++) {
            // 维护最大值队列
            while(!maxDeque.isEmpty() && a[right] >= a[maxDeque.peekLast()]) {
                maxDeque.pollLast();
            }
            // 保证队列头部始终是当前窗口的最大值索引
            maxDeque.offerLast(right);

            // 维护最小值队列
            while (!minDeque.isEmpty() && a[minDeque.peekLast()] >= a[right]) {
                minDeque.pollLast();
            }
            // 同理
            minDeque.offerLast(right);

            // 调整左边界，确保窗口极差小于等于X
            // 如果当前窗口的极差大了
            while(a[maxDeque.peekFirst()] - a[minDeque.peekFirst()] > mid){
                if (maxDeque.peekFirst() == left) maxDeque.pollFirst();
                if (minDeque.peekFirst() == left) minDeque.pollFirst();
                // 左边界右移，缩小窗口，直到极差小于等于X
                left++;
            }

            // 记录最长窗口长度
            maxLen[right] = right - left + 1;
        }

        // 寻找最小的L，使得可以选出k个长度为L的窗口
        int leftBound = 1, rightBound = n;
        int bestL = n;

        // 再次二分，列举L
        while (leftBound <= rightBound) {
            int midL = leftBound + (rightBound - leftBound) / 2;

            if (canSelectK(midL, maxLen)) {
                bestL = midL;
                rightBound = midL - 1;
            } else {
                leftBound = midL + 1;
            }
        }

        // 检查是否真的可行
        boolean feasible = canSelectK(bestL, maxLen);
        return new int[]{bestL, feasible ? 1 : 0};
    }


    // 判断是否可以选出k个长度为L的窗口
    private static boolean canSelectK(int L, int[] maxLen) {
        int count = 0;
        int pos = 0;

        while (pos <= n - L) {
            if (maxLen[pos + L] >= L) {
                count++;
                pos += L;
                if (count >= k) return true;
            } else {
                pos++;
            }
        }

        return count >= k;
    }
}
