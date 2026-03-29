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
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, -1001, 1001); //Used the limits based on the provided constraints
    }

    private boolean isValidBST(TreeNode node, int low, int high) {
        if(node == null) {
            return true;
        }
        if(node.val <= low || node.val >= high) {
            return false;
        }
        return isValidBST(node.left, low, node.val) && //max value in left subtree should be less than the curr node's val
            isValidBST(node.right, node.val, high); //min value in the right subtree should be more than the current node's val
    }
}

// Similar to Count Good Nodes in a tree 
// https://neetcode.io/problems/count-good-nodes-in-binary-tree
// where we kept passing the highest seen so far, we will have to pass the restricting values
// downstream and match against them
// For example 1 in the question, every node with value x in the left subtree of 1 (if it existed)
// should satisfy the constraint [INT_MIN < x < 1] and every node in the right subtree of 1 (if it existed)
// should satisfy the constraint [1 < x < 2] since it is in the right subtree of 1 and left subtree of 2

// If any node does not satisfy the above constraint, then the tree is not a valid BST
// If a given node satisfies this criteria, it's left and right subtree should also be valid BST
// for the tree to be a valid BST
