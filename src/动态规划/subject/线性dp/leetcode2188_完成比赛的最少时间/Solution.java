package 动态规划.subject.线性dp.leetcode2188_完成比赛的最少时间;

import java.util.Arrays;

/**
 * 同一种轮胎连续跑第x圈的耗时是 fi * ri^(x-1)（fi是首圈耗时，ri是每圈倍数），所以适时换轮胎更划算。
 * 线性的动态规划
 * 完成第i圈的最少时间” 仅与 “完成第j圈的最少时间”（j < i）相关，且圈数从 1 到numLaps是线性递增的
 * 由于轮胎连续使用的耗时是指数增长，当k超过一定值后，“换胎 + 用新胎跑” 的成本会远低于 “继续用旧胎跑”
 * 对每种轮胎，预计算它连续跑 1~max_k 圈的总耗时
 * 再基于所有轮胎的预计算结果，得到 “连续跑k圈的最小总耗时”（记为min_continuous[k]），即所有轮胎中连续跑k圈的耗时最小值
 * 定义dp[i]：完成前i圈的最少总时间。
 */
class Solution {
    public int minimumFinishTime(int[][] tires, int changeTime, int numLaps) {
        // 计算连续跑k圈的最小总耗时
        int maxK = 30;
        long[] minContinuous = new long[maxK + 1];
        Arrays.fill(minContinuous, Long.MAX_VALUE);

        for(int[] tire : tires){
            int fi =  tire[0];
            int ri = tire[1];
            long currentSum = 0; // 该轮胎连续跑k圈的总耗时
            long currentLap = fi; // 该轮胎当前圈的耗时（第1圈fi，第2圈fi*ri...）

            for(int kp = 1; kp <= maxK; kp++){
                // 若当前圈耗时已超过换胎+新胎1圈的耗时，继续跑不划算，提前退出
                if (currentLap > changeTime + fi) {
                    break;
                }

                currentSum += currentLap;

                // 更新连续跑k圈的最小总耗时
                if (currentSum < minContinuous[kp]) {
                    minContinuous[kp] = currentSum;
                }

                // 防止溢出（若下一圈耗时超过Long.MAX_VALUE，提前退出）
                if (currentLap > Long.MAX_VALUE / ri) {
                    break;
                }

                currentLap *= ri;
            }
        }

        // 线性动态规划：dp[i]表示完成前i圈的最少时间
        long[] dp = new long[numLaps + 1];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0; // 基准状态：0圈耗时0

        for(int i = 1; i <= numLaps; i++){
            // 遍历该轮胎连续跑的圈数k
            int maxPossibleK = Math.min(i, maxK);

            for(int k = 1; k <= maxPossibleK; k++){
                if (minContinuous[k] == Long.MAX_VALUE) {
                    continue;
                }

                // 计算当前方案的总耗时：dp[i-k] + 换胎时间（若需要） + 连续k圈耗时
                long pre = dp[i - k];
                if (pre == Long.MAX_VALUE) {
                    continue; // 前序状态无效，跳过
                }
                long cost = pre + (i - k == 0 ? 0 : changeTime) + minContinuous[k];

                if(cost < dp[i]){
                    dp[i] = cost;
                }
            }
        }
        return (int)dp[numLaps];
    }
}
