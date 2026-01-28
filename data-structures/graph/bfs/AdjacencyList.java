import java.util.ArrayList;
import java.util.List;

class AdjacencyList {

    private static List<List<Integer>> buildGraph(int[][] edges, int n) {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }

        return adjacencyList;
    }

    public static void main(String[] args) {
        int[][] edges = new int[][] {
                { 0, 1 },
                { 0, 2 },
                { 0, 3 },
                { 1, 4 },
                { 2, 4 },
                { 2, 5 },
                { 3, 4 },
                { 3, 6 },
                { 6, 7 },
        };
        List<List<Integer>> adjacencyList = buildGraph(edges, 8);
        List<Integer> nbrs = adjacencyList.get(6);
        for (int nbr : nbrs) {
            System.out.print(nbr + " ");
        }
        System.out.println();
    }
}
