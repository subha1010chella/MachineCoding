//// VehicleService.java
//public class VehicleService {
//    private VehicleRepository repo = new VehicleRepository();
//
//    public String registerVehicle(String id) {
//        if (repo.exists(id)) return "Vehicle already exists";
//        repo.save(new Vehicle(id));
//        return "Vehicle registered";
//    }
//
//    public String updateLocation(String id, double lat, double lon, long timestamp) {
//        Vehicle v = repo.get(id);
//        if (v == null) return "Vehicle not found";
//
//        v.addLocation(new Location(lat, lon, timestamp));
//        return "Location updated";
//    }
//
//    public double getDistanceTravelled(String id) {
//        Vehicle v = repo.get(id);
//        if (v == null) return -1;
//
//        double total = 0.0;
//        var locs = v.getLocations();
//        for (int i = 1; i < locs.size(); i++) {
//            total += DistanceCalculator.calculate(locs.get(i - 1), locs.get(i));
//        }
//        return total;
//    }
//}
