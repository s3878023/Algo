package SAMPLE_TEST_2;

import java.util.Arrays;
import java.util.Comparator;

class Topic {
    double weight;
    double surfaceArea;

    public Topic(double weight, double surfaceArea) {
        this.weight = weight;
        this.surfaceArea = surfaceArea;
    }
}

public class DaiDoraemonCake {
    private Topic[] topics;
    private double cakeSurfaceArea;

    public DaiDoraemonCake(Topic[] topics, double cakeSurfaceArea) {
        this.topics = topics;
        this.cakeSurfaceArea = cakeSurfaceArea;
    }

    // weightByNumber complexity = O(N * logN)
    public double weightByNumber(int X) {
        // Sort topics by weight in descending order
        Arrays.sort(topics, (t1, t2) -> Double.compare(t2.weight, t1.weight));

        double maxWeight = 0.0;
        for (int i = 0; i < X; i++) {
            maxWeight += topics[i].weight;
        }
        return maxWeight;
    }

    // largestWeight complexity = O(2^N * N)
    public double largestWeight() {
        int n = topics.length;
        double maxWeight = 0.0;
        int bestMask = 0;

        // Iterate over all subsets using bitmasking
        for (int mask = 0; mask < (1 << n); mask++) {
            double currentWeight = 0.0;
            double currentSurface = 0.0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    currentWeight += topics[i].weight;
                    currentSurface += topics[i].surfaceArea;
                }
            }
            if (currentSurface <= cakeSurfaceArea && currentWeight > maxWeight) {
                maxWeight = currentWeight;
                bestMask = mask;
            }
        }

        // Display the indices of the topics selected
        for (int i = 0; i < n; i++) {
            if ((bestMask & (1 << i)) != 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();

        return maxWeight;
    }

    public static void main(String[] args) {
        Topic[] topics = {
                new Topic(8.0, 7.0),
                new Topic(10.0, 8.0),
                new Topic(5.0, 3.0)
        };
        double cakeSurfaceArea = 10.0;

        DaiDoraemonCake doraemonCake = new DaiDoraemonCake(topics, cakeSurfaceArea);

        // Test weightByNumber method
        System.out.printf("Max weight by number (X = 2): %.1f%n", doraemonCake.weightByNumber(2));
        System.out.printf("Max weight by number (X = 1): %.1f%n", doraemonCake.weightByNumber(1));

        // Test largestWeight method
        System.out.printf("Max weight by surface area: %.1f%n", doraemonCake.largestWeight());
    }
}
