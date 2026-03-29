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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return null;
        }
        if(root.val == p.val || root.val == q.val) {
            return root;
        }
        if(p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if(p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }
}

// This is a binary SEARCH tree
// So if a node is equal to one of the values, that is the LCA
// If both the values are smaller than the current nodes value, then find LCA in the left subtree
// If both the values are greater than the current nodes' value, find LCA in the right subtree
// If one value is smaller and the other value is greater than the nodes' value, then node is the LCA

// This solution assumes that both the nodes exist in the BST
// An update to get rid of that assumption would be to continue finding the other node in the tree to make sure it exists
