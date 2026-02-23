package 比赛.LeetCode周赛.第490场周赛;

class Solution {
    public int scoreDifference(int[] nums) {
        // 主动玩家
        int score_z = 0;
        // 被动玩家
        int score_b = 0;

        // 初始时，第一位玩家是主动玩家
        boolean flag = true;

        for (int i = 0; i < nums.length; i++) {
            // 如果 nums[i] 是奇数，交换角色
            if ((nums[i] & 1) == 1){
                // 角色交换
                flag = !flag;
            }

            // 每第 6 场比赛（索引 5, 11, 17...），交换角色
            // 规则 1 和 规则 2 可能同时发生，导致两次交换抵消了
            if ((i + 1) % 6 == 0){
                flag = !flag;
            }

            if(flag){
                score_z += nums[i];
            }else{
                score_b += nums[i];
            }
        }
        return score_z - score_b;
    }
}

public class Q1 {
}
