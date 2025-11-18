package smartcity;

public class LocationNode {
    String name;
    LocationNode left, right;

    LocationNode(String name) {
        this.name = name;
        left = right = null;
    }
}

