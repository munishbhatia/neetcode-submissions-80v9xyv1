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
    public int goodNodes(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return goodNodes(root, Integer.MIN_VALUE);
    }

    private int goodNodes(TreeNode node, int maxSoFar) {
        if(node == null) {
            return 0;
        }
        return ((maxSoFar <= node.val) ? 1 : 0) +
            goodNodes(node.left, Math.max(maxSoFar, node.val)) + 
            goodNodes(node.right, Math.max(maxSoFar, node.val));
    }
}

// We basically need to keep track of the highest value seen so far on the path
// We'll have to track back the highest value as well when popping the call/recursion stack
// Root will always be a goof node
