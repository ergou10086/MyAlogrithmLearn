package 基础算法和其他.哈希.LeetCode49_字母异位词分组;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // 24个字母的桶进行哈希，扫描每一个单词，把它出现的字母存进去对应的位置，然后扫后面的词汇中的每个单词，如果出现了扫描到的不是1的情况，就跳过，然后标记
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        // Map: 字母频次签名 -> 对应的异位词列表
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            // 创建26个字母的计数桶
            int[] count = new int[26];
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }

            // 将桶转换为唯一字符串作为 key
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                sb.append(count[i]);
                if (i < 25){
                    sb.append('#');
                }
            }
            String key = sb.toString();
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }
}

public class Main {
}
