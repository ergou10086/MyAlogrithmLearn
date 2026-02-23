package 比赛.LeetCode周赛.第490场周赛;

import java.util.HashMap;
import java.util.Map;

class Solution4 {
    // 每个位置3种处理方式, val 乘以 nums[i],val 除以 nums[i]，不变
    // 可以看到，每一位最大是6，最小是1，那么任何由 1-6 乘除得到的有理数有一定规律
    // 1不共享，2贡献2^1,3贡献3^1，4贡献2^2,5贡献5^1,6贡献2^1*3^1
    // 其中有三个质因数
    // 一眼DP，开搜
    // 算了，Map 记忆化吧

    // 质因数分解打表
    private final int[][] factor = {
            {0, 0, 0}, // 0 (占位)
            {0, 0, 0}, // 1
            {1, 0, 0}, // 2
            {0, 1, 0}, // 3
            {2, 0, 0}, // 4
            {0, 0, 1}, // 5
            {1, 1, 0}  // 6
    };

    // 缓存，index为组合，Long为方案数
    private Map<String, Long> ways;

    /**
     * 记忆化搜索
     * @param idx 当前处理的 nums 索引
     * @param e2, e3, e5 当前 val 拥有的质指数
     * @param t2, t3, t5 目标 k 的质指数
     */
    private long search(int[] nums, int idx, int e2, int e3, int e5, int t2, int t3, int t5) {
        // 所有数字处理完了，检查
        if(idx == nums.length){
            return (e2 == t2 && e3 == t3 && e5 == t5) ? 1 : 0;
        }

        // 序列化当前状态，索引+2+3+5，作为记忆化key
        String state = idx + "_" + e2 + "_" + e3 + "_" + e5;
        if(ways.containsKey(state)){
            // 存在这个状态，直接返回之前的结果
            return ways.get(state);
        }

        // 处理当前位
        int val = nums[idx];
        int f2 = factor[val][0];
        int f3 = factor[val][1];
        int f5 = factor[val][2];

        long count = 0;

        // 乘以 nums[idx]，指数相加
        count += search(nums, idx + 1, e2 + f2, e3 + f3, e5 + f5, t2, t3, t5);

        // 除以 nums[idx]，指数相减
        count += search(nums, idx + 1, e2 - f2, e3 - f3, e5 - f5, t2, t3, t5);

        // 保持不变
        count += search(nums, idx + 1, e2, e3, e5, t2, t3, t5);

        ways.put(state, count);
        return count;
    }

    public int countSequences(int[] nums, long k) {
        ways = new HashMap<>();

        // 依次提取 k 中的 2, 3, 5
        long tempk = k;
        int num2 = 0, num3 = 0, num5 = 0;
        while(tempk > 0 && tempk % 2 == 0) { num2++; tempk /= 2; }
        while(tempk > 0 && tempk % 3 == 0) { num3++; tempk /= 3; }
        while(tempk > 0 && tempk % 5 == 0) { num5++; tempk /= 5; }

        // 如果 k 还剩下不能被 2,3,5 整除的部分且不为1，说明 nums 无法通过乘除得到 k
        if (tempk != 1){
            return 0;
        }

        // 开搜
        return (int)search(nums, 0, 0, 0, 0, num2, num3, num5);
    }
}

public class Q4 {
}
