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
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if(root == null) return null;

        final Map<TreeNode, TreeNode> map = new HashMap<>();
        final Set<TreeNode> visited = new HashSet<>();
        final Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()) {
            TreeNode curr = stack.peek();
            if(!visited.contains(curr)) visited.add(curr);
            map.put(curr, curr);

            if(curr.right != null && !visited.contains(curr.right)) stack.push(curr.right);
            if(curr.left != null && !visited.contains(curr.left)) stack.push(curr.left);

            if(stack.peek() == curr) { // no new nodes were added as it's childern - this is a leaf or children have been visited
                stack.pop();
                
                curr.left = map.getOrDefault(curr.left, null);
                curr.right = map.getOrDefault(curr.right, null);  

                if(isLeaf(curr) && curr.val == target) map.put(curr, null);
            }
        }

        return map.get(root);
    }

    private boolean isLeaf(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }
}