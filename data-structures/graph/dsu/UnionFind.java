public class UnionFind {

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

    public static void main(String[] args) {
        UnionFind dsu = new UnionFind(7);
        dsu.union(0, 1);
        dsu.union(0, 2);
        dsu.union(1, 2);
        dsu.union(1, 3);
        dsu.union(2, 3);
        dsu.union(3, 4);
        dsu.union(1, 6);
        dsu.union(5, 6);
        System.out.println(dsu.areFriends(1, 5));
        for (int i = 0; i < 7; i++) {
            System.out.print(dsu.parent[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < 7; i++) {
            System.out.print(dsu.rank[i] + " ");
        }
        System.out.println();
    }
}