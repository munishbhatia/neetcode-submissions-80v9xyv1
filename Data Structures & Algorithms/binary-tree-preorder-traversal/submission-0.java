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
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root == null) return new ArrayList<>();

        final List<Integer> preorder = new ArrayList<>();
        preorderHelper(root, preorder);

        return preorder;
    }

    private void preorderHelper(TreeNode node, List<Integer> result) {
        if(node == null) return;

        result.add(node.val);
        preorderHelper(node.left, result);
        preorderHelper(node.right, result);
    }
}