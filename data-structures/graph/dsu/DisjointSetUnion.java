public class DisjointSetUnion {

    private int[] parent;

    public DisjointSetUnion(int size) {
        this.parent = new int[size];
        for (int i = 0; i < size; i++) {
            this.parent[i] = i;
        }
    }

    public int find(int u) {
        if (parent[u] == u)
            return u;
        return find(parent[u]);
    }

    public void union(int u, int v) {
        int p1 = find(u);
        int p2 = find(v);
        if (p1 != p2) {
            parent[p2] = p1;
        }
    }

    public boolean areFriends(int p1, int p2) {
        return find(p1) == find(p2);
    }

    public static void main(String[] args) {
        DisjointSetUnion dsu = new DisjointSetUnion(7);
        dsu.union(0, 1);
        dsu.union(0, 2);
        dsu.union(1, 2);
        dsu.union(1, 3);
        dsu.union(2, 3);
        dsu.union(3, 4);
        dsu.union(5, 6);
        System.out.println(dsu.areFriends(1, 5));
    }
}