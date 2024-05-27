import java.util.Arrays;

public class DaiStreetNetwork {
    private int[][] distance;

    public DaiStreetNetwork(int[][] distance) {
        this.distance = distance;
    }
    //Time complexity: O(n)
    public int nearestNeighbour(int placeIndex){
        int nearest = -1;
        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < distance[placeIndex].length; i++) {
            if (distance[placeIndex][i] > 0 && distance[placeIndex][i] < minDistance) {
                minDistance = distance[placeIndex][i];
                nearest = i;
            }
        }
        return nearest;
    }
    private int minDistance(int[] distance, boolean[] visited){
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < distance.length; v++) {
            if (!visited[v] && distance[v] <= min) {
                min = distance[v];
                min_index = v;
            }
        }return min_index;
    }
    private void printPathway(int[] distance, int target) {
        System.out.print("the closest Pathway from home to school is:");
        for (int i = 0; i <= target; i++) {
            if (distance[i] != Integer.MAX_VALUE) {
                System.out.print(i + " =====> ");
            }
        }
        System.out.println("no where to go");
    }
    //timeComplexity: O(n^2)
    public void shortestToSchool() {
        int N = distance.length;
        int[] dist = new int[N];
        boolean[] visited = new boolean[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        for (int count = 0; count < N - 1; count++) {
            int u  = minDistance(dist, visited);
            visited[u] = true;
            for (int v = 0; v < N; v++) {
                if (!visited[v] && distance[u][v] > 0 && dist[u] != Integer.MAX_VALUE && dist[u] + distance[u][v] < dist[v]){
                    dist[v] = dist[u] + distance[u][v];
                }
            }
        }
        System.out.println("Shortest path to school (RMIT) is: " + dist[N-1]);
        printPathway(dist, N-1);
    }

    public static void main(String[] args) {
        int[][] testnetwork = {{0, -1, 5, 10}, {-1, 0, 4, 2}, {-1, -1, 0, 4}, {3, -1, 7, 0}} ;
        DaiStreetNetwork streetNetwork = new DaiStreetNetwork(testnetwork);
        System.out.println("Nearest neighbour is: " + streetNetwork.nearestNeighbour(0));
        streetNetwork.shortestToSchool();
    }
}
