import java.util.*;

public class SmartLogisticsCO4 {

    static final int INF = 99999;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = 4;

        String[] warehouse = new String[n];

        System.out.println("Enter 4 Warehouse Names:");

        for (int i = 0; i < n; i++)
            warehouse[i] = sc.next();

        int[][] graph = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }

        System.out.print("Enter Number of Routes: ");
        int m = sc.nextInt();

        System.out.println("Enter Source Destination Cost");
        System.out.println("(Use indices 0-3)");

        int[][] edges = new int[m][3];

        for (int i = 0; i < m; i++) {

            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();

            graph[u][v] = w;
            graph[v][u] = w;

            edges[i][0] = u;
            edges[i][1] = v;
            edges[i][2] = w;
        }

        int source = 0;

        /* Dijkstra */

        int[] dijkstra = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(dijkstra, INF);
        dijkstra[source] = 0;

        for (int count = 0; count < n - 1; count++) {

            int min = INF, u = -1;

            for (int i = 0; i < n; i++) {
                if (!visited[i] && dijkstra[i] < min) {
                    min = dijkstra[i];
                    u = i;
                }
            }

            visited[u] = true;

            for (int v = 0; v < n; v++) {

                if (!visited[v]
                        && graph[u][v] != INF
                        && dijkstra[u] + graph[u][v] < dijkstra[v]) {

                    dijkstra[v] = dijkstra[u] + graph[u][v];
                }
            }
        }

        /* Bellman Ford */

        int[] bellman = new int[n];

        Arrays.fill(bellman, INF);
        bellman[source] = 0;

        for (int i = 1; i < n; i++) {

            for (int j = 0; j < m; j++) {

                int u = edges[j][0];
                int v = edges[j][1];
                int w = edges[j][2];

                if (bellman[u] != INF &&
                        bellman[u] + w < bellman[v]) {

                    bellman[v] = bellman[u] + w;
                }

                if (bellman[v] != INF &&
                        bellman[v] + w < bellman[u]) {

                    bellman[u] = bellman[v] + w;
                }
            }
        }

        /* Floyd Warshall */

        int[][] floyd = new int[n][n];

        for (int i = 0; i < n; i++)
            floyd[i] = graph[i].clone();

        for (int k = 0; k < n; k++) {

            for (int i = 0; i < n; i++) {

                for (int j = 0; j < n; j++) {

                    if (floyd[i][k] != INF &&
                            floyd[k][j] != INF &&
                            floyd[i][k] + floyd[k][j] < floyd[i][j]) {

                        floyd[i][j] =
                                floyd[i][k] + floyd[k][j];
                    }
                }
            }
        }

        /* Output */

        System.out.println("\n======================================");
        System.out.println(" SMART LOGISTICS ROUTING REPORT");
        System.out.println("======================================");

        System.out.println("\nWarehouses:");

        for (String w : warehouse)
            System.out.print(w + " ");

        System.out.println();

        System.out.println("\nDijkstra (Source: "
                + warehouse[source] + ")");

        for (int i = 0; i < n; i++) {

            System.out.println(
                    warehouse[source] + " → "
                            + warehouse[i]
                            + " = "
                            + dijkstra[i]);
        }

        System.out.println("\nBellman-Ford (Source: "
                + warehouse[source] + ")");

        for (int i = 0; i < n; i++) {

            System.out.println(
                    warehouse[source] + " → "
                            + warehouse[i]
                            + " = "
                            + bellman[i]);
        }

        System.out.println("\nFloyd-Warshall Matrix:");

        System.out.print("\t");

        for (String w : warehouse)
            System.out.print(w + "\t");

        System.out.println();

        for (int i = 0; i < n; i++) {

            System.out.print(warehouse[i] + "\t");

            for (int j = 0; j < n; j++) {

                System.out.print(floyd[i][j] + "\t");
            }

            System.out.println();
        }

        System.out.println("\nTime Complexity:");
        System.out.println("Dijkstra      = O((V+E) log V)");
        System.out.println("Bellman-Ford  = O(V × E)");
        System.out.println("Floyd-Warshall= O(V³)");

        System.out.println("\n======================================");
        sc.close();
    }
}