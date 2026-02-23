package 基础算法和其他.位运算.LeetCode868_二进制间距;

// 题目何意味，不就是找两个1最大相距多远吗
class Solution {

    // 位运算
    public int binaryGap(int n) {
        int ans = 0;
        int first = -1;
        int current = 0;

        while (n > 0){
            // 判断是不是1
            if((n & 1) == 1){
                if(first != -1){
                    ans = Math.max(ans, current - first);
                }
                first = current;
            }
            n = n >> 1;
            current = current + 1;
        }

        return ans;
    }

    // 双指针
    public int binaryGap2(int n) {
        // 记录上一个1的位置
        int left = -1;
        int ans = 0;

        String binary = Integer.toBinaryString(n);

        for(int right = 0; right < binary.length(); right++){
            if(binary.charAt(right) == '1'){
                if(left != -1){
                    ans = Math.max(ans, right - left);
                }
                left = right;
            }
        }

        return ans;
    }
}

public class Main {
}
