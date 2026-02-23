package 搜索.DFS.subject.LeetCode1022_从根到叶的二进制数之和;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
        this.left = left;
        this.right = right;
     }
}



class Solution {
    public int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }

    /**
     * 深搜
     */
    private int dfs(TreeNode node, int sum) {
        // 空节点，返回 0
        if (node == null){
            return 0;
        }

        // 左移一位 + 当前节点值
        sum = (sum << 1) | node.val;

        // 叶子节点，返回这条路径表示的数字
        if (node.left == null && node.right == null){
            return sum;
        }

        // 非叶子，继续递归
        return dfs(node.left, sum) + dfs(node.right, sum);
    }
}

public class Main {
}
