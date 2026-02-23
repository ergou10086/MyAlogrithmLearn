package 比赛.LeetCode周赛.第490场周赛;

import java.util.Arrays;

class Solution2 {
    public boolean isDigitorialPermutation(int n) {
        String temp = String.valueOf(n);

        // 预计算阶乘
        // 数字排列也不影响阶乘之和吧
        int[] steps = new int[10];
        steps[0] = 1;
        for (int i = 1; i < 10; i++) {
            steps[i] = steps[i - 1] * i;
        }

        // 处理数字的频率
        int num = n;
        int[] countN = new int[10];
        int sum = 0;

        while (num > 0) {
            int digit = num % 10;
            countN[digit]++;
            sum += steps[digit];   // 计算阶乘之和
            num /= 10;
        }

        // 判断sum能不能排列成n
        // 也就是说，sum的数字组成必须和n一样
        int[] countSum = new int[10];
        int tempSum = sum;
        int digitInSum = 0;    // 阶乘之和有几位
        while (tempSum > 0) {
            int digit = tempSum % 10;
            countSum[digit]++;
            tempSum /= 10;
            digitInSum++;
        }

        // 验证位数是否匹配
        int digitInN = 0;
        for (int c : countN){
            digitInN += c;
        }
        if(digitInN != digitInSum){
            return false;
        }

        return Arrays.equals(countN, countSum);
    }
}

public class Q2 {

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        // 示例1：输入145，输出true
        System.out.println(solution.isDigitorialPermutation(145)); // true
        // 示例2：输入10，输出false
        System.out.println(solution.isDigitorialPermutation(10)); // false
        // 额外测试：输入451（145的排列），输出true
        System.out.println(solution.isDigitorialPermutation(451)); // true
    }
}
