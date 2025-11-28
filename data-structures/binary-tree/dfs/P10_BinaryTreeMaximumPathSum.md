### LeetCode Problem Link: [124. Binary Tree Maximum Path Sum](https://leetcode.com/problems/binary-tree-maximum-path-sum/)

```java
class Solution {
    private int maxSoFar = Integer.MIN_VALUE;
    private int solve(TreeNode root) {
        if(root == null) return 0;

        int leftSum = solve(root.left);
        int rightSum = solve(root.right);
        leftSum = Math.max(leftSum, 0);
        rightSum = Math.max(rightSum, 0);

        int currSum = leftSum + rightSum + root.val;
        maxSoFar = Math.max(maxSoFar, currSum);
        return Math.max(leftSum ,rightSum) + root.val;
    }

    public int maxPathSum(TreeNode root) {
        solve(root);
        return maxSoFar;
    }
}
```

#### Time Complexity: `O(N)`.

#### Space Compelxity: `O(N)`, because recursion uses space to store activation records.
