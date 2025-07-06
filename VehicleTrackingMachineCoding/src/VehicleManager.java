import java.util.HashMap;
import java.util.Map;

public class VehicleManager {
    private final Map<String, Vehicle> vehicleMap = new HashMap<>();

    public void registerVehicle(String vehicleId) {
        if (vehicleMap.containsKey(vehicleId)) {
            System.out.println("Vehicle already registered.");
        } else {
            vehicleMap.put(vehicleId, new Vehicle(vehicleId));
            System.out.println("Vehicle registered successfully.");
        }
    }

    public void updateLocation(String vehicleId, double lat, double lon) {
        Vehicle vehicle = vehicleMap.get(vehicleId);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }
        vehicle.updateLocation(lat, lon);
        System.out.println("Location updated.");
    }

    public void getDistanceTravelled(String vehicleId) {
        Vehicle vehicle = vehicleMap.get(vehicleId);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
        } else {
            System.out.printf("Total distance: %.2f km%n", vehicle.getTotalDistance());
        }
    }
}
