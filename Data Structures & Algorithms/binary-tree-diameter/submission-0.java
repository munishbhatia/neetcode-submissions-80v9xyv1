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
    int diameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) {
            return diameter;
        }

        height(root);
        return diameter;
    }

    private int height(TreeNode node) {
        if(node == null || isLeaf(node)) {
            return 0;
        }

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        int dia = Math.max(Math.max(leftHeight, rightHeight) + 1, (leftHeight + rightHeight + (node.left != null ? 1 : 0) + (node.right != null ? 1 : 0)));
        diameter = Math.max(diameter, dia);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    private boolean isLeaf(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }
}


// Diameter at a given node is either:
// 1. Height of the left subtree
// 2. Height of the right subtree
// 3. Height of left and right subtrees + 1
// If 3 is used as the diameter, it cannot be included
//     in the diameter calculation at the parent node
// So keep calculating the height of a node's left and right subtrees
// And updating a global diameter as max(max(leftHeight, rightHeight) + 1, leftHeight + rightHeight + 2)