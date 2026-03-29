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
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(subRoot == null) {
            return true; //empty tree is a subtree of every tree
        }
        if(root == null) {
            return false; //Non-empty tree cannot be a subtree of an empty tree
        }
        
        return areTreesEqual(root, subRoot) ||
            isSubtree(root.left, subRoot) ||
            isSubtree(root.right, subRoot);
    }

    private boolean areTreesEqual(TreeNode x, TreeNode y) {
        if(x == null && y == null) {
            return true;
        }
        if(x == null || y == null) {
            return false;
        }
        return x.val == y.val &&
            areTreesEqual(x.left, y.left) &&
            areTreesEqual(x.right, y.right);
    }
}

//A tree y is a subtree of another tree x if:
//y.val == x.val && left subtree and right subtree of x and y match, or
//if x is a subtree of the left child of x, or
//if x is a subtree of the right child of x
