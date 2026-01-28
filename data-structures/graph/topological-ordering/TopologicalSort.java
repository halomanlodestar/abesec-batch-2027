import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class TopologicalSort {

    private static List<List<Integer>> buildGraph(int[][] edges, int n, Map<Integer, Integer> indegreeMap) {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
            indegreeMap.put(i, 0);
        }
        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            indegreeMap.put(edge[1], indegreeMap.get(edge[1]) + 1);
        }

        return adjacencyList;
    }

    private static List<Integer> getTopologicalOrdering(int[][] edges, int n) {
        Map<Integer, Integer> indegreeMap = new HashMap<>();
        List<List<Integer>> graph = buildGraph(edges, n, indegreeMap);
        Queue<Integer> queue = new LinkedList<>();

        List<Integer> sortedOrder = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : indegreeMap.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            sortedOrder.add(curr);
            for (int nbr : graph.get(curr)) {
                indegreeMap.put(nbr, indegreeMap.get(nbr) - 1);
                if (indegreeMap.get(nbr) == 0) {
                    queue.add(nbr);
                }
            }
        }
        return sortedOrder;
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
        int n = 8;
        List<Integer> order = getTopologicalOrdering(edges, n);
        for (int vertex : order) {
            System.out.print(vertex + "   ");
        }
        System.out.println();
    }
}
