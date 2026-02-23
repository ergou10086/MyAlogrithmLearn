package 基础算法和其他.双指针.template.LeetCode11_盛水最多的容器;

class Solution {
    // 两个位置，p1代表从当前位置往左边看的最高处，p2代表从当前位置往右边看的最高处
    public int maxArea(int[] height) {
        int n = height.length;
        // 初始化双指针分别指向数组两端
        int left = 0;
        int right = n - 1;
        int maxWater = 0;

        while (left < right){
            // 宽度是 right - left，高度取决于两端较短的那一个
            int curr = Math.min(height[left], height[right]) * (right - left);
            maxWater = Math.max(maxWater, curr);

            // 哪边短就移动哪边，短板效应，因为保留短板移动长板，面积只会因为宽度减小而变得更小
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxWater;
    }
}

public class Main {
}
