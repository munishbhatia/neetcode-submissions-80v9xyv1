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
        return robHelper(root, new HashMap<>())[0];
    }

    private int[] robHelper(TreeNode node, Map<TreeNode, int[]> memory) {
        if(node == null) return new int[]{0, 0};

        if(memory.containsKey(node)) return memory.get(node);
        
        if(isLeaf(node)) {
            memory.put(node, new int[]{node.val, 1});
            return memory.get(node);
        }

        int valWithNode = node.val + ((node.left != null) ? (robHelper(node.left.left, memory)[0] + robHelper(node.left.right, memory)[0]) : 0) 
                                    + ((node.right != null) ? (robHelper(node.right.left, memory)[0] + robHelper(node.right.right, memory)[0]) : 0);
        
        int valWithoutNode = robHelper(node.left, memory)[0] + robHelper(node.right, memory)[0];

        // if(valWithNode > valWithoutNode) System.out.println("Included " + node.val + " for a total of " + valWithNode);
        // if(valWithoutNode > valWithNode) System.out.println("Excluded " + node.val + " for a total of " + valWithoutNode);

        if(valWithNode > valWithoutNode) {
            memory.put(node, new int[]{valWithNode, 1});
            return memory.get(node);
        }
        
        memory.put(node, new int[]{valWithoutNode, 0});
        return memory.get(node);
    }

    private boolean isLeaf(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }
}