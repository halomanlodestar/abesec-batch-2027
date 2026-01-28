### LeetCode Problem Link: [98. Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree)

```java
class Solution {
    private boolean solve(TreeNode root, long min, long max) {
        if(root == null) return true;
        if(root.val <= min || root.val >= max) return false;
        return solve(root.left, min, root.val)
                && solve(root.right, root.val, max);
    }

    public boolean isValidBST(TreeNode root) {
        return solve(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
}
```

#### Time Complexity: `O(N)`.

#### Space Compelxity: `O(N)`, because recursion uses space to store activation records.
