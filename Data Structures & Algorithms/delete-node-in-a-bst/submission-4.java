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
    
    public TreeNode deleteNode(TreeNode root, int key) {
        return deleteNodeRecursive(root, null, key);
    }

    private TreeNode deleteNodeRecursive(TreeNode curr, TreeNode parent, int key) {
        if(curr == null) return null;

        if(key < curr.val) {
            curr.left = deleteNodeRecursive(curr.left, curr, key);
            return curr;
        }

        if(key > curr.val) {
            curr.right = deleteNodeRecursive(curr.right, curr, key);
            return curr;
        }

        //curr is the node to delete
        if(isLeaf(curr)) { //Both children are empty
            return null;
        }

        if(curr.left != null && curr.right != null) { //Has both children
            TreeNode[] replacementAndParent = getRightmostNodeAndItsParentOfSubtree(curr.left, curr);
            curr.val = replacementAndParent[1].val;
            if(replacementAndParent[0] == curr) {
                curr.left = null;
            } else {
                replacementAndParent[0].right = null;
            }
            return curr;
        }

        //Has only one child
        return (curr.left != null) ? curr.left : curr.right; //Root is being deleted and has only one child
    }
    
    private boolean isLeaf(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }

    private TreeNode[] getRightmostNodeAndItsParentOfSubtree(TreeNode node, TreeNode parentOfNode) {
        if(node == null) return null;

        TreeNode parent = parentOfNode;
        TreeNode curr = node;

        while(curr.right != null) {
            parent = curr;
            curr = curr.right;
        }

        return new TreeNode[]{parent, curr};
    }
}