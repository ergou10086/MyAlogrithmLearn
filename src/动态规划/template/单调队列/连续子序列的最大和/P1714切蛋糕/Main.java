package 动态规划.template.单调队列.连续子序列的最大和.P1714切蛋糕;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();   // n块蛋糕
        int m = scanner.nextInt();   // 窗口大小
        int[] arr = new int[n + 1];
        int[] qzh = new int[n + 1];  // 前缀和

        for (int i = 1; i <= n; i++) arr[i] = scanner.nextInt();
        for (int i = 1; i <= n; i++) {
            qzh[i] = qzh[i - 1] + arr[i];
        }

        Deque<Integer> deque = new ArrayDeque<>();
        int res = Integer.MIN_VALUE;

        for (int i = 0; i <= n; i++) {
            // 维护单调队列，确保队列中的元素是递增的
            while (!deque.isEmpty() && qzh[deque.peekLast()] >= qzh[i]) {
                deque.pollLast();    // 队尾出队
            }
            deque.offerLast(i);

            // 移除超出窗口范围的元素
            while (!deque.isEmpty() && deque.peekFirst() < i - m) {
                deque.pollFirst();
            }

            // 计算当前窗口的最大和
            if (!deque.isEmpty()) {
                res = Math.max(res, qzh[i] - qzh[deque.peekFirst()]);
            }
        }

        System.out.println(res);

        scanner.close();
    }
}