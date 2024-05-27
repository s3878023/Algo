package sampleProject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

class Place {
    private int x;
    private int y;
    private Set<String> services;

    public Place(int x, int y, Set<String> services) {
        this.x = x;
        this.y = y;
        this.services = services;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Set<String> getServices() {
        return services;
    }

    // Method to calculate Euclidean distance between two places
    public double distanceTo(int x, int y) {
        return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
    }
}

class Map2D {
    private Map<String, List<Place>> serviceIndex;

    public Map2D() {
        this.serviceIndex = new HashMap<>();
    }

    public void addPlace(Place place) {
        for (String service : place.getServices()) {
            serviceIndex.computeIfAbsent(service, k -> new ArrayList<>()).add(place);
        }
    }

    public void editPlace(Place place, Set<String> newServices) {
        for (String service : place.getServices()) {
            serviceIndex.get(service).remove(place);
        }
        for (String service : newServices) {
            serviceIndex.computeIfAbsent(service, k -> new ArrayList<>()).add(place);
        }
    }

    public void removePlace(Place place) {
        for (String service : place.getServices()) {
            serviceIndex.get(service).remove(place);
        }
    }

    public List<Place> search(int topLeftX, int topLeftY, int width, int height, String serviceType, int maxResults) {
        List<Place> result = new ArrayList<>();
        double maxDistance = Math.sqrt(Math.pow(width / 2.0, 2) + Math.pow(height / 2.0, 2));
        List<Place> places = serviceIndex.getOrDefault(serviceType, new ArrayList<>());

        for (Place place : places) {
            if (place.getX() >= topLeftX && place.getX() <= topLeftX + width &&
                    place.getY() >= topLeftY && place.getY() <= topLeftY + height &&
                    place.distanceTo(topLeftX + width / 2, topLeftY + height / 2) <= maxDistance) {
                result.add(place);
                if (result.size() >= maxResults) {
                    break;
                }
            }
        }
        return result;
    }

    public boolean removePlaceAt(int x, int y) {
        List<Place> placesToRemove = new ArrayList<>();
        for (List<Place> places : serviceIndex.values()) {
            for (Place place : places) {
                if (place.getX() == x && place.getY() == y) {
                    placesToRemove.add(place);
                }
            }
        }
        if (placesToRemove.isEmpty()) {
            System.out.println("No shop at the place.");
            return false;
        }
        for (Place place : placesToRemove) {
            removePlace(place);
        }
        System.out.println("Place(s) at (" + x + ", " + y + ") removed.");
        return true;
    }
//
//    public boolean removePlacesByServiceType(String serviceType) {
//        List<Place> placesToRemove = serviceIndex.getOrDefault(serviceType, new ArrayList<>());
//        if (placesToRemove.isEmpty()) {
//            System.out.println("No shop of type '" + serviceType + "' found.");
//            return false;
//        }
//        for (Place place : placesToRemove) {
//            removePlace(place);
//        }
//        System.out.println("Place(s) of type '" + serviceType + "' removed.");
//        return true;
//    }


}

public class Main {
    public static void main(String[] args) {
        Map2D map = new Map2D();

        // Adding some places
        Set<String> services1 = new HashSet<>();
        services1.add("ATM");
        map.addPlace(new Place(500, 500, services1));

        Set<String> services2 = new HashSet<>();
        services2.add("Restaurant");
        services2.add("Coffee Shop");
        map.addPlace(new Place(600, 600, services2));

        for (int i = 0; i < 100000; i++) { // Update loop condition
            int x = (int) (Math.random() * 1000);
            int y = (int) (Math.random() * 1000);
            Set<String> services = new HashSet<>();
            services.add("Service" + (i % 100)); // Varying service types
            map.addPlace(new Place(x, y, services));
            System.out.println("success");
        }

        map.addPlace(new Place(520, 520, Set.of("Service1")));
        map.addPlace(new Place(530, 530, Set.of("Service2")));
        map.addPlace(new Place(540, 540, Set.of("Service3")));
        map.addPlace(new Place(168, 168, Set.of("Pizzaria")));
        map.addPlace(new Place(149, 149, Set.of("Hotel")));
        map.addPlace(new Place(266, 266, Set.of("Stadium")));
        map.addPlace(new Place(264, 266, Set.of("Stadium")));

        map.removePlaceAt(264, 266);
        map.removePlaceAt(501,501);
//        map.removePlacesByServiceType("Hotel");

        // Search for places
        List<Place> searchResult = map.search(0, 0, 500, 500, "Stadium", 1000);
        for (Place place : searchResult) {
            System.out.println("Found " + place.getServices() + " at (" + place.getX() + ", " + place.getY() + ")");
        }
    }
}