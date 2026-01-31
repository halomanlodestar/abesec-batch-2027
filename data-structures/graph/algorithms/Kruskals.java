import java.util.Arrays;

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

public class Kruskals {
    public static int findMinCost(int[][] edges, int n) {

        UnionFind dsu = new UnionFind(n);
        Arrays.sort(edges, (a, b) -> a[2] - b[2]);
        int minCost = 0;
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            int p1 = dsu.find(u);
            int p2 = dsu.find(v);
            if (p1 != p2) {
                dsu.union(u, v);
                minCost += edge[2];
            }
        }
        return minCost;
    }

    public static void main(String[] args) {
        int[][] edges = new int[][] {
                { 3, 5, 14 },
                { 1, 7, 11 },
                { 4, 5, 10 },
                { 4, 3, 9 },
                { 1, 2, 8 },
                { 0, 7, 8 },
                { 7, 8, 7 },
                { 2, 3, 7 },
                { 6, 8, 6 },
                { 2, 5, 4 },
                { 0, 1, 4 },
                { 5, 6, 2 },
                { 2, 8, 2 },
                { 6, 7, 1 }
        };
        System.out.println(findMinCost(edges, 9));
    }
}