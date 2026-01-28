import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class DepthFirstSearch {

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

    private static void dfs(List<List<Integer>> adjacencyList, int node, Set<Integer> visited, List<Integer> dfsArr) {
        visited.add(node);
        dfsArr.add(node);
        for (int nbr : adjacencyList.get(node)) {
            if (!visited.contains(nbr)) {
                dfs(adjacencyList, nbr, visited, dfsArr);
            }
        }
    }

    public static void main(String[] args) {
        int[][] edges = new int[][] {
                { 0, 1 },
                { 0, 5 },
                { 0, 6 },
                { 1, 6 },
                { 2, 4 },
                { 2, 6 },
                { 3, 5 },
                { 5, 6 },
                { 7, 8 }
        };
        int n = 9;
        List<List<Integer>> adjacencyList = buildGraph(edges, n);
        Set<Integer> visited = new HashSet<>();
        List<List<Integer>> components = new ArrayList<>();
        int count = 0;
        for (int node = 0; node < n; node++) {
            if (!visited.contains(node)) {
                List<Integer> component = new ArrayList<>();
                dfs(adjacencyList, node, visited, component);
                count += 1;
                components.add(component);
            }
        }
        for (List<Integer> component : components) {
            System.out.println(component.toString());
        }
        System.out.println();
        System.out.print(count);
        System.out.println();
    }
}
