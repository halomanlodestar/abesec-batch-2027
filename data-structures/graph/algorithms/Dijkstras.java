import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Pair {
    int node;
    int distance;

    public Pair(int node, int distance) {
        this.node = node;
        this.distance = distance;
    }
}

public class Dijkstras {

    private static List<List<Pair>> buildGraph(int[][] edges, int n) {
        List<List<Pair>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(new Pair(edge[1], edge[2]));
            graph.get(edge[1]).add(new Pair(edge[0], edge[2]));
        }
        return graph;
    }

    private static int[] findShortestPath(int n, int[][] edges, int src) {
        List<List<Pair>> graph = buildGraph(edges, n);
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        PriorityQueue<Pair> minHeap = new PriorityQueue<>((p1, p2) -> p1.distance - p2.distance);

        distance[src] = 0;
        minHeap.add(new Pair(src, 0));

        while (!minHeap.isEmpty()) {
            Pair currPair = minHeap.poll();
            int currNode = currPair.node;
            int currDist = currPair.distance;

            for (Pair pair : graph.get(currNode)) {
                int nbr = pair.node;
                int nbrDist = pair.distance;
                if (currDist + nbrDist < distance[nbr]) {
                    distance[nbr] = currDist + nbrDist;
                    minHeap.add(new Pair(nbr, distance[nbr]));
                }
            }
        }

        return distance;
    }

    public static void main(String[] args) {
        int[][] edges = new int[][] {
                { 0, 1, 4 },
                { 0, 3, 5 },
                { 0, 4, 2 },
                { 1, 2, 4 },
                { 1, 3, 2 },
                { 2, 3, 2 },
                { 3, 4, 1 }
        };
        int n = 5;
        int src = 0;
        int ans[] = findShortestPath(n, edges, src);
        for (int i = 0; i < n; i++) {
            System.out.println("Distance from " + src + " to " + i + " : " + ans[i]);
        }
    }
}
