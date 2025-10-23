package smartcity;

import java.util.*;

public class Graph {
    private Map<String, List<String>> adjList = new HashMap<>();

    // Add new location
    public boolean addLocation(String location) {
        if (location == null || location.isBlank()) return false;
        if (adjList.containsKey(location)) return false;
        adjList.put(location, new ArrayList<>());
        return true;
    }

    // Remove a location
    public boolean removeLocation(String location) {
        if (!adjList.containsKey(location)) return false;
        // remove the node
        adjList.remove(location);
        // remove any references to it from other lists
        for (List<String> roads : adjList.values()) {
            roads.removeIf(r -> r.equals(location));
        }
        return true;
    }

    // Add road between two locations (undirected)
    public boolean addRoad(String from, String to) {
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) return false;
        if (from.equals(to)) return false; // no self-loop
        List<String> fList = adjList.get(from);
        List<String> tList = adjList.get(to);
        if (fList.contains(to)) return false; // already exists
        fList.add(to);
        tList.add(from);
        return true;
    }

    // Remove road
    public boolean removeRoad(String from, String to) {
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) return false;
        boolean removedFrom = adjList.get(from).remove(to);
        boolean removedTo = adjList.get(to).remove(from);
        return removedFrom || removedTo;
    }

    // Display all connections
    public void displayConnections() {
        System.out.println("\n--- All Connections ---");
        if (adjList.isEmpty()) {
            System.out.println("No locations or connections.");
            return;
        }
        for (var entry : adjList.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    // Get all locations
    public Set<String> getLocations() {
        return new TreeSet<>(adjList.keySet()); // sorted set for nicer display
    }

    // Check if a location exists
    public boolean containsLocation(String location) {
        return adjList.containsKey(location);
    }
}

