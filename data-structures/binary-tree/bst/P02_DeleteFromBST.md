### LeetCode Problem Link: [450. Delete Node in a BST](https://leetcode.com/problems/delete-node-in-a-bst/)

```java
class Solution {
    private TreeNode findLeftMax(TreeNode node) {
        if(node.right != null) return findLeftMax(node.right);
        return node;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;

        if(key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if(key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if(root.left == null && root.right == null) return null;
            if(root.left == null || root.right == null) {
                return root.left != null ? root.left : root.right;
            }
            TreeNode leftMax = findLeftMax(root.left);
            root.val = leftMax.val;
            root.left = deleteNode(root.left, leftMax.val);
            return root;
        }
        return root;
    }
}
```

#### Time Complexity: `O(N)`.

#### Space Compelxity: `O(N)`, because recursion uses space to store activation records.
