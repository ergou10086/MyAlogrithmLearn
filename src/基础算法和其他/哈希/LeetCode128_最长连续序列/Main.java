package 基础算法和其他.哈希.LeetCode128_最长连续序列;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int res = 0;

        for (int num : numSet) {
            // 开始扫
            if (!numSet.contains(num - 1)) {
                int current = num;
                int currentLength = 1;

                while (numSet.contains(current + 1)) {
                    current++;
                    currentLength++;
                }

                res = Math.max(res, currentLength);
            }
        }

        return res;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println((new Solution()).longestConsecutive(new int[]{0,3,7,2,5,8,4,6,0,1}));
    }
}
