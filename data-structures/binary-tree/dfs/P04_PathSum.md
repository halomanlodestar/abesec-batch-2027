### LeetCode Problem Link: [112. Path Sum](https://leetcode.com/problems/path-sum/)

```java
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;
        targetSum -= root.val;
        if(root.left == null && root.right == null && targetSum == 0) return true;
        return hasPathSum(root.left, targetSum) ||
        hasPathSum(root.right, targetSum);
    }
}
```

#### Time Complexity: `O(N)`.

#### Space Compelxity: `O(N)`, because recursion uses space to store activation records.
