### LeetCode Problem Link: [1489. Find Critical and Pseudo-Critical Edges in Minimum Spanning Tree](https://leetcode.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/description/)

```java

class UnionFind {

    private int[] parent;
    private int[] rank;

    public UnionFind(int size) {
        this.parent = new int[size];
        this.rank = new int[size];
        for (int i = 0; i < size; i++) {
            this.parent[i] = i;
            this.rank[i] = 1;
        }
    }

    public int find(int u) {
        if (parent[u] == u)
            return u;
        return parent[u] = find(parent[u]);
    }

    public void union(int u, int v) {
        int p1 = find(u);
        int p2 = find(v);
        if (p1 == p2) {
            return;
        }
        if (this.rank[p1] == this.rank[p2]) {
            parent[p2] = p1;
            this.rank[p1] += 1;
        } else if (this.rank[p1] > this.rank[p2]) {
            this.parent[p2] = p1;
        } else {
            this.parent[p1] = p2;
        }
    }

    public boolean areFriends(int p1, int p2) {
        return find(p1) == find(p2);
    }
}

class Solution {

    private int findMST(int n, int[][] edges, int[] include, int[] exclude) {
        UnionFind dsu = new UnionFind(n);
        int minCost = 0;
        int edgesCovered = 0;
        if(include != null) {
            int u = include[0], v = include[1];
            int p1 = dsu.find(u);
            int p2 = dsu.find(v);
            if (p1 != p2) {
                dsu.union(u, v);
                edgesCovered += 1;
                minCost += include[2];
            }
        }

        for (int[] edge : edges) {
            if(exclude != null && exclude[0] == edge[0] && exclude[1] == edge[1] && exclude[2] == edge[2]) {
                continue;
            }
            if(include != null && include[0] == edge[0] && include[1] == edge[1] && include[2] == edge[2]) {
                continue;
            }
            int u = edge[0], v = edge[1];
            int p1 = dsu.find(u);
            int p2 = dsu.find(v);
            if (p1 != p2) {
                dsu.union(u, v);
                edgesCovered += 1;
                minCost += edge[2];
            }
        }

        return edgesCovered == n - 1 ? minCost : Integer.MAX_VALUE;
    }

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int[][] originalEdges = new int[edges.length][3];
        for(int i = 0; i < edges.length; i++) {
            originalEdges[i][0] = edges[i][0];
            originalEdges[i][1] = edges[i][1];
            originalEdges[i][2] = edges[i][2];
        }

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> criticalEdges = new ArrayList<>();
        List<Integer> pseudoCriticalEdges = new ArrayList<>();

        Arrays.sort(edges, (a, b) -> a[2] - b[2]);

        int originalCost = findMST(n, edges, null, null);

        for(int i = 0; i < originalEdges.length; i++) {
            int excludedCost = findMST(n, edges, null, originalEdges[i]);
            int includedCost = findMST(n, edges, originalEdges[i], null);
            if(excludedCost > originalCost) {
                criticalEdges.add(i);
            } else if(includedCost == originalCost) {
                pseudoCriticalEdges.add(i);
            }
        }

        result.add(criticalEdges);
        result.add(pseudoCriticalEdges);

        return result;


    }
}
```
