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
    public boolean isBalanced(TreeNode root) {
        if(root == null) {
            return true;
        }

        boolean[] res = new boolean[]{true};
        isBalancedHelper(root, res);

        return res[0];
    }

    private int isBalancedHelper(TreeNode node, boolean[] res) {
        if(node == null) {
            return 0;
        }

        int leftHeight = isBalancedHelper(node.left, res);
        int rightHeight = isBalancedHelper(node.right, res);

        if(Math.abs(leftHeight - rightHeight) > 1) {
            res[0] = false;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }
}

//Check if node.left is balanced and get it's height
//Check if node.right is balanced and get it's height
//Check if leftHeight and rightHeight difference is at most 1
