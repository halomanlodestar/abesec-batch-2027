### LeetCode Problem Link: [987. Vertical Order Traversal of a Binary Tree](https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree)

```java
class Pair {
    int r, val;
    public Pair(int r, int val) {
        this.r = r;
        this.val = val;
    }

    public String toString() {
        return "{" + r + " : " + val + "}";
    }
}
class Solution {
    private void solve(TreeNode root, int row, int col, TreeMap<Integer, List<Pair>> map) {
        if(root == null) return;
        List<Pair> colList = map.getOrDefault(col, new ArrayList<>());
        colList.add(new Pair(row, root.val));
        map.put(col, colList);

        solve(root.left, row + 1, col - 1, map);
        solve(root.right, row + 1, col + 1, map);
    }
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer, List<Pair>> map = new TreeMap<>();
        solve(root, 0, 0, map);
        List<List<Integer>> finalAns = new ArrayList<>();
        for(Map.Entry<Integer, List<Pair>> entry : map.entrySet()) {
            List<Pair> currCol = entry.getValue();
            currCol.sort((p1, p2) -> {
                if(p1.r == p2.r) {
                    return p1.val - p2.val;
                }
                return p1.r - p2.r;
            });
            List<Integer> ans = new ArrayList<>();
            for(Pair p : currCol) {
                ans.add(p.val);
            }
            finalAns.add(ans);
        }
        return finalAns;
    }
}
```

#### Time Complexity: `O(N)`.

#### Space Compelxity: `O(N)`, because recursion uses space to store activation records.
