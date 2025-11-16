package 动态规划.template.线性DP.编辑距离.leetcode72_编辑距离;

/**
 *
 */
public class Solution {
    public int minDistance(String word1, String word2) {
        // 加空格好解
        word1 = " " + word1;
        word2 = " " + word2;

        int l1 = word1.length();
        int l2 = word2.length();

        int[][] dp = new int[l1][l2];

        // 前i个字母，空串转化为该串需要改i次
        for(int i = 0; i < l1; i++){
            dp[i][0] = i;
        }

        for(int j = 0; j < l2; j++){
            dp[0][j] = j;
        }

        for (int i = 1; i < l1; i++) {
            for(int j = 1; j < l2; j++) {
                // 不用改，继承
                if(word1.charAt(i) == word2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }else{
                    // 取小的方案
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + 1);
                }
            }
        }

        return dp[l1-1][l2-1];
    }
}
