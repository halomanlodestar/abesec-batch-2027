import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class BreadthFirstSearch {

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

    private static List<Integer> bfs(int n, List<List<Integer>> adjacencyList, int src, Set<Integer> visited) {
        List<Integer> bfs = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        queue.add(src);
        distance[src] = 0;
        visited.add(src);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            bfs.add(curr);
            for (int nbr : adjacencyList.get(curr)) {
                if (!visited.contains(nbr)) {
                    queue.add(nbr);
                    visited.add(nbr);
                    distance[nbr] = distance[curr] + 1;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println(i + " : " + distance[i]);
        }
        return bfs;
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
                // { 3, 6 },
                { 6, 7 },
        };
        int n = 8;
        List<List<Integer>> adjacencyList = buildGraph(edges, 8);
        Set<Integer> visited = new HashSet<>();
        List<Integer> bfs = bfs(n, adjacencyList, 1, visited);
        for (int vertex : bfs) {
            System.out.print(vertex + "   ");
        }
        // for (int src = 0; src < n; src++) {
        // if (!visited.contains(src)) {
        // bfs = bfs(n, adjacencyList, src, visited);
        // for (int vertex : bfs) {
        // System.out.print(vertex + " ");
        // }
        // }
        // }
    }
}
