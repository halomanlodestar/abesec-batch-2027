### LeetCode Problem Link: [105. Construct Binary Tree from Preorder and Inorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal)

```java
class Solution {
    Map<Integer, Integer> inorderIndexMap = new HashMap<>();
    int preorderIndex = 0;
    private TreeNode arrayToTree(int[] preorder, int s, int e) {
        if(s > e) return null;
        TreeNode root = new TreeNode(preorder[preorderIndex]);
        int i = inorderIndexMap.get(preorder[preorderIndex]);
        preorderIndex += 1;
        root.left = arrayToTree(preorder, s, i - 1);
        root.right = arrayToTree(preorder, i + 1, e);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for(int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        return arrayToTree(preorder, 0, preorder.length - 1);
    }
}
```

#### Time Complexity: `O(N)`.

#### Space Compelxity: `O(N)`, because recursion uses space to store activation records.
