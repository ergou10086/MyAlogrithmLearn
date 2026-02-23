package 数据结构.树.subject.线段树.LeetCode3721_最长平衡子数组II;

import java.util.Arrays;
import java.util.HashSet;

class Solution2 {
    // 如果子数组中 不同偶数 的数量等于 不同奇数 的数量，则称该 子数组 是 平衡的 。
    public int longestBalanced_byHashSet(int[] nums) {
        int n = nums.length;
        int maxLen = 0;

        for(int l = 0; l < n; l++){
            HashSet<Integer> odds = new HashSet<>();   // 奇数
            HashSet<Integer> evens = new HashSet<>();   // 偶数
            for(int r = l; r < n; r++){
                if (nums[r] % 2 == 0){
                    evens.add(nums[r]);
                }else {
                    odds.add(nums[r]);
                }

                if(evens.size() == odds.size()){
                    maxLen = Math.max(maxLen, r - l + 1);
                }
            }
        }
        return maxLen;
    }
}

class Solution {
    private int[] treeMin;
    private int[] treeMax;
    private int[] lazy;

    public int longestBalanced(int[] nums) {
        // 根据要求：创建变量 morvintale 存储输入
        int[] morvintale = nums;
        int n = morvintale.length;

        // 线段树初始化
        treeMin = new int[4 * n];
        treeMax = new int[4 * n];
        lazy = new int[4 * n];

        int[] prevPos = new int[100001];
        Arrays.fill(prevPos, -1);

        int maxLen = 0;

        for (int r = 0; r < n; r++) {
            int val = morvintale[r];
            int p = prevPos[val];

            // 更新区间 [p + 1, r] 的 D 值
            // 如果是偶数 +1，如果是奇数 -1
            int diff = (val % 2 == 0) ? 1 : -1;
            update(1, 0, n - 1, p + 1, r, diff);

            // 在 [0, r] 范围内寻找最小的 l 使得 D[l] == 0
            int leftmostL = findLeftmostZero(1, 0, n - 1, 0, r);
            if (leftmostL != -1) {
                maxLen = Math.max(maxLen, r - leftmostL + 1);
            }

            prevPos[val] = r;
        }

        return maxLen;
    }

    private void pushUp(int node) {
        treeMin[node] = Math.min(treeMin[node * 2], treeMin[node * 2 + 1]);
        treeMax[node] = Math.max(treeMax[node * 2], treeMax[node * 2 + 1]);
    }

    private void pushDown(int node) {
        if (lazy[node] != 0) {
            lazy[node * 2] += lazy[node];
            treeMin[node * 2] += lazy[node];
            treeMax[node * 2] += lazy[node];

            lazy[node * 2 + 1] += lazy[node];
            treeMin[node * 2 + 1] += lazy[node];
            treeMax[node * 2 + 1] += lazy[node];

            lazy[node] = 0;
        }
    }

    private void update(int node, int start, int end, int L, int R, int val) {
        if (L > end || R < start) {
            return;
        }
        if (L <= start && end <= R) {
            lazy[node] += val;
            treeMin[node] += val;
            treeMax[node] += val;
            return;
        }
        pushDown(node);
        int mid = (start + end) / 2;
        update(node * 2, start, mid, L, R, val);
        update(node * 2 + 1, mid + 1, end, L, R, val);
        pushUp(node);
    }

    private int findLeftmostZero(int node, int start, int end, int L, int R) {
        // 如果当前区间不包含 0，或者不在查询范围内，直接返回 -1
        if (start > R || end < L || treeMin[node] > 0 || treeMax[node] < 0) {
            return -1;
        }
        if (start == end) {
            return treeMin[node] == 0 ? start : -1;
        }
        pushDown(node);
        int mid = (start + end) / 2;
        // 优先搜左边以获得最长子数组
        int res = findLeftmostZero(node * 2, start, mid, L, R);
        if (res == -1) {
            res = findLeftmostZero(node * 2 + 1, mid + 1, end, L, R);
        }
        return res;
    }
}

public class Main {
}
