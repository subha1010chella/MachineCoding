//import java.util.*;
//
//public class Vehicle {
//    private String id;
//    private List<Location> locations;
//
//    public Vehicle(String id) {
//        this.id = id;
//        this.locations = new ArrayList<>();
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public List<Location> getLocations() {
//        return locations;
//    }
//
//    public void addLocation(Location loc) {
//        locations.add(loc);
//    }
//}


public class Vehicle {
    private final String vehicleId;
    private double prevLat, prevLon;
    private double currLat, currLon;
    private double totalDistance;

    public Vehicle(String vehicleId) {
        this.vehicleId = vehicleId;
        this.totalDistance = 0.0;
    }

    public void updateLocation(double newLat, double newLon) {
        if (this.currLat != 0 || this.currLon != 0) {
            this.prevLat = this.currLat;
            this.prevLon = this.currLon;
            this.totalDistance += haversine(this.prevLat, this.prevLon, newLat, newLon);
        }
        this.currLat = newLat;
        this.currLon = newLon;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        final double R = 6371.0; // Radius of Earth in km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}
