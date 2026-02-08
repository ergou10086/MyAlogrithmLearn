package 基础算法和其他.双指针.subject.LeetCode189_轮转数组;

class Solution {
    // 可以发现是倒数第k个开始遍历到头，然后从下标0开始遍历
    public void rotate(int[] nums, int k) {
        int p = nums.length;
        if (p == 0) {
            return;
        }

        k = k % p;

        int[] params = new int[p];
        int sk = 0;

        // 复制后 k 个元素（原数组的 [p-k, p-1]）
        for (int i = p - k; i < p; i++) {
            params[sk++] = nums[i];
        }

        // 复制前 p - k 个元素（原数组的 [0, p-k-1]）
        for (int i = 0; i < p - k; i++) {
            params[sk++] = nums[i];
        }

        for (int i = 0; i < p; i++) {
            nums[i] = params[i];
        }
    }

    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) return;
        k %= n;

        int[] temp = new int[n];
        // 后 k 个移到前面
        System.arraycopy(nums, n - k, temp, 0, k);
        // 前 n - k 个移到后面
        System.arraycopy(nums, 0, temp, k, n - k);
        // 复制回去
        System.arraycopy(temp, 0, nums, 0, n);
    }
}

public class Main {
}
