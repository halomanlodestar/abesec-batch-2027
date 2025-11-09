### LeetCode Problem Link: [100. Same Tree](https://leetcode.com/problems/same-tree)

```java
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        if(p.val != q.val) return false;
        return sameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
```

#### Time Complexity: `O(N)`.

#### Space Compelxity: `O(N)`, because recursion uses space to store activation records.
