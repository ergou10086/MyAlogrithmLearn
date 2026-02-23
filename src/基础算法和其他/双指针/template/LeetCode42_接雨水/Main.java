package 基础算法和其他.双指针.template.LeetCode42_接雨水;

class Solution {
    // 木桶效应
    // 对于数组中下标为 i 的位置，它能接多少水，取决于它左边最高的柱子，它右边最高的柱子和自身高度
    // 该位置的接水量公式为：（p[i] = max(0, min(leftMax[i], rightMax[i])- height[i])）
    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }

        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int totalWater = 0;

        while (left < right) {
            // 滚动更新左右两侧观察到的最大高度
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            // 哪一边小，算哪边
            if (leftMax < rightMax) {
                // 左边更低
                totalWater += (leftMax - height[left]);
                left++;
            }else{
                // 右边更低
                totalWater += (rightMax - height[right]);
                right--;
            }
        }

        return totalWater;
    }


    // 没优化之前，用数组预处理
    public int trap2(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }

        int n = height.length;
        int left = 0;
        int right = n - 1;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        int totalWater = 0;

        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        for (int i = 0; i < n; i++) {
            totalWater += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return totalWater;
    }
}

public class Main {

}
