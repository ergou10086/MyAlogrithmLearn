package 基础算法和其他.哈希.LeetCode3713_最长平衡子串I;

import java.util.HashMap;
import java.util.HashSet;

class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        int res = 0;
        for(int l = 0; l < n; l++){
            HashMap<Character,Integer> map = new HashMap<>();
            for(int r = l; r < n; r++){
                if(!map.containsKey(s.charAt(r))){
                    map.put(s.charAt(r), 1);
                }else{
                    map.put(s.charAt(r), map.get(s.charAt(r)) + 1);
                }
                if(isBalanced(map)){
                    res = Math.max(res, r - l + 1);
                }
            }
        }

        return res;
    }

    private boolean isBalanced(HashMap<Character, Integer> freqMap) {
        return new HashSet<>(freqMap.values()).size() == 1;
    }
}

public class Main {
}
