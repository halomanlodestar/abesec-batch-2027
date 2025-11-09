### LeetCode Problem Link: [1448. Count Good Nodes in Binary Tree](https://leetcode.com/problems/count-good-nodes-in-binary-tree)

```java
class Solution {
    private int solve(TreeNode root, int maxSoFar) {
        if(root == null) return 0;
        int left = solve(root.left, Math.max(root.val, maxSoFar));
        int right = solve(root.right, Math.max(root.val, maxSoFar));
        if(maxSoFar > root.val) {
            return left + right;
        }
        return left + right + 1;
    }

    public int goodNodes(TreeNode root) {
        return solve(root, Integer.MIN_VALUE);
    }
}
```

#### Time Complexity: `O(N)`.

#### Space Compelxity: `O(N)`, because recursion uses space to store activation records.
