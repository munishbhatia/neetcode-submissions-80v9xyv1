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
    public int kthSmallest(TreeNode root, int k) {
        final int[] res = new int[] {Integer.MIN_VALUE};
        kthSmallest(root, k, 0, res);
        return res[0];
    }

    private int kthSmallest(TreeNode node, int k, int curr, int[] res) {
        if(node == null) {
            return curr;
        }
        int leftSubtreeCount = kthSmallest(node.left, k, curr, res);
        if(leftSubtreeCount + 1 == k) {
            res[0] = node.val;
            return k; //no need to continue the traversal
        }
        int rightSubtreeCount = kthSmallest(node.right, k, leftSubtreeCount+1, res);
        return rightSubtreeCount;
    }
}

// Naive approach - store the inorder traversal of the BST in a list/array
// Get the kth element element by traversing the list from the start

// We can also do an inorder traversal of the BST and keep track of the visit index of the node
// Node with visit index k = kth smallest node
// We visit the left subtree, then current node, followed by the right subtree in inorder traversal
// say we are looking for the 5th smallest node and node c has 2 nodes in int's left subtree
// this would make node c as the 3rd node in sequence
// We can either continue to find the 2nd node in sequence in the right subtree of node c
// or continue passing down the index and return the value of the node at the 5th index
// If there are less than 2 nodes in the right subtree of node c (say only 1 node is present),
// we will then continue the search at the parent node of node c and in the right subtree of the 
// parent node of node c respectively