//package JAVA_GROUP_SAMPLE;
//
//import java.util.ArrayList;
//import java.util.List;
//
//// Define a class to represent a place
//class Place {
//    int x;
//    int y;
//    List<String> services;
//
//    public Place(int x, int y, List<String> services) {
//        this.x = x;
//        this.y = y;
//        this.services = services;
//    }
//}
//
//// Define the Map2D ADT
//class Map2D {
//    final int size; // Size of the map
//    final List<Place>[][] grid; // Grid representing the map
//
//    // Constructor to initialize the map with given size
//    public Map2D(int size) {
//        this.size = size;
//        this.grid = new ArrayList[size][size];
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                grid[i][j] = new ArrayList<>();
//            }
//        }
//    }
//
//    // Method to add a place to the map
//    public void addPlace(Place place) {
//        grid[place.x][place.y].add(place);
//    }
//
//    // Method to edit services of a place
//    public void editPlaceServices(int x, int y, List<String> newServices) {
//        List<Place> places = grid[x][y];
//        for (Place place : places) {
//            place.services = newServices;
//        }
//    }
//
//    // Method to remove a place from the map
//    public void removePlace(int x, int y) {
//        grid[x][y].clear();
//    }
//
//    // Method to search for places within a bounding rectangle
//    public List<Place> search(int topLeftX, int topLeftY, int width, int height) {
//        List<Place> result = new ArrayList<>();
//        for (int i = topLeftX; i < topLeftX + width && i < size; i++) {
//            for (int j = topLeftY; j < topLeftY + height && j < size; j++) {
//                result.addAll(grid[i][j]);
//            }
//        }
//        return result;
//    }
//
//    // Method to calculate Euclidean distance between two points
//    public double calculateDistance(int x1, int y1, int x2, int y2) {
//        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
//    }
//
//    // Method to find locations within a specified rectangle
//    public List<Place> locationsWithinRectangle(int x1, int y1, int x2, int y2) {
//        List<Place> locations = new ArrayList<>();
//        for (int x = x1; x <= x2 && x < size; x++) {
//            for (int y = y1; y <= y2 && y < size; y++) {
//                locations.addAll(grid[x][y]);
//            }
//        }
//        return locations;
//    }
//}
//
//public class Main {
//    public static void main(String[] args) {
//        // Example usage of the Map2D ADT with a map size of 100x100
//        Map2D map = new Map2D(100);
//
//        // Adding some places to the map
//        List<String> services1 = new ArrayList<>();
//        services1.add("Gas Station");
//        Place gasStation = new Place(50, 50, services1);
//        map.addPlace(gasStation);
//
//        List<String> services2 = new ArrayList<>();
//        services2.add("Restaurant");
//        Place restaurant = new Place(60, 60, services2);
//        map.addPlace(restaurant);
//
//        // Define the bounding rectangle
//        int topLeftX = 40;
//        int topLeftY = 40;
//        int width = 30;
//        int height = 30;
//
//        // Find locations within the specified rectangle
//        List<Place> locationsWithinRectangle = map.locationsWithinRectangle(topLeftX, topLeftY, topLeftX + width, topLeftY + height);
//        System.out.println("Locations within the specified rectangle:");
//        for (Place place : locationsWithinRectangle) {
//            System.out.println("(" + place.x + ", " + place.y + ")");
//        }
//    }
//}
package JAVA_GROUP_SAMPLE;

import java.util.ArrayList;
import java.util.List;

// Define a class to represent a place
class Place {
    int x;
    int y;
    List<String> services;

    public Place(int x, int y, List<String> services) {
        this.x = x;
        this.y = y;
        this.services = services;
    }
}

// Define the Map2D ADT
class Map2D {
    final int size; // Size of the map
    final List<Place>[][] grid; // Grid representing the map

    // Constructor to initialize the map with given size
    public Map2D(int size) {
        this.size = size;
        this.grid = new ArrayList[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new ArrayList<>();
            }
        }
    }

    // Method to add a place to the map
    public void addPlace(Place place) {
        grid[place.x][place.y].add(place);
    }

    // Method to edit services of a place
    public void editPlaceServices(int x, int y, List<String> newServices) {
        List<Place> places = grid[x][y];
        for (Place place : places) {
            place.services = newServices;
        }
    }

    // Method to remove a place from the map
    public void removePlace(int x, int y) {
        grid[x][y].clear();
    }

    // Method to search for places within a bounding rectangle
    public List<Place> search(int topLeftX, int topLeftY, int width, int height) {
        List<Place> result = new ArrayList<>();
        for (int i = topLeftX; i < topLeftX + width && i < size; i++) {
            for (int j = topLeftY; j < topLeftY + height && j < size; j++) {
                result.addAll(grid[i][j]);
            }
        }
        return result;
    }

    // Method to calculate Euclidean distance between two points
    public double calculateDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    // Method to find locations within a specified rectangle
    public List<Place> locationsWithinRectangle(int x1, int y1, int x2, int y2) {
        List<Place> locations = new ArrayList<>();
        for (int x = x1; x <= x2 && x < size; x++) {
            for (int y = y1; y <= y2 && y < size; y++) {
                locations.addAll(grid[x][y]);
            }
        }
        return locations;
    }
}

public class Main {
    public static void main(String[] args) {
        // Example usage of the Map2D ADT with a map size of 100,000,000
        Map2D map = new Map2D(10_000);

        // Adding some places to the map
        List<String> services = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            services.add("Service" + i);
        }

        // Add a place with multiple services
        Place place = new Place(5000, 5000, services);
        map.addPlace(place);

        // Define the bounding rectangle
        int topLeftX = 4000;
        int topLeftY = 4000;
        int width = 2000;
        int height = 2000;

        // Find locations within the specified rectangle
        List<Place> locationsWithinRectangle = map.locationsWithinRectangle(topLeftX, topLeftY, topLeftX + width, topLeftY + height);
        System.out.println("Locations within the specified rectangle: " + locationsWithinRectangle.size());
    }
}

