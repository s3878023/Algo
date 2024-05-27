package Selecting_Menu_System;
import java.util.ArrayList;
import java.util.List;

public class Map2D {
    public static void main(String[] args) {
        // Create a Map2D instance
        Map2D map = new Map2D();

        // Add some places to the map
        map.add(10, 20, List.of("Restaurant"));
        map.add(30, 40, List.of("Hospital"));
        map.add(50, 60, List.of("Park"));
        map.add(70, 80, List.of("School"));

        // Search for places within a bounding rectangle
        List<Place> result = map.search(0, 0, 100, 100);
        System.out.println("Places within the bounding rectangle:");
        for (Place place : result) {
            System.out.println("Place: (" + place.x + ", " + place.y + ")");
        }

        // Edit a place's services
        map.edit(30, 40, List.of("Pharmacy"));

        // Search again after editing
        System.out.println("\nPlaces within the bounding rectangle after editing:");
        result = map.search(0, 0, 100, 100);
        for (Place place : result) {
            System.out.println("Place: (" + place.x + ", " + place.y + ")");
        }

        // Remove a place
        map.remove(70, 80);

        // Search again after removal
        System.out.println("\nPlaces within the bounding rectangle after removal:");
        result = map.search(0, 0, 100, 100);
        for (Place place : result) {
            System.out.println("Place: (" + place.x + ", " + place.y + ")");
        }
    }
    private KDNode root;

    public Map2D() {
        this.root = null;
    }

    public void add(int x, int y, List<String> services) {
        insert(new Place(x, y, services));
    }

    public void edit(int x, int y, List<String> services) {
        remove(x, y);
        add(x, y, services);
    }

    public void remove(int x, int y) {
        root = removeRec(root, x, y, 0);
    }

    private KDNode removeRec(KDNode root, int x, int y, int depth) {
        if (root == null) {
            return null;
        }

        int currentDimension = depth % 2; // Assuming 2D space
        Place place = root.place;

        if (place.x == x && place.y == y) {
            if (root.right != null) {
                KDNode min = findMin(root.right, currentDimension, depth + 1);
                root.place = min.place;
                root.right = removeRec(root.right, min.place.x, min.place.y, depth + 1);
            } else if (root.left != null) {
                KDNode min = findMin(root.left, currentDimension, depth + 1);
                root.place = min.place;
                root.right = removeRec(root.right, min.place.x, min.place.y, depth + 1);
            } else {
                return null;
            }
        } else {
            if (currentDimension == 0) {
                if (x < place.x) {
                    root.left = removeRec(root.left, x, y, depth + 1);
                } else {
                    root.right = removeRec(root.right, x, y, depth + 1);
                }
            } else {
                if (y < place.y) {
                    root.left = removeRec(root.left, x, y, depth + 1);
                } else {
                    root.right = removeRec(root.right, x, y, depth + 1);
                }
            }
        }

        return root;
    }

    private KDNode findMin(KDNode node, int dim, int depth) {
        if (node == null) {
            return null;
        }

        int currentDimension = depth % 2;
        if (currentDimension == dim) {
            if (node.left == null) {
                return node;
            } else {
                return findMin(node.left, dim, depth + 1);
            }
        } else {
            KDNode leftMin = findMin(node.left, dim, depth + 1);
            KDNode rightMin = findMin(node.right, dim, depth + 1);
            if (leftMin == null && rightMin == null) {
                return node;
            } else if (leftMin == null) {
                return rightMin;
            } else if (rightMin == null) {
                return leftMin;
            } else {
                if (dim == 0) {
                    if (leftMin.place.x < rightMin.place.x) {
                        return leftMin;
                    } else {
                        return rightMin;
                    }
                } else {
                    if (leftMin.place.y < rightMin.place.y) {
                        return leftMin;
                    } else {
                        return rightMin;
                    }
                }
            }
        }
    }

    public List<Place> search(int x, int y, int width, int height) {
        List<Place> result = new ArrayList<>();
        searchRec(root, x, y, width, height, 0, result);
        return result;
    }

    private void searchRec(KDNode root, int x, int y, int width, int height, int depth, List<Place> result) {
        if (root == null) {
            return;
        }

        int currentDimension = depth % 2; // Assuming 2D space
        Place place = root.place;

        if (place.x >= x && place.x <= x + width && place.y >= y && place.y <= y + height) {
            result.add(place);
        }

        if (currentDimension == 0) {
            if (place.x >= x) {
                searchRec(root.left, x, y, width, height, depth + 1, result);
            }
            if (place.x <= x + width) {
                searchRec(root.right, x, y, width, height, depth + 1, result);
            }
        } else {
            if (place.y >= y) {
                searchRec(root.left, x, y, width, height, depth + 1, result);
            }
            if (place.y <= y + height) {
                searchRec(root.right, x, y, width, height, depth + 1, result);
            }
        }
    }

    private void insert(Place place) {
        this.root = insertRec(this.root, place, 0);
    }

    private KDNode insertRec(KDNode root, Place place, int depth) {
        if (root == null) {
            return new KDNode(place);
        }

        int currentDimension = depth % 2; // Assuming 2D space
        if (currentDimension == 0) {
            if (place.x < root.place.x) {
                root.left = insertRec(root.left, place, depth + 1);
            } else {
                root.right = insertRec(root.right, place, depth + 1);
            }
        } else {
            if (place.y < root.place.y) {
                root.left = insertRec(root.left, place, depth + 1);
            } else {
                root.right = insertRec(root.right, place, depth + 1);
            }
        }

        return root;
    }
    class KDNode {
        Place place;
        KDNode left;
        KDNode right;

        public KDNode(Place place) {
            this.place = place;
            this.left = null;
            this.right = null;
        }
    }

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

}
