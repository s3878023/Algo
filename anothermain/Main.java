package anothermain; 
import java.util.*  ;

interface MyMap<K, V> {
    void put(K key, V value);
    V get(K key);
    boolean containsKey(K key);
    void remove(K key);
    int size();
    boolean isEmpty();
}

class MyHashMap<K, V> implements MyMap<K, V> {
    private Entry<K, V>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        table = new Entry[1000];
        size = 0;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void put(K key, V value) {
        int index = key.hashCode() % table.length;
        for (Entry<K, V> e = table[index]; e != null; e = e.next) {
            if (e.key.equals(key)) {
                e.value = value;
                return;
            }
        }
        table[index] = new Entry<>(key, value, table[index]);
        size++;
    }

    @Override
    public V get(K key) {
        int index = key.hashCode() % table.length;
        for (Entry<K, V> e = table[index]; e != null; e = e.next) {
            if (e.key.equals(key)) {
                return e.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int index = key.hashCode() % table.length;
        for (Entry<K, V> e = table[index]; e != null; e = e.next) {
            if (e.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void remove(K key) {
        int index = key.hashCode() % table.length;
        Entry<K, V> prev = null;
        for (Entry<K, V> e = table[index]; e != null; e = e.next) {
            if (e.key.equals(key)) {
                if (prev == null) {
                    table[index] = e.next;
                } else {
                    prev.next = e.next;
                }
                size--;
                return;
            }
            prev = e;
        }
    }

    @Override
    public int size() {
        return size;
    }

    private static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
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

//class ListIterator<T> implements Iterator<T> {
//    private List<T> list;
//    private int currentIndex;
//
//    public ListIterator(List<T> list) {
//        this.list = list;
//        this.currentIndex = 0;
//    }
//
//    public boolean hasNext() {
//        return currentIndex < list.size();
//    }
//
//    public T next() {
//        if (!hasNext()) {
//            throw new IllegalStateException("No more elements in the list.");
//        }
//        return list.get(currentIndex++);
//    }
//}

class ArrayList<T> implements List<T> {
    private int size;
    private int pointer;
    private static int CAPACITY = 1000;
    private T[] items;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        size = 0;
        pointer = 0;
        items = (T[]) new Object[CAPACITY];
    }

    private void shiftRight(int index) {
        for (int i = size; i > index; i--) {
            items[i] = items[i - 1];
        }
    }
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
        if (index >= size || index < 0) {
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

class Map2D {
    private MyMap<Integer, MyMap<Integer, ArrayList<Place>>> grid;

    public Map2D() {
        grid = new MyHashMap<>();
    }

    public void addPlace(int x, int y, ArrayList<String> services) {
        int gridX = x / 100000;
        int gridY = y / 100000;
        if (!grid.containsKey(gridX)) {
            grid.put(gridX, new MyHashMap<>());
        }
        if (!grid.get(gridX).containsKey(gridY)) {
            grid.get(gridX).put(gridY, new ArrayList<>());
        }
        grid.get(gridX).get(gridY).add(new Place(x, y, services));
    }

    public void editPlace(int x, int y, ArrayList<String> services) {
        int gridX = x / 100000;
        int gridY = y / 100000;
        if (grid.containsKey(gridX) && grid.get(gridX).containsKey(gridY)) {
            ArrayList<Place> places = grid.get(gridX).get(gridY);
            for (Place place : places) {
                if (place.getX() == x && place.getY() == y) {
                    place.setServices(services);
                    return;
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

    public ArrayList<Place> searchPlaces(int minX, int minY, int maxX, int maxY) {
        ArrayList<Place> result = new ArrayList<>();
        int minGridX = minX / 100000;
        int minGridY = minY / 100000;
        int maxGridX = maxX / 100000;
        int maxGridY = maxY / 100000;
        for (int gridX = minGridX; gridX <= maxGridX; gridX++) {
            for (int gridY = minGridY; gridY <= maxGridY; gridY++) {
                if (grid.containsKey(gridX) && grid.get(gridX).containsKey(gridY)) {
                    ArrayList<Place> places = grid.get(gridX).get(gridY);
                    for (Place place : places) {
                        if (place.getX() >= minX && place.getX() <= maxX &&
                                place.getY() >= minY && place.getY() <= maxY) {
                            if(result.size() <= 50){
                                result.add(place);
                            }else{
                                break;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    public ArrayList<Place> searchPlacesByService(int minX, int minY, int maxX, int maxY, String service) {
        ArrayList<Place> result = new ArrayList<>();
        int minGridX = minX / 100000;
        int minGridY = minY / 100000;
        int maxGridX = maxX / 100000;
        int maxGridY = maxY / 100000;
        for (int gridX = minGridX; gridX <= maxGridX; gridX++) {
            for (int gridY = minGridY; gridY <= maxGridY; gridY++) {
                if (grid.containsKey(gridX) && grid.get(gridX).containsKey(gridY)) {
                    ArrayList<Place> places = grid.get(gridX).get(gridY);
                    for (Place place : places) {
                        if (place.getX() >= minX && place.getX() <= maxX &&
                                place.getY() >= minY && place.getY() <= maxY &&
                                place.getServices().contains(service)) {
                            if(result.size() <= 50){
                                result.add(place);
                            }else{
                                break;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    static class Place {
        private int x;
        private int y;
        private ArrayList<String> services;

        public Place(int x, int y, ArrayList<String> services) {
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

        public ArrayList<String> getServices() {
            return services;
        }

        public void setServices(ArrayList<String> services) {
            this.services = services;
        }

        @Override
        public String toString() {
            // Format the information for output
            String servicesString = String.join(", ", services);
            return String.format("X: %d, Y: %d, Services: %s", x, y, servicesString);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Map2D map = new Map2D();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String[] categories = {"ATM", "Hotel", "Restaurant", "Gas Station", "Convenient Store", "School", "Hospital", "Mall", "Coffee Shop", "Movie"};

        for (int i = 0; i < 1000000; i++) {
            int x = random.nextInt(10000000);
            int y = random.nextInt(10000000);
            ArrayList<String> services = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                int index = random.nextInt(categories.length);
                services.add(categories[index]);
            }
            map.addPlace(x, y, services);
            System.out.println("Added!" + i);
        }

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add a place");
            System.out.println("2. Edit a place");
            System.out.println("3. Remove a place");
            System.out.println("4. Search for places");
            System.out.println("5. Search for places by service");
            System.out.println("6. Exit");
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
                    // Start timing using System.currentTimeMillis()
                    long startTime = System.currentTimeMillis();
                    ArrayList<String> services = (ArrayList<String>) Arrays.asList(servicesArray);
                    map.addPlace(x, y, services);
                    // End timing
                    long endTime = System.currentTimeMillis();
                    // Calculate the duration in milliseconds
                    long executionTime = endTime - startTime;
                    System.out.println("Place added successfully!");
                    System.out.println("Execution time: " + executionTime + " ms");
                    break;
                case 2:
                    System.out.print("Enter X coordinate of the place to edit: ");
                    int editX = scanner.nextInt();
                    System.out.print("Enter Y coordinate of the place to edit: ");
                    int editY = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new services (comma-separated): ");
                    String[] newServicesArray = scanner.nextLine().split(",");
                    // Start timing using System.currentTimeMillis()
                    long startTime2 = System.currentTimeMillis();
                    ArrayList<String> newServices = (ArrayList<String>) Arrays.asList(newServicesArray);
                    map.editPlace(editX, editY, newServices);
                    // End timing
                    long endTime2 = System.currentTimeMillis();
                    // Calculate the duration in milliseconds
                    long executionTime2 = endTime2 - startTime2;
                    System.out.println("Place edited successfully!");
                    System.out.println("Execution time: " + executionTime2 + " ms");
                    break;
                case 3:
                    System.out.print("Enter X coordinate of the place to remove: ");
                    int removeX = scanner.nextInt();
                    System.out.print("Enter Y coordinate of the place to remove: ");
                    int removeY = scanner.nextInt();
                    // Start timing using System.currentTimeMillis()
                    long startTime3 = System.currentTimeMillis();
                    map.removePlace(removeX, removeY);
                    // End timing
                    long endTime3 = System.currentTimeMillis();
                    // Calculate the duration in milliseconds
                    long executionTime3 = endTime3 - startTime3;
                    System.out.println("Place removed successfully!");
                    //Print the execution time
                    System.out.println("Execution time: " + executionTime3 + " ms");
                    break;
                case 4:
                    System.out.print("Enter TOP LEFT X coordinate: ");
                    int minX = scanner.nextInt();
                    System.out.print("Enter TOP LEFT Y coordinate: ");
                    int minY = scanner.nextInt();
                    System.out.print("Enter maximum X coordinate: ");
                    int maxX = scanner.nextInt();
                    System.out.print("Enter maximum Y coordinate: ");
                    int maxY = scanner.nextInt();

                    // Start timing using System.currentTimeMillis()
                    long startTime4 = System.currentTimeMillis();

                    ArrayList<Map2D.Place> searchResult = map.searchPlaces(minX, minY, maxX, maxY);
                    // End timing
                    long endTime4 = System.currentTimeMillis();
                    // Calculate the duration in milliseconds
                    long executionTime4 = endTime4 - startTime4;
                    //Output the result
                    if (searchResult.isEmpty()) {
                        System.out.println("No places found in the specified range.");
                    } else {
                        System.out.println("Places found in the specified range:");
                        for (Map2D.Place place : searchResult) {
                            System.out.println(place.toString());
                        }
                    }
                    //Print the execution time
                    System.out.println("Execution time: " + executionTime4 + " ms");
                    break;
                case 5:
                    System.out.print("Enter TOP LEFT X coordinate: ");
                    int minX1 = scanner.nextInt();
                    System.out.print("Enter TOP LEFT Y coordinate: ");
                    int minY1 = scanner.nextInt();
                    System.out.print("Enter maximum X coordinate: ");
                    int maxX1 = scanner.nextInt();
                    System.out.print("Enter maximum Y coordinate: ");
                    int maxY1 = scanner.nextInt();
                    System.out.print("Enter a service: ");
                    String service = scanner.next();

                    // Start timing using System.currentTimeMillis()
                    long startTime41 = System.currentTimeMillis();

                    ArrayList<Map2D.Place> searchResult1 = map.searchPlacesByService(minX1, minY1, maxX1, maxY1, service);
                    // End timing
                    long endTime41 = System.currentTimeMillis();
                    // Calculate the duration in milliseconds
                    long executionTime41 = endTime41 - startTime41;
                    //Output the result
                    if (searchResult1.isEmpty()) {
                        System.out.println("No places found in the specified range.");
                    } else {
                        System.out.println("Places found in the specified range:");
                        for (Map2D.Place place : searchResult1) {
                            System.out.println(place.toString());
                        }
                    }
                    //Print the execution time
                    System.out.println("Execution time: " + executionTime41 + " ms");
                    break;
                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }
}