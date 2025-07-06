// DistanceCalculator.java
public class DistanceCalculator {
    public static double calculate(Location a, Location b) {
        double dx = a.getLat() - b.getLat();
        double dy = a.getLon() - b.getLon();
        return Math.sqrt(dx * dx + dy * dy); // Euclidean, or Haversine if needed
    }
}
