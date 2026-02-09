package 基础算法和其他.前缀和.subject.LeetCode560_和为K的子数组;

import java.util.HashMap;
import java.util.Map;

class Solution {
    // 子数组是数组中元素的连续非空序列
    // 那就可以扫，然后前缀和
    public int subarraySum(int[] nums, int k) {
        int s = nums.length;
        int curSum = 0;      // 前缀和
        int count = 0;

        // 哈希表：key = 前缀和，value = 该前缀和出现的次数
        Map<Integer, Integer> pref = new HashMap<>();
        pref.put(0, 1);

        for (int num : nums) {
            curSum += num;

            // 查找
            if (pref.containsKey(curSum - k)) {
                count += pref.get(curSum - k);
            }

            // 更新
            pref.put(curSum, pref.getOrDefault(curSum, 0) + 1);
        }

        return count;
    }
}

public class Main {
}
