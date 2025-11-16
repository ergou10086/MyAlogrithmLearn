package 基础算法和其他.贪心.subject.leetcode1864_构成交替字符串需要的最小交换次数;

public class Solution {
    public int minSwaps(String s) {
        int num0 = 0, num1 = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') num0++;
            else num1++;
        }

        // 若0和1的数量差超过1，无法构成交替串
        if (Math.abs(num0 - num1) > 1) {
            return -1;
        }

        // 根据数量判断可能的模式
        if (num0 > num1) {
            // 只能是0开头的模式（0101...）
            return countSwaps(s, '0');
        } else if (num1 > num0) {
            // 只能是1开头的模式（1010...）
            return countSwaps(s, '1');
        } else {
            // 两种模式都可能，取最小值
            return Math.min(countSwaps(s, '0'), countSwaps(s, '1'));
        }
    }

    // 计算构成以target开头的交替串所需的交换次数
    private int countSwaps(String s, char target) {
        int swaps = 0;
        for (int i = 0; i < s.length(); i++) {
            // 偶数位（0,2,4...）应该是target，奇数位应该是另一个字符
            char expected = (i % 2 == 0) ? target : (target == '0' ? '1' : '0');
            if (s.charAt(i) != expected) {
                swaps++;
            }
        }
        // 交换次数是错位数量的一半（因为每次交换修复两个错位）
        return swaps / 2;
    }
}