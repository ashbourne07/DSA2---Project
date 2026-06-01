import java.util.*;

class Product {
    int itemId;
    String productName;

    Product(int itemId, String productName) {
        this.itemId = itemId;
        this.productName = productName;
    }
}

public class SmartLogisticsInventory {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        TreeMap<Integer, String> inventory = new TreeMap<>();

        System.out.print("Enter number of inventory items: ");
        int n = sc.nextInt();
        sc.nextLine();

        for(int i = 0; i < n; i++) {

            System.out.print("Enter Item ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Product Name: ");
            String name = sc.nextLine();

            inventory.put(id, name);
        }

        System.out.print("Enter Item ID to Search: ");
        int searchId = sc.nextInt();

        System.out.print("Enter Range Start ID: ");
        int startRange = sc.nextInt();

        System.out.print("Enter Range End ID: ");
        int endRange = sc.nextInt();

        System.out.println("\n==============================================");
        System.out.println(" SMART LOGISTICS WAREHOUSE INVENTORY REPORT");
        System.out.println("==============================================");

        System.out.println("\nInventory Records Entered:");

        for(Map.Entry<Integer,String> entry : inventory.entrySet()) {
            System.out.println(
                    entry.getKey() +
                    " - " +
                    entry.getValue());
        }

        System.out.println("\nSearch Operation:");

        if(inventory.containsKey(searchId)) {
            System.out.println("Item Found");
            System.out.println("Item ID : " + searchId);
            System.out.println("Product : " + inventory.get(searchId));
        }
        else {
            System.out.println("Item Not Found");
        }

        System.out.println("\nRange Query Result (" +
                startRange + " - " + endRange + "):");

        boolean found = false;

        for(Map.Entry<Integer,String> entry :
                inventory.subMap(startRange, true,
                        endRange, true).entrySet()) {

            System.out.println(
                    entry.getKey() +
                    " - " +
                    entry.getValue());

            found = true;
        }

        if(!found) {
            System.out.println("No Records Found");
        }

        System.out.println("\nTime Complexity:");
        System.out.println("Search      : O(log n)");
        System.out.println("Insert      : O(log n)");
        System.out.println("Range Query : O(log n + k)");

        System.out.println("\nConclusion:");
        System.out.println("B+ Tree indexing helps warehouse inventory");
        System.out.println("management by providing efficient search");
        System.out.println("and range-based retrieval of products.");

        System.out.println("\n==============================================");
        sc.close();
    }
}
