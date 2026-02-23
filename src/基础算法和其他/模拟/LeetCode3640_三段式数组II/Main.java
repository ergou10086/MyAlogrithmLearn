package 基础算法和其他.模拟.LeetCode3640_三段式数组II;

class Solution {

    // 找一个和最大的三段式数组
    // 「严格递增 - 严格递减 - 严格递增」，一共三段，每一段至少要有两个数。
    // 第一段的范围为 [start,peak]  第二段的范围为 [peak,bottom]  第三段 [bottom,end]
    public long maxSumTrionic(int[] nums) {
        int len = nums.length;
        long ans = Long.MIN_VALUE;

        if (len < 6) {
            return Long.MIN_VALUE;
        }

        int i = 0;
        while (i < len) {
            // 第一段
            int start = i;
            while (i + 1 < len && nums[i] < nums[i + 1]) {
                i++;
            }
            // 此时 [start, i] 是递增段，长度 = i - start + 1
            // 只有1个元素，不满足
            if (i == start) {
                i++;
                continue;
            }
            int _1f = i;

            // 第二段
            int peak = i;
            while (i + 1 < len && nums[i] > nums[i + 1]) {
                i++;
            }
            // 此时 [peak, i] 是递减段，长度 = i - peak + 1
            if (i == peak || i == len - 1 || nums[i] == nums[i + 1]) {
                i = peak + 1;
                continue;
            }
            int _2f = i;

            // 第三段
            // 第三段只能从第二段的下一个位置开始找
            int bottom = _2f + 1;

        }
        return ans;
    }
}

public class Main {
}
