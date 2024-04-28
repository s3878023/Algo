package TESTING;

import java.util.*;

class Place {
    int x;
    int y;
    Set<String> services;

    public Place(int x, int y, Set<String> services) {
        this.x = x;
        this.y = y;
        this.services = services;
    }
}

class Quadtree {
    private static final int MAX_CAPACITY = 4; // Maximum capacity of each quad
    private Node root;

    private static class Node {
        int x;
        int y;
        int width;
        int height;
        List<Place> places;
        Node[] children;

        public Node(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.places = new ArrayList<>();
            this.children = new Node[4];
        }

        public boolean isLeafNode() {
            return children[0] == null;
        }

        public void split() {
            int subWidth = width / 2;
            int subHeight = height / 2;
            children[0] = new Node(x, y, subWidth, subHeight);
            children[1] = new Node(x + subWidth, y, subWidth, subHeight);
            children[2] = new Node(x, y + subHeight, subWidth, subHeight);
            children[3] = new Node(x + subWidth, y + subHeight, subWidth, subHeight);
        }

        public int getIndex(Place place) {
            int index = -1;
            double verticalMidpoint = x + (width / 2);
            double horizontalMidpoint = y + (height / 2);
            boolean topQuadrant = (place.y < horizontalMidpoint && place.y + place.services.size() <= horizontalMidpoint);
            boolean bottomQuadrant = (place.y > horizontalMidpoint);

            if (place.x < verticalMidpoint && place.x + place.services.size() < verticalMidpoint) {
                if (topQuadrant) {
                    index = 0;
                } else if (bottomQuadrant) {
                    index = 2;
                }
            } else if (place.x > verticalMidpoint) {
                if (topQuadrant) {
                    index = 1;
                } else if (bottomQuadrant) {
                    index = 3;
                }
            }
            return index;
        }
    }

    public Quadtree(int width, int height) {
        root = new Node(0, 0, width, height);
    }

    public void insert(Place place) {
        insert(root, place);
    }

    private void insert(Node node, Place place) {
        if (node.isLeafNode()) {
            node.places.add(place);
            if (node.places.size() > MAX_CAPACITY) {
                node.split();
                reassignPlaces(node);
            }
        } else {
            int index = node.getIndex(place);
            if (index != -1) {
                insert(node.children[index], place);
            }
        }
    }

    private void reassignPlaces(Node node) {
        List<Place> reassign = new ArrayList<>();
        for (Place place : node.places) {
            int index = node.getIndex(place);
            if (index != -1) {
                node.children[index].places.add(place);
            } else {
                reassign.add(place);
            }
        }
        node.places.clear();
        node.places.addAll(reassign);
    }

    public List<Place> search(int topLeftX, int topLeftY, int width, int height, String serviceType, int k) {
        List<Place> result = new ArrayList<>();
        search(root, topLeftX, topLeftY, width, height, serviceType, k, result);
        return result;
    }

    private void search(Node node, int topLeftX, int topLeftY, int width, int height, String serviceType, int k, List<Place> result) {
        if (node == null) return;

        for (Place place : node.places) {
            if (place.x >= topLeftX && place.x <= topLeftX + width &&
                    place.y >= topLeftY && place.y <= topLeftY + height &&
                    place.services.contains(serviceType)) {
                result.add(place);
                if (result.size() == k) {
                    return;
                }
            }
        }

        if (!node.isLeafNode()) {
            for (Node child : node.children) {
                if (child.x <= topLeftX + width && child.x + child.width >= topLeftX &&
                        child.y <= topLeftY + height && child.y + child.height >= topLeftY) {
                    search(child, topLeftX, topLeftY, width, height, serviceType, k, result);
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Quadtree quadtree = new Quadtree(10000, 10000);

        // Add some places
        quadtree.insert(new Place(100, 100, new HashSet<>(Arrays.asList("ATM", "Restaurant"))));
        quadtree.insert(new Place(200, 200, new HashSet<>(Collections.singletonList("Hospital"))));
        quadtree.insert(new Place(300, 300, new HashSet<>(Collections.singletonList("ATM"))));

        // Search for places
        List<Place> searchResult = quadtree.search(0, 0, 400, 400, "Restaurant", 2);
        for (Place place : searchResult) {
            System.out.println("Found Restaurant at: (" + place.x + ", " + place.y + ")");
        }
    }
}
