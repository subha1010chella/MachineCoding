// Location.java
public class Location {
    private double latitude;
    private double longitude;
    private long timestamp;

    public Location(double latitude, double longitude, long timestamp) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
    }

    public double getLat() { return latitude; }
    public double getLon() { return longitude; }
}
