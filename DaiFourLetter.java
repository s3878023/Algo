import java.util.*;

public class DaiFourLetter {
    private String current;
    private final String target = "RMIT";
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public DaiFourLetter(String current) {
        this.current = current;
    }

    private List<String > getNeighbor(String s) {
        List<String> neighbors = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char[] chars = s.toCharArray();
            char left = (char)(chars[i] == 'A' ? 'Z' : chars[i] - 1);
            char right = (char)(chars[i] == 'A' ? 'Z' : chars[i] + 1);
            chars[i] = left;
            neighbors.add(new String(chars));

            chars[i] = right;
            neighbors.add(new String(chars));
        }
        return neighbors;
    }

    private int transformInside (String start, Set<String> forbidden) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(start);
        visited.add(start);
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                if (current.equals(target)) {
                    return steps;
                }
                List<String> neighbors = getNeighbor(current);
                for (String neighbor : neighbors) {
                    if (!visited.contains(neighbor) && !forbidden.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
            steps++;
        }
        return -1;
    }}
