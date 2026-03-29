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

class Solution {
    final int MIN_VAL = -1000000;
    public int maxPathSum(TreeNode root) {
        final int[] result = new int[]{MIN_VAL}; //Initialized to less than min value in the tree
        maxPathSum(root, result);
        return result[0];
    }

    private int maxPathSum(TreeNode node, int[] result) {
        if(node == null) {
            return MIN_VAL;
        }

        final int left = maxPathSum(node.left, result);
        final int right = maxPathSum(node.right, result);

        int maxCurr = Math.max(left + right + node.val,
            Math.max(left, right) + node.val
        );
        maxCurr = Math.max(maxCurr, node.val);
        
        result[0] = Math.max(result[0], maxCurr);

        return Math.max(node.val, Math.max(left, right) + node.val);
    }
}

//Max(left subtree, right subtree)
//Max(left + right + node, Max(left, right) + node, node)
//We return only Max(Max(left, right) + node, node)
//We'll never return left + right + node since we don't want to include node twice
