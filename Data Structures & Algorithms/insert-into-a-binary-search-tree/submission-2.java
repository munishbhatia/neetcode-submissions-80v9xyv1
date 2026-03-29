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
    //RECURSIVE SOLUTION
    // public TreeNode insertIntoBST(TreeNode root, int val) {
    //     if(root == null) return new TreeNode(val);

    //     if(val < root.val) root.left = insertIntoBST(root.left, val);
    //     else if(val > root.val) root.right = insertIntoBST(root.right, val);
        
    //     return root;
    // }

    //ITERATIVE SOLUTION
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null) return new TreeNode(val);

        TreeNode prev = null;
        TreeNode curr = root;
        

        while(curr != null) {
            prev = curr;
            if(val < curr.val) curr = curr.left;
            else curr = curr.right;
        }

        curr = new TreeNode(val);

        if(val < prev.val) prev.left = curr;
        else prev.right = curr;

        return root;
    }
}