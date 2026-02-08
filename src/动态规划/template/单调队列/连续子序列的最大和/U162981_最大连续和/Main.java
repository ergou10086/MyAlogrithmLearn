package 动态规划.template.单调队列.连续子序列的最大和.U162981_最大连续和;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line =  br.readLine().split(" ");

        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);

        int[] nums = new int[n];
        line = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(line[i]);
        }

        // 计算前缀和
        long[] preSum = new long[n + 1];
        for(int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }

        Deque<Integer> deque = new ArrayDeque<>();
        // 初始时加入prefixSum[0]的索引
        deque.offerLast(0);
        long maxSum = Long.MIN_VALUE;

        for(int i = 1; i <= n; i++) {
            // 移除窗口外的元素，保证子数组长度不超过m
            while(!deque.isEmpty() && deque.peekFirst() < i - m) {
                deque.pollFirst();
            }

            // 当前窗口的最大和 = prefixSum[i] - 队列头部的最小前缀和
            maxSum = Math.max(maxSum, preSum[i] - preSum[deque.peekFirst()]);

            // 维护单调递增队列：移除队列尾部比当前前缀和大的元素
            while(!deque.isEmpty() && preSum[i] <= preSum[deque.peekLast()]) {
                deque.pollLast();
            }

            // 新元素入队
            deque.offerLast(i);
        }

        System.out.println(maxSum);
    }
}
