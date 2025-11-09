### LeetCode Problem Link: [101. Symmetric Tree](https://leetcode.com/problems/symmetric-tree/)

```java
class Solution {
    private boolean solve(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        if(p.val != q.val) return false;
        return solve(p.left, q.right) && solve(p.right, q.left);
    }

    public boolean isSymmetric(TreeNode root) {
        return solve(root, root);
    }
}
```

#### Time Complexity: `O(N)`.

#### Space Compelxity: `O(N)`, because recursion uses space to store activation records.
