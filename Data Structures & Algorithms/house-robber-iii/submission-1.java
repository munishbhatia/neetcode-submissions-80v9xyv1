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
    public int rob(TreeNode root) {
        return robHelper(root, new HashMap<>());
    }

    private int robHelper(TreeNode node, Map<TreeNode, Integer> memory) {
        if(node == null) return 0;

        if(memory.containsKey(node)) return memory.get(node);
        
        if(isLeaf(node)) {
            memory.put(node, node.val);
            return node.val;
        }

        int valWithNode = node.val + ((node.left != null) ? (robHelper(node.left.left, memory) + robHelper(node.left.right, memory)) : 0) 
                                    + ((node.right != null) ? (robHelper(node.right.left, memory) + robHelper(node.right.right, memory)) : 0);
        
        int valWithoutNode = robHelper(node.left, memory) + robHelper(node.right, memory);

        memory.put(node, (valWithNode > valWithoutNode) ? valWithNode : valWithoutNode);
        return memory.get(node);
    }

    private boolean isLeaf(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }
}