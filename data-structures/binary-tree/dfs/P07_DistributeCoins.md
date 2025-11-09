### LeetCode Problem Link: [979. Distribute Coins in Binary Tree](https://leetcode.com/problems/distribute-coins-in-binary-tree/)

```java
class Solution {
    int count = 0;

    private int solve(TreeNode root) {
        if(root == null) return 0;
        int left = solve(root.left);
        int right = solve(root.right);
        count += (Math.abs(left) + Math.abs(right));
        int exactCoins = left + right + root.val;
        return exactCoins - 1;
    }

    public int distributeCoins(TreeNode root) {
        solve(root);
        return count;
    }
}
```

#### Time Complexity: `O(N)`.

#### Space Compelxity: `O(N)`, because recursion uses space to store activation records.
