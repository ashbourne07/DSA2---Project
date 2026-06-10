import java.util.Scanner;

class Order {
    int orderId;
    int priority;

    Order(int orderId, int priority) {
        this.orderId = orderId;
        this.priority = priority;
    }
}

public class SmartLogisticsCO5 {

    static void merge(Order[] arr, int left, int mid, int right) {

        int n1 = mid - left + 1;
        int n2 = right - mid;

        Order[] L = new Order[n1];
        Order[] R = new Order[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];

        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {

            if (L[i].priority <= R[j].priority)
                arr[k++] = L[i++];
            else
                arr[k++] = R[j++];
        }

        while (i < n1)
            arr[k++] = L[i++];

        while (j < n2)
            arr[k++] = R[j++];
    }

    static void mergeSort(Order[] arr, int left, int right) {

        if (left < right) {

            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = 5;

        Order[] orders = new Order[n];

        System.out.println("Enter 5 Delivery Orders:");

        for (int i = 0; i < n; i++) {

            System.out.print("Order ID: ");
            int id = sc.nextInt();

            System.out.print("Priority (1-5): ");
            int priority = sc.nextInt();

            orders[i] = new Order(id, priority);
        }

        Order[] original = new Order[n];

        for (int i = 0; i < n; i++)
            original[i] = new Order(
                    orders[i].orderId,
                    orders[i].priority);

        mergeSort(orders, 0, n - 1);

        System.out.println("\n========================================");
        System.out.println(" SMART LOGISTICS SORTING REPORT");
        System.out.println("========================================");

        System.out.println("\nOriginal Delivery Orders:");

        for (Order o : original) {

            System.out.println(
                    o.orderId +
                    " - Priority " +
                    o.priority);
        }

        System.out.println("\nSorted Orders (Merge Sort):");

        for (Order o : orders) {

            System.out.println(
                    o.orderId +
                    " - Priority " +
                    o.priority);
        }

        System.out.println("\nAlgorithm Comparison:");

        System.out.println("Merge Sort    : O(n log n)");
        System.out.println("Quick Sort    : Avg O(n log n)");
        System.out.println("Heap Sort     : O(n log n)");
        System.out.println("Counting Sort : O(n + k)");
        System.out.println("Radix Sort    : O(d(n + k))");

        System.out.println("\nConclusion:");
        System.out.println("Merge Sort provides stable");
        System.out.println("sorting for delivery records.");
        System.out.println("Different algorithms are");
        System.out.println("suitable for different");
        System.out.println("logistics requirements.");

        System.out.println("\n========================================");
        sc.close();
    }
}