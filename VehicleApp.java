
import java.util.*;

interface Vehicle {
    String getMake();
    String getModel();
    int getYear();
}

interface CarVehicle {
    int getNumberOfDoors();
    FuelType getFuelType();
}

interface MotorVehicle {
    int getNumberOfWheels();
    MotoType getMotorcycleType();
}

interface TruckVehicle {
    double getCargoCapacityTons();
    TransmissionType getTransmissionType();
}

enum FuelType { PETROL, DIESEL, ELECTRIC }
enum MotoType { SPORT, CRUISER, OFF_ROAD }
enum TransmissionType { MANUAL, AUTOMATIC }

final class Car implements Vehicle, CarVehicle {
    private final String make, model;
    private final int year, numberOfDoors;
    private final FuelType fuelType;

    Car(String make, String model, int year, int numberOfDoors, FuelType fuelType) {
        this.make = make; this.model = model; this.year = year;
        this.numberOfDoors = numberOfDoors; this.fuelType = fuelType;
    }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public int getNumberOfDoors() { return numberOfDoors; }
    public FuelType getFuelType() { return fuelType; }

    @Override public String toString() {
        return String.format("Car  | %s %s (%d), %d doors, fuel=%s",
                make, model, year, numberOfDoors, fuelType);
    }
}

final class Motorcycle implements Vehicle, MotorVehicle {
    private final String make, model;
    private final int year, numberOfWheels;
    private final MotoType type;

    Motorcycle(String make, String model, int year, int numberOfWheels, MotoType type) {
        this.make = make; this.model = model; this.year = year;
        this.numberOfWheels = numberOfWheels; this.type = type;
    }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public int getNumberOfWheels() { return numberOfWheels; }
    public MotoType getMotorcycleType() { return type; }

    @Override public String toString() {
        return String.format("Moto | %s %s (%d), %d wheels, type=%s",
                make, model, year, numberOfWheels, type);
    }
}

final class Truck implements Vehicle, TruckVehicle {
    private final String make, model;
    private final int year;
    private final double cargoCapacityTons;
    private final TransmissionType transmissionType;

    Truck(String make, String model, int year, double cargoCapacityTons, TransmissionType transmissionType) {
        this.make = make; this.model = model; this.year = year;
        this.cargoCapacityTons = cargoCapacityTons; this.transmissionType = transmissionType;
    }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public double getCargoCapacityTons() { return cargoCapacityTons; }
    public TransmissionType getTransmissionType() { return transmissionType; }

    @Override public String toString() {
        return String.format("Truck| %s %s (%d), capacity=%.2f tons, transmission=%s",
                make, model, year, cargoCapacityTons, transmissionType);
    }
}

public class VehicleApp {
    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        List<Vehicle> fleet = new ArrayList<>();
        System.out.println("=== Vehicle Information System ===");

        try {
            fleet.add(readCar());
            fleet.add(readMotorcycle());
            fleet.add(readTruck());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            return; // fail fast if something fatal occurs
        }

        System.out.println("\n--- Fleet Summary ---");
        for (Vehicle v : fleet) {
            // Polymorphism: dynamic toString based on actual type
            System.out.println(v.toString());
        }
    }

    // ---------- Input helpers (validation & reprompting) ----------
    private static String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = SC.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    private static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int n = Integer.parseInt(SC.nextLine().trim());
                if (n < min || n > max) {
                    System.out.printf("Enter a number between %d and %d.%n", min, max);
                    continue;
                }
                return n;
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static double readDouble(String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            try {
                double d = Double.parseDouble(SC.nextLine().trim());
                if (d < min || d > max) {
                    System.out.printf("Enter a number between %.2f and %.2f.%n", min, max);
                    continue;
                }
                return d;
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static <E extends Enum<E>> E readEnum(String prompt, Class<E> enumType) {
        String options = Arrays.toString(enumType.getEnumConstants());
        while (true) {
            System.out.printf("%s %s: ", prompt, options);
            String raw = SC.nextLine().trim().replace('-', '_').replace(' ', '_').toUpperCase();
            try {
                return Enum.valueOf(enumType, raw);
            } catch (IllegalArgumentException ex) {
                System.out.println("Invalid choice. Please use one of the listed options.");
            }
        }
    }

    // ---------- Builders for each vehicle ----------
    private static Car readCar() {
        System.out.println("\nEnter Car details:");
        String make = readNonEmpty("Make: ");
        String model = readNonEmpty("Model: ");
        int year = readInt("Year (1990-2035): ", 1990, 2035);
        int doors = readInt("Number of doors (2-6): ", 2, 6);
        FuelType fuel = readEnum("Fuel type", FuelType.class);
        return new Car(make, model, year, doors, fuel);
    }

    private static Motorcycle readMotorcycle() {
        System.out.println("\nEnter Motorcycle details:");
        String make = readNonEmpty("Make: ");
        String model = readNonEmpty("Model: ");
        int year = readInt("Year (1990-2035): ", 1990, 2035);
        int wheels = readInt("Number of wheels (2-4): ", 2, 4);
        MotoType type = readEnum("Motorcycle type", MotoType.class);
        return new Motorcycle(make, model, year, wheels, type);
    }

    private static Truck readTruck() {
        System.out.println("\nEnter Truck details:");
        String make = readNonEmpty("Make: ");
        String model = readNonEmpty("Model: ");
        int year = readInt("Year (1990-2035): ", 1990, 2035);
        double capacity = readDouble("Cargo capacity in tons (0.5-40): ", 0.5, 40.0);
        TransmissionType trans = readEnum("Transmission", TransmissionType.class);
        return new Truck(make, model, year, capacity, trans);
    }
}
