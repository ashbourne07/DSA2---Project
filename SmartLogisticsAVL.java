import java.util.*;

class AVLNode {
    int key, height;
    AVLNode left, right;

    AVLNode(int key) {
        this.key = key;
        height = 1;
    }
}

public class SmartLogisticsAVL {

    static AVLNode root;

    static int height(AVLNode n) {
        return (n == null) ? 0 : n.height;
    }

    static int getBalance(AVLNode n) {
        return (n == null) ? 0 : height(n.left) - height(n.right);
    }

    static AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    static AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode t2 = y.left;

        y.left = x;
        x.right = t2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    static AVLNode insert(AVLNode node, int key) {

        if (node == null)
            return new AVLNode(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    static AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;

        while (current.left != null)
            current = current.left;

        return current;
    }

    static AVLNode deleteNode(AVLNode root, int key) {

        if (root == null)
            return root;

        if (key < root.key)
            root.left = deleteNode(root.left, key);

        else if (key > root.key)
            root.right = deleteNode(root.right, key);

        else {

            if ((root.left == null) || (root.right == null)) {

                AVLNode temp;

                if (root.left != null)
                    temp = root.left;
                else
                    temp = root.right;

                if (temp == null)
                    root = null;
                else
                    root = temp;

            } else {

                AVLNode temp = minValueNode(root.right);

                root.key = temp.key;

                root.right = deleteNode(root.right, temp.key);
            }
        }

        if (root == null)
            return root;

        root.height = Math.max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    static boolean search(AVLNode root, int key) {

        if (root == null)
            return false;

        if (root.key == key)
            return true;

        if (key < root.key)
            return search(root.left, key);

        return search(root.right, key);
    }

    static void inorder(AVLNode root) {

        if (root != null) {
            inorder(root.left);
            System.out.print(root.key + " ");
            inorder(root.right);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of packages: ");
        int n = sc.nextInt();

        int[] packages = new int[n];

        System.out.println("Enter Tracking IDs:");

        for (int i = 0; i < n; i++) {
            packages[i] = sc.nextInt();
            root = insert(root, packages[i]);
        }

        System.out.print("Enter Tracking ID to Search: ");
        int searchId = sc.nextInt();

        System.out.print("Enter Tracking ID to Delete: ");
        int deleteId = sc.nextInt();

        boolean found = search(root, searchId);

        root = deleteNode(root, deleteId);

        System.out.println("\n========== SMART LOGISTICS REPORT ==========");
        System.out.println("Inserted Tracking IDs:");

        for (int id : packages)
            System.out.print(id + " ");

        System.out.println();

        System.out.println("\nSearch Operation:");
        if (found)
            System.out.println("Package " + searchId + " Found");
        else
            System.out.println("Package " + searchId + " Not Found");

        System.out.println("\nDelete Operation:");
        System.out.println("Package " + deleteId + " Deleted");

        System.out.println("\nFinal AVL Tree (Inorder Traversal):");
        inorder(root);

        System.out.println("\n\nTime Complexity:");
        System.out.println("Insert = O(log n)");
        System.out.println("Search = O(log n)");
        System.out.println("Delete = O(log n)");

        System.out.println("\n==========================================");
        sc.close();
    }
}