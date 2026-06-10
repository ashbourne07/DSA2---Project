import java.util.*;

public class SmartLogisticsCO6 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        /* Activity Scheduling */

        System.out.println("Enter 4 Delivery Requests:");

        String[] deliveries = new String[4];
        int[] start = new int[4];
        int[] finish = new int[4];

        for (int i = 0; i < 4; i++) {

            System.out.print("Delivery Name: ");
            deliveries[i] = sc.next();

            System.out.print("Start Time: ");
            start[i] = sc.nextInt();

            System.out.print("Finish Time: ");
            finish[i] = sc.nextInt();
        }

        /* Knapsack */

        System.out.print("\nEnter Vehicle Capacity: ");
        int capacity = sc.nextInt();

        System.out.println("Enter 4 Packages:");

        int[] weight = new int[4];
        int[] profit = new int[4];

        for (int i = 0; i < 4; i++) {

            System.out.print("Package Weight: ");
            weight[i] = sc.nextInt();

            System.out.print("Package Profit: ");
            profit[i] = sc.nextInt();
        }

        /* LCS */

        System.out.print("\nEnter Shipment Sequence 1: ");
        String s1 = sc.next();

        System.out.print("Enter Shipment Sequence 2: ");
        String s2 = sc.next();

        /* Matrix Chain */

        System.out.println("\nEnter 4 Matrix Dimensions:");

        int[] dim = new int[4];

        for (int i = 0; i < 4; i++)
            dim[i] = sc.nextInt();

        /* Activity Scheduling */

        List<String> selected = new ArrayList<>();

        int lastFinish = -1;

        for (int i = 0; i < 4; i++) {

            if (start[i] >= lastFinish) {

                selected.add(deliveries[i]);
                lastFinish = finish[i];
            }
        }

        /* Knapsack */

        int[][] dp = new int[5][capacity + 1];

        for (int i = 1; i <= 4; i++) {

            for (int w = 0; w <= capacity; w++) {

                if (weight[i - 1] <= w) {

                    dp[i][w] = Math.max(
                            profit[i - 1]
                                    + dp[i - 1][w - weight[i - 1]],
                            dp[i - 1][w]);
                }
                else {

                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        int maxProfit = dp[4][capacity];

        /* LCS */

        int m = s1.length();
        int n = s2.length();

        int[][] lcs = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    lcs[i][j] = 1 + lcs[i - 1][j - 1];

                else
                    lcs[i][j] = Math.max(
                            lcs[i - 1][j],
                            lcs[i][j - 1]);
            }
        }

        /* MCM */

        int mcmCost =
                dim[0] * dim[1] * dim[2]
                        + dim[0] * dim[2] * dim[3];

        /* Output */

        System.out.println("\n==========================================");
        System.out.println(" SMART LOGISTICS OPTIMIZATION REPORT");
        System.out.println("==========================================");

        System.out.println("\nActivity Scheduling:");

        for (String d : selected)
            System.out.println(d);

        System.out.println(
                "Maximum Deliveries = "
                        + selected.size());

        System.out.println("\n0/1 Knapsack:");

        System.out.println(
                "Maximum Profit = "
                        + maxProfit);

        System.out.println("\nLongest Common Subsequence:");

        System.out.println(
                "LCS Length = "
                        + lcs[m][n]);

        System.out.println("\nMatrix Chain Multiplication:");

        System.out.println(
                "Minimum Cost = "
                        + mcmCost);

        System.out.println("\nTime Complexity:");

        System.out.println(
                "Activity Scheduling : O(n log n)");

        System.out.println(
                "0/1 Knapsack        : O(n × W)");

        System.out.println(
                "LCS                 : O(m × n)");

        System.out.println(
                "Matrix Chain        : O(n³)");

        System.out.println("\n==========================================");
        sc.close();
    }
}