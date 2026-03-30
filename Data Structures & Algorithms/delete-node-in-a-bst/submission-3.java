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
        if(root == null) return root;

        TreeNode prev = null;
        TreeNode curr = root;

        while(curr != null && curr.val != key) { //curr = null would mean the key does not exist in the BST
            prev = curr;
            
            curr = (key < curr.val) ? curr.left : curr.right;
        }

        if(curr == null) return root;

        //Now curr points to the node to delete, and prev is it's parent node

        if(isLeaf(curr)) { //Both children are empty
            if(prev == null) return null;

            if(curr == prev.left) {
                prev.left = null;
            } else {
                prev.right = null;
            }

            return root;
        }

        if(curr.left != null && curr.right != null) { //Has both children
            TreeNode[] replacementAndParent = getRightmostNodeAndItsParentOfSubtree(curr.left, curr);
            curr.val = replacementAndParent[1].val;
            if(replacementAndParent[0] == curr) {
                curr.left = null;
            } else {
                replacementAndParent[0].right = null;
            }
            return root;
        }

        //Has only one child
        if(prev == null) return (curr.left != null) ? curr.left : curr.right; //Root is being deleted and has only one child

        if(prev.left == curr) { //curr is left child of prev & curr has only 1 child
            prev.left = (curr.left != null) ? curr.left : curr.right;
            return root;
        }

        //curr is right child of prev & curr has only 1 child
        prev.right = (curr.left != null) ? curr.left : curr.right;
        return root;
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