package TESTSAMPLE;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        Map2D map = new Map2D();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            int x = random.nextInt(10000000);
            int y = random.nextInt(10000000);
            String[] serviceType = {"RandomService1", "RandomService2"};
            List<String> services = Arrays.asList(serviceType);
            map.addPlace(x, y, services);
            System.out.println("Added!");
        }

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add a place");
            System.out.println("2. Edit a place");
            System.out.println("3. Remove a place");
            System.out.println("4. Search for places");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter X coordinate: ");
                    int x = scanner.nextInt();
                    System.out.print("Enter Y coordinate: ");
                    int y = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter services (comma-separated): ");
                    String[] servicesArray = scanner.nextLine().split(",");
                    List<String> services = Arrays.asList(servicesArray);
                    map.addPlace(x, y, services);
                    System.out.println("Place added successfully!");
                    break;
                case 2:
                    System.out.print("Enter X coordinate of the place to edit: ");
                    int editX = scanner.nextInt();
                    System.out.print("Enter Y coordinate of the place to edit: ");
                    int editY = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new services (comma-separated): ");
                    String[] newServicesArray = scanner.nextLine().split(",");
                    List<String> newServices = Arrays.asList(newServicesArray);
                    map.editPlace(editX, editY, newServices);
                    System.out.println("Place edited successfully!");
                    break;
                case 3:
                    System.out.print("Enter X coordinate of the place to remove: ");
                    int removeX = scanner.nextInt();
                    System.out.print("Enter Y coordinate of the place to remove: ");
                    int removeY = scanner.nextInt();
                    map.removePlace(removeX, removeY);
                    System.out.println("Place removed successfully!");
                    break;
                case 4:
                    System.out.print("Enter minimum X coordinate of the bounding rectangle: ");
                    int minX = scanner.nextInt();
                    System.out.print("Enter minimum Y coordinate of the bounding rectangle: ");
                    int minY = scanner.nextInt();
                    System.out.print("Enter maximum X coordinate of the bounding rectangle: ");
                    int maxX = scanner.nextInt();
                    System.out.print("Enter maximum Y coordinate of the bounding rectangle: ");
                    int maxY = scanner.nextInt();
                    List<Map2D.Place> searchResult = map.searchPlaces(minX, minY, maxX, maxY);
                    System.out.println("Search result:");
                    if(searchResult.isEmpty()){
                        System.out.println("No place found at the specificed coordinates.");
                    } else {
                        for (Map2D.Place place : searchResult) {
                            System.out.println(place);
                        }
                    }
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }
        }
    }
}

class Map2D {
    private Map<Integer, Map<Integer, List<Place>>> grid;

    public Map2D() {
        grid = new HashMap<>();
    }

    public void addPlace(int x, int y, List<String> services) {
        int gridX = x / 100000;
        int gridY = y / 100000;
        grid.putIfAbsent(gridX, new java.util.HashMap<>());
        grid.get(gridX).putIfAbsent(gridY, new ArrayList<>());
        grid.get(gridX).get(gridY).add(new Place(x, y, services));
    }

    public void editPlace(int x, int y, List<String> services) {
        int gridX = x / 100000;
        int gridY = y / 100000;
        if (grid.containsKey(gridX) && grid.get(gridX).containsKey(gridY)) {
            List<Place> places = grid.get(gridX).get(gridY);
            for (Place place : places) {
                if (place.getX() == x && place.getY() == y) {
                    place.setServices(services);
                    break;
                }
            }
        }
    }

    public void removePlace(int x, int y) {
        int gridX = x / 100000;
        int gridY = y / 100000;
        if (grid.containsKey(gridX) && grid.get(gridX).containsKey(gridY)) {
            List<Place> places = grid.get(gridX).get(gridY);
            for (Place place : places) {
                if (place.getX() == x && place.getY() == y) {
                    places.remove(place);
                    break;
                }
            }
            if (places.isEmpty()) {
                grid.get(gridX).remove(gridY);
                if (grid.get(gridX).isEmpty()) {
                    grid.remove(gridX);
                }
            }
        }
    }

    public List<Place> searchPlaces(int minX, int minY, int maxX, int maxY) {
        List<Place> result = new ArrayList<>();
        for (java.util.Map.Entry<Integer, java.util.Map<Integer, List<Place>>> entry : grid.entrySet()) {
            int gridX = entry.getKey();
            for (java.util.Map.Entry<Integer, List<Place>> innerEntry : entry.getValue().entrySet()) {
                int gridY = innerEntry.getKey();
                if (gridX * 100000 <= maxX && (gridX + 1) * 100000 >= minX &&
                        gridY * 100000 <= maxY && (gridY + 1) * 100000 >= minY) {
                    for (Place place : innerEntry.getValue()) {
                        if (place.getX() >= minX && place.getX() <= maxX &&
                                place.getY() >= minY && place.getY() <= maxY) {
                            result.add(place);
                            if (result.size() == 50) {
                                return result;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    public static class Place {
        private int x;
        private int y;
        private List<String> services;

        public Place(int x, int y, List<String> services) {
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

        public List<String> getServices() {
            return services;
        }

        public void setServices(List<String> services) {
            this.services = services;
        }
        @Override
        public String toString(){
            String serviceString = String.join(", ", services);
            return String.format("X: %d, Y: %d, Service: %s",x,y,serviceString);
        }

    }
}

interface List<T> extends Iterable<T> {

    boolean insertAt(int index, T value);

    boolean insertBefore(T searchValue, T value);

    boolean insertAfter(T searchValue, T value);

    boolean removeAt(int index);

    boolean remove(T value);

    boolean contains(T value);

    int size();

    boolean hasNext();

    T next();

    void reset();

    T get(int index);

    void add(T t);

    boolean isEmpty();

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    default Iterator<T> iterator() {
        return new ListIterator(this);
    }

    class ListIterator<T> implements Iterator<T> {
        private List<T> list;
        private int currentIndex;

        public ListIterator(List<T> list) {
            this.list = list;
            this.currentIndex = 0;
        }

        public boolean hasNext() {
            return currentIndex < list.size();
        }

        public T next() {
            if (!hasNext()) {
                throw new IllegalStateException("No more elements in the list.");
            }
            return list.get(currentIndex++);
        }
    }
}

class ArrayList<T> implements List<T> {
    private int size;
    private int pointer;
    private static int CAPACITY = 1000;
    private T[] items;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        size = 0;
        pointer = 0;
        items = (T[])new Object[CAPACITY];
    }


    private void shiftRight(int index) {
        for (int i = size; i > index; i--) {
            items[i] = items[i - 1];
        }
    }

    // shift all elements from the end one position to the left
    // until index
    private void shiftLeft(int index) {
        for (int i = index + 1; i < size; i++) {
            items[i - 1] = items[i];
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void reset() {
        pointer = 0;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return items[index];
    }

    @Override
    public boolean hasNext() {
        return (pointer < size);
    }

    @Override
    public T next() {
        pointer++;
        return items[pointer - 1];
    }

    @Override
    public boolean contains(T value) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean insertAt(int index, T value) {
        if (index > size) {
            return false;
        }
        shiftRight(index);
        items[index] = value;
        size++;
        return true;
    }

    @Override
    public boolean insertBefore(T searchValue, T value) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(searchValue)) {
                return insertAt(i, value);
            }
        }
        return false;
    }

    @Override
    public boolean insertAfter(T searchValue, T value) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(searchValue)) {
                return insertAt(i + 1, value);
            }
        }
        return false;
    }

    @Override
    public boolean removeAt(int index) {
        if (index >= size) {
            return false;
        }
        shiftLeft(index);
        size--;
        return true;
    }

    @Override
    public boolean remove(T value) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(value)) {
                return removeAt(i);
            }
        }
        return false;
    }

    @Override
    public void add(T t) {
        if (size < CAPACITY) {
            items[size++] = t;
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}

class Arrays {
    public static <T> List<T> asList(T[] a) {
        List<T> list = new ArrayList<>();
        for (T t : a) {
            list.add(t);
        }
        return list;
    }
}