// Given the root of a binary tree, the depth of each node is the shortest distance to the root.

// Return the smallest subtree such that it contains all the deepest nodes in the original tree.

// A node is called the deepest if it has the largest depth possible among any node in the entire tree.

// The subtree of a node is a tree consisting of that node, plus the set of all descendants of that node.

 

// Example 1:


// Input: root = [3,5,1,6,2,0,8,null,null,7,4]
// Output: [2,7,4]
// Explanation: We return the node with value 2, colored in yellow in the diagram.
// The nodes coloured in blue are the deepest nodes of the tree.
// Notice that nodes 5, 3 and 2 contain the deepest nodes in the tree but node 2 is the smallest subtree among them, so we return it.
// Example 2:

// Input: root = [1]
// Output: [1]
// Explanation: The root is the deepest node in the tree.
// Example 3:

// Input: root = [0,1,3,null,2]
// Output: [2]
// Explanation: The deepest node in the tree is 2, the valid subtrees are the subtrees of nodes 2, 1 and 0 but the subtree of node 2 is the smallest.
 

// Constraints:

// The number of nodes in the tree will be in the range [1, 500].
// 0 <= Node.val <= 500
// The values of the nodes in the tree are unique.
 

// Note: This question is the same as 1123: https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves


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
// class Solution {

//     Map<Integer, Integer> mp = new HashMap<>();
//     int maxD = 0;

//     TreeNode LCA(TreeNode root) {
//         if (root == null || mp.getOrDefault(root.val, -1) == maxD) {
//             return root;
//         }

//         TreeNode l = LCA(root.left);
//         TreeNode r = LCA(root.right);

//         if (l != null && r != null) {
//             return root;
//         }

//         return l != null ? l : r;
//     }

//     void depth(TreeNode root, int d) {
//         if (root == null) {
//             return;
//         }

//         maxD = Math.max(maxD, d);
//         mp.put(root.val, d);
//         depth(root.left, d + 1);
//         depth(root.right, d + 1);
//     }

//     // T.C : O(n)
//     // S.C : O(maxD) system recursion stack space
//     public TreeNode subtreeWithAllDeepest(TreeNode root) {
//         depth(root, 0);
//         return LCA(root);
    // }

// Approach-2 (Using 1 Pass Solution)
// T.C : O(n)
// S.C : O(maxDepth) System stack space
// class Solution {

//     class Pair {
//         int depth;
//         TreeNode node;

//         Pair(int depth, TreeNode node) {
//             this.depth = depth;
//             this.node = node;
//         }
//     }

//     Pair solve(TreeNode root) {
//         if (root == null) {
//             return new Pair(0, null);
//         }

//         Pair l = solve(root.left);
//         Pair r = solve(root.right);

//         if (l.depth == r.depth) {
//             return new Pair(l.depth + 1, root);
//         } else if (l.depth > r.depth) {
//             return new Pair(l.depth + 1, l.node);
//         } else {
//             return new Pair(r.depth + 1, r.node);
//         }
//     }

//     public TreeNode subtreeWithAllDeepest(TreeNode root) {
//         return solve(root).node;
//     }

// }