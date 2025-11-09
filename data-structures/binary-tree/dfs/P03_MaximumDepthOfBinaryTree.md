### LeetCode Problem Link: [104. Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree)

```java
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return 1 + Math.max(left, right);
    }
}
```

#### Time Complexity: `O(N)`.

#### Space Compelxity: `O(N)`, because recursion uses space to store activation records.
