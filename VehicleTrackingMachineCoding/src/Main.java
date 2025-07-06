//// Main.java
//public class Main {
//    public static void main(String[] args) {
//        VehicleService service = new VehicleService();
//
//        System.out.println(service.registerVehicle("V1"));
//        System.out.println(service.updateLocation("V1", 10.0, 20.0, System.currentTimeMillis()));
//        System.out.println(service.updateLocation("V1", 13.0, 24.0, System.currentTimeMillis()));
//        System.out.println("Distance: " + service.getDistanceTravelled("V1"));
//    }
//}

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        VehicleManager manager = new VehicleManager();
        Scanner sc = new Scanner(System.in);

        System.out.println("Vehicle Tracking System");
        System.out.println("Commands:");
        System.out.println("  register <vehicleId>");
        System.out.println("  update <vehicleId> <latitude> <longitude>");
        System.out.println("  distance <vehicleId>");
        System.out.println("  exit");

        while (true) {
            System.out.print("> ");
            String[] input = sc.nextLine().trim().split(" ");

            if (input.length == 0) continue;

            String cmd = input[0];

            switch (cmd.toLowerCase()) {
                case "register":
                    if (input.length != 2) System.out.println("Usage: register <vehicleId>");
                    else manager.registerVehicle(input[1]);
                    break;

                case "update":
                    if (input.length != 4) System.out.println("Usage: update <vehicleId> <lat> <lon>");
                    else {
                        try {
                            double lat = Double.parseDouble(input[2]);
                            double lon = Double.parseDouble(input[3]);
                            manager.updateLocation(input[1], lat, lon);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid coordinates.");
                        }
                    }
                    break;

                case "distance":
                    if (input.length != 2) System.out.println("Usage: distance <vehicleId>");
                    else manager.getDistanceTravelled(input[1]);
                    break;

                case "exit":
                    System.out.println("Exiting.");
                    sc.close();
                    return;

                default:
                    System.out.println("Unknown command.");
            }
        }
    }
}
