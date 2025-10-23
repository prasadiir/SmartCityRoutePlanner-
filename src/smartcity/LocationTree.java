package smartcity;

public class LocationTree {
    LocationNode root;

    // Insert a new location into the tree
    public void insert(String name) {
        if (name == null || name.isBlank()) return;
        root = insertRec(root, name);
    }

    private LocationNode insertRec(LocationNode root, String name) {
        if (root == null) {
            return new LocationNode(name);
        }
        int cmp = name.compareToIgnoreCase(root.name);
        if (cmp < 0) root.left = insertRec(root.left, name);
        else if (cmp > 0) root.right = insertRec(root.right, name);
        // if equal, do nothing (no duplicates)
        return root;
    }

    // Remove a node with given name. Returns true if removed, false if not found.
    public boolean remove(String name) {
        if (name == null || name.isBlank()) return false;
        if (!contains(name)) return false;
        root = removeRec(root, name);
        return true;
    }

    private LocationNode removeRec(LocationNode root, String name) {
        if (root == null) return null;
        int cmp = name.compareToIgnoreCase(root.name);
        if (cmp < 0) {
            root.left = removeRec(root.left, name);
        } else if (cmp > 0) {
            root.right = removeRec(root.right, name);
        } else {
            // found node to delete
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            // node with two children: get inorder successor (smallest in right)
            root.name = minValue(root.right);
            root.right = removeRec(root.right, root.name);
        }
        return root;
    }

    private String minValue(LocationNode node) {
        String minv = node.name;
        while (node.left != null) {
            node = node.left;
            minv = node.name;
        }
        return minv;
    }

    // check whether tree contains name
    public boolean contains(String name) {
        return containsRec(root, name);
    }

    private boolean containsRec(LocationNode root, String name) {
        if (root == null) return false;
        int cmp = name.compareToIgnoreCase(root.name);
        if (cmp == 0) return true;
        if (cmp < 0) return containsRec(root.left, name);
        else return containsRec(root.right, name);
    }

    // Display all locations (in-order)
    public void display() {
        System.out.println("\n--- All Locations (Tree View) ---");
        if (root == null) System.out.println("No locations added yet.");
        else displayRec(root);
    }

    private void displayRec(LocationNode root) {
        if (root != null) {
            displayRec(root.left);
            System.out.println(root.name);
            displayRec(root.right);
        }
    }
}
