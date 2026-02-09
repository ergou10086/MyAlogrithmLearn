package 数据结构.线性数据结构.单调队列.subject.滑动窗口.LeetCode438_找到字符串中所有字母异位词;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {
    // 从一个位置开始扫，扫p串的长度，用哈希记录字母的位置，要是和s对的上，就记录这个位置
    public List<Integer> findAnagrams(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        List<Integer> ans = new ArrayList<Integer>();

        if (sLen < pLen) {
            return ans;
        }

        HashMap<Character, Integer> map_p = new HashMap<>();
        for (int i = 0; i < pLen; i++) {
            char c = p.charAt(i);
            map_p.put(c, map_p.getOrDefault(c, 0) + 1);
        }

        // 滑动窗口初始化
        HashMap<Character, Integer> map_s = new HashMap<>();
        for (int i = 0; i < pLen; i++) {
            char c = s.charAt(i);
            map_s.put(c, map_s.getOrDefault(c, 0) + 1);
        }

        if (map_s.equals(map_p)) {
            ans.add(0);
        }

        // 从 i = 1 开始，窗口为 [i, i + pLen - 1]
        // 每次窗口右移后，比较 window 和 need 是否相等
        for (int i = 1; i <= sLen - pLen; i++) {
            char leftChar = s.charAt(i - 1), rightChar = s.charAt(i + pLen - 1);
            map_s.put(leftChar, map_s.get(leftChar) - 1);
            map_s.put(rightChar, map_s.getOrDefault(rightChar, 0) + 1);

            if (map_s.get(leftChar) == 0) {
                map_s.remove(leftChar); // 频次为0就删掉，避免影响 equals 比较
            }

            // 检查当前窗口是否匹配
            if (map_s.equals(map_p)) {
                ans.add(i);
            }
        }

        return ans;
    }
}

public class Main {
}
