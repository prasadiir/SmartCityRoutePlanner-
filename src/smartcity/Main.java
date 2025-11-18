package smartcity;

import java.util.Scanner;

public class Main {
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Graph graph = new Graph();
        LocationTree tree = new LocationTree();

        while (true) {
            System.out.println("\n--- Smart City Route Planner ---");
            System.out.println("1. Add a new location");
            System.out.println("2. Remove a location");
            System.out.println("3. Add a road between locations");
            System.out.println("4. Remove a road");
            System.out.println("5. Display all connections");
            System.out.println("6. Display all locations");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 1 and 7.");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter location name: ");
                    String loc = sc.nextLine().trim();
                    if (loc.isBlank()) {
                        System.out.println("Location name cannot be empty.");
                        break;
                    }
                    boolean ok = graph.addLocation(loc);
                    if (ok) {
                        tree.insert(loc);
                        System.out.println(loc + " added successfully!");
                    } else {
                        System.out.println("Location already exists or invalid.");
                    }
                }
                case 2 -> {
                    System.out.print("Enter location to remove: ");
                    String loc = sc.nextLine().trim();
                    if (loc.isBlank()) {
                        System.out.println("Location name cannot be empty.");
                        break;
                    }
                    boolean gRemoved = graph.removeLocation(loc);
                    boolean tRemoved = tree.remove(loc); // will return false if not present
                    if (gRemoved || tRemoved) {
                        System.out.println(loc + " removed successfully!");
                    } else {
                        System.out.println("Location not found.");
                    }
                }
                case 3 -> {
                    System.out.print("Enter first location: ");
                    String from = sc.nextLine().trim();
                    System.out.print("Enter second location: ");
                    String to = sc.nextLine().trim();
                    if (from.isBlank() || to.isBlank()) {
                        System.out.println("Names cannot be empty.");
                        break;
                    }
                    boolean ok = graph.addRoad(from, to);
                    if (ok) System.out.println("Road added between " + from + " and " + to + ".");
                    else System.out.println("Unable to add road. Make sure both locations exist and road does not already exist.");
                }
                case 4 -> {
                    System.out.print("Enter first location: ");
                    String from = sc.nextLine().trim();
                    System.out.print("Enter second location: ");
                    String to = sc.nextLine().trim();
                    if (from.isBlank() || to.isBlank()) {
                        System.out.println("Names cannot be empty.");
                        break;
                    }
                    boolean ok = graph.removeRoad(from, to);
                    if (ok) System.out.println("Road removed successfully.");
                    else System.out.println("Unable to remove road. Make sure both locations exist and the road exists.");
                }
                case 5 -> graph.displayConnections();
                case 6 -> tree.display();
                case 7 -> {
                    System.out.println("Thank you for using Smart City Route Planner!");
                    sc.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice! Please enter a number from 1 to 7.");
            }
        }
    }
}
