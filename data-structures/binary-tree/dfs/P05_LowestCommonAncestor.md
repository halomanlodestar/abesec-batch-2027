### LeetCode Problem Link: [236. Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree)

**Brute Force Solution**

```java
class Solution {
    private boolean pathFromRootToGivenNode(TreeNode root, TreeNode p, List<TreeNode> path) {
        if(root == null) return false;
        path.add(root);
        if(root.val == p.val) return true;

        if(pathFromRootToGivenNode(root.left, p, path) || pathFromRootToGivenNode(root.right, p, path)) {
            return true;
        }

        path.remove(path.size() - 1);
        return false;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pathP = new ArrayList<>();
        List<TreeNode> pathQ = new ArrayList<>();
        pathFromRootToGivenNode(root, p, pathP);
        pathFromRootToGivenNode(root, q, pathQ);
        TreeNode ans = null;
        for(int i = 0; i < Math.min(pathP.size(), pathQ.size()); i++) {
            if(pathP.get(i).val == pathQ.get(i).val)
                ans = pathP.get(i);
            else
                break;
        }
        return ans;
    }
}
```

#### Time Complexity: `O(N)`.

#### Space Compelxity: `O(N)`, because recursion uses space to store activation records.

**Optimised Solution**

Yet to be discussed
