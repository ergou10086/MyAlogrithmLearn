package 基础算法和其他.哈希.LeetCode3719_最长平衡子数组I;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class Solution {
    // 扫一遍吧，应该能过平方
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        int res = 0;

        for (int i = 0; i < n; i++) {
            Set<Integer> odds = new HashSet<>();   // 存不同的奇数
            Set<Integer> evens = new HashSet<>();  // 存不同的偶数

            for (int j = i; j < n; j++) {
                int p = nums[j];
                if ((p & 1) == 1){
                    odds.add(nums[j]);
                }else{
                    evens.add(nums[j]);
                }

                // 检查是否平衡
                if (odds.size() == evens.size()) {
                    res = Math.max(res, j - i + 1);
                }
            }
        }

        return res;
    }
}


public class Main {

}
