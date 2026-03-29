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
    public List<Integer> rightSideView(TreeNode root) {
        if(root == null) {
            return List.of();
        }

        final Queue<TreeNode> q = new LinkedList<>();
        final List<Integer> result = new LinkedList<>();

        q.offer(root);

        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i<size; ++i) {
                TreeNode curr = q.poll();

                if(curr.left != null) {
                    q.offer(curr.left);
                }
                if(curr.right != null) {
                    q.offer(curr.right);
                }

                if(i == size-1) {
                    result.add(curr.val);
                }
            }
        }
        return result;
    }
}

// Right view of a tree would include only the last nodes of each tree level in a level order traversal of a tree
// Use level order traversal, just discard all the values except the last one
