### LeetCode Problem Link: [113. Path Sum II](https://leetcode.com/problems/path-sum-ii/)

```java
class Solution {
    private void solve(TreeNode root, int targetSum,
            List<List<Integer>> allPaths, List<Integer> path) {
            if(root == null) return;

            targetSum -= root.val;
            path.add(root.val);
            if(targetSum == 0 && root.left == null && root.right == null) { // check for targetSum for zero and check for leaf node
                allPaths.add(new ArrayList(path));
            }

            solve(root.left, targetSum, allPaths, path);
            solve(root.right, targetSum, allPaths, path);
            path.remove(path.size() - 1);

        }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> allPaths = new ArrayList<>();
        solve(root, targetSum, allPaths, new ArrayList<>());
        return allPaths;
    }
}
```

#### Time Complexity: `O(N)`.

#### Space Compelxity: `O(N)`, because recursion uses space to store activation records.
