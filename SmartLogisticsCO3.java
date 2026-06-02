import java.util.*;

public class SmartLogisticsCO3 {

    static void bfs(List<List<Integer>> graph, int start, String[] warehouses) {
        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> q = new LinkedList<>();

        visited[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int node = q.poll();
            System.out.print(warehouses[node] + " ");

            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    q.add(neighbor);
                }
            }
        }
    }

    static void dfsUtil(List<List<Integer>> graph, int node,
                        boolean[] visited, String[] warehouses) {

        visited[node] = true;
        System.out.print(warehouses[node] + " ");

        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor])
                dfsUtil(graph, neighbor, visited, warehouses);
        }
    }

    static void dfs(List<List<Integer>> graph, int start,
                    String[] warehouses) {

        boolean[] visited = new boolean[graph.size()];
        dfsUtil(graph, start, visited, warehouses);
    }

    static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        Edge(int s, int d, int w) {
            src = s;
            dest = d;
            weight = w;
        }

        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }

    static class DSU {
        int[] parent;

        DSU(int n) {
            parent = new int[n];

            for (int i = 0; i < n; i++)
                parent[i] = i;
        }

        int find(int x) {
            if (parent[x] == x)
                return x;

            return parent[x] = find(parent[x]);
        }

        boolean union(int a, int b) {

            int pa = find(a);
            int pb = find(b);

            if (pa == pb)
                return false;

            parent[pa] = pb;
            return true;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = 5;

        String[] warehouses = new String[n];

        System.out.println("Enter 5 Warehouse Names:");

        for (int i = 0; i < n; i++)
            warehouses[i] = sc.next();

        System.out.print("Enter Number of Routes: ");
        int m = sc.nextInt();

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        List<Edge> edges = new ArrayList<>();

        System.out.println(
                "Enter Route Details (SourceIndex DestinationIndex Cost)");

        for (int i = 0; i < m; i++) {

            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();

            graph.get(u).add(v);
            graph.get(v).add(u);

            edges.add(new Edge(u, v, w));
        }

        Collections.sort(edges);

        DSU dsu = new DSU(n);

        int mstCost = 0;
        List<Edge> mstEdges = new ArrayList<>();

        for (Edge e : edges) {

            if (dsu.union(e.src, e.dest)) {
                mstCost += e.weight;
                mstEdges.add(e);
            }
        }

        System.out.println("\n====================================");
        System.out.println(" SMART LOGISTICS ROUTE REPORT");
        System.out.println("====================================");

        System.out.println("\nWarehouses:");

        for (String w : warehouses)
            System.out.print(w + " ");

        System.out.println();

        System.out.println("\nBFS Traversal:");
        bfs(graph, 0, warehouses);

        System.out.println();

        System.out.println("\nDFS Traversal:");
        dfs(graph, 0, warehouses);

        System.out.println();

        System.out.println("\nMinimum Spanning Tree:");

        for (Edge e : mstEdges) {
            System.out.println(
                    warehouses[e.src] + " - " +
                    warehouses[e.dest] +
                    " = " + e.weight);
        }

        System.out.println("\nTotal MST Cost = " + mstCost);

        System.out.println("\nTime Complexity:");
        System.out.println("BFS = O(V + E)");
        System.out.println("DFS = O(V + E)");
        System.out.println("Kruskal MST = O(E log E)");

        System.out.println("\n====================================");
        sc.close();
    }
}