// File: Assignment_7_akhil.java
// Author: Bangaru Chaitanya Venkata Sai Akhil

import java.util.*;

class Vehicle {
    String vehicleId;
    String brand;
    String model;
    int year;
    double mileage;
    String fuelType;
    String currentStatus; // Available, Assigned, Maintenance
    Driver assignedDriver;

    // Static variables
    static int totalVehicles = 0;
    static double fleetValue = 0;
    static String companyName = "OpenAI Transport Co.";
    static double totalFuelConsumption = 0;

    public Vehicle(String vehicleId, String brand, String model, int year, double mileage, String fuelType, double value) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.fuelType = fuelType;
        this.currentStatus = "Available";
        totalVehicles++;
        fleetValue += value;
    }

    public void assignDriver(Driver driver) {
        if (assignedDriver == null) {
            this.assignedDriver = driver;
            driver.assignedVehicle = this;
            this.currentStatus = "Assigned";
            System.out.println("✅ Driver " + driver.driverName + " assigned to vehicle " + vehicleId);
        } else {
            System.out.println("❌ Vehicle already has a driver assigned.");
        }
    }

    public void scheduleMaintenance() {
        this.currentStatus = "Maintenance";
        System.out.println("🛠 Vehicle " + vehicleId + " scheduled for maintenance.");
    }

    public void updateMileage(double distance, double fuelUsed) {
        this.mileage += distance;
        totalFuelConsumption += fuelUsed;
        if (assignedDriver != null) assignedDriver.totalTrips++;
        System.out.println("📍 Vehicle " + vehicleId + " ran " + distance + " km. Fuel used: " + fuelUsed);
    }

    public double calculateRunningCost(double fuelPricePerLiter, double maintenanceCostPerKm) {
        return (totalFuelConsumption * fuelPricePerLiter) + (mileage * maintenanceCostPerKm);
    }

    public boolean checkServiceDue() {
        if (mileage % 10000 < 500) { // service every 10,000 km
            System.out.println("⚠ Vehicle " + vehicleId + " service due soon.");
            return true;
        }
        return false;
    }

    // Static reporting
    public static void getFleetUtilization(Vehicle[] vehicles) {
        int assigned = 0, available = 0, maintenance = 0;
        for (Vehicle v : vehicles) {
            if (v != null) {
                switch (v.currentStatus) {
                    case "Assigned": assigned++; break;
                    case "Available": available++; break;
                    case "Maintenance": maintenance++; break;
                }
            }
        }
        System.out.println("\n=== Fleet Utilization Report ===");
        System.out.println("Total Vehicles: " + totalVehicles);
        System.out.println("Assigned: " + assigned + ", Available: " + available + ", Maintenance: " + maintenance);
    }

    public static double calculateTotalMaintenanceCost(Vehicle[] vehicles, double costPerKm) {
        double total = 0;
        for (Vehicle v : vehicles) {
            if (v != null) total += v.mileage * costPerKm;
        }
        return total;
    }

    public static void getVehiclesByType(Vehicle[] vehicles, String type) {
        System.out.println("\n=== Vehicles of type: " + type + " ===");
        for (Vehicle v : vehicles) {
            if (v != null && v.getClass().getSimpleName().equals(type)) {
                System.out.println(v.vehicleId + " - " + v.brand + " " + v.model + " | Mileage: " + v.mileage);
            }
        }
    }
}

// Subclasses
class Car extends Vehicle {
    int seatingCapacity;

    public Car(String vehicleId, String brand, String model, int year, double mileage, String fuelType, double value, int seatingCapacity) {
        super(vehicleId, brand, model, year, mileage, fuelType, value);
        this.seatingCapacity = seatingCapacity;
    }
}

class Bus extends Vehicle {
    int seatingCapacity;

    public Bus(String vehicleId, String brand, String model, int year, double mileage, String fuelType, double value, int seatingCapacity) {
        super(vehicleId, brand, model, year, mileage, fuelType, value);
        this.seatingCapacity = seatingCapacity;
    }
}

class Truck extends Vehicle {
    double loadCapacity; // in tons

    public Truck(String vehicleId, String brand, String model, int year, double mileage, String fuelType, double value, double loadCapacity) {
        super(vehicleId, brand, model, year, mileage, fuelType, value);
        this.loadCapacity = loadCapacity;
    }
}

// Driver class
class Driver {
    String driverId;
    String driverName;
    String licenseType;
    Vehicle assignedVehicle;
    int totalTrips;

    public Driver(String driverId, String driverName, String licenseType) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.licenseType = licenseType;
        this.totalTrips = 0;
    }
}

public class Assignment_7_akhil {
    public static void main(String[] args) {
        // Create vehicles
        Vehicle v1 = new Car("V001", "Toyota", "Corolla", 2020, 15000, "Petrol", 12000, 5);
        Vehicle v2 = new Bus("V002", "Volvo", "B9R", 2019, 40000, "Diesel", 50000, 50);
        Vehicle v3 = new Truck("V003", "Tata", "HeavyDuty", 2021, 20000, "Diesel", 30000, 15);

        Vehicle[] fleet = {v1, v2, v3};

        // Create drivers
        Driver d1 = new Driver("D001", "Akhil", "Car");
        Driver d2 = new Driver("D002", "Ravi", "Bus");

        // Assign drivers
        v1.assignDriver(d1);
        v2.assignDriver(d2);

        // Update mileage with trips
        v1.updateMileage(200, 20);
        v2.updateMileage(500, 60);
        v3.updateMileage(800, 100);

        // Check service
        v2.checkServiceDue();

        // Schedule maintenance
        v3.scheduleMaintenance();

        // Reports
        Vehicle.getFleetUtilization(fleet);
        System.out.println("\nTotal Maintenance Cost: " + Vehicle.calculateTotalMaintenanceCost(fleet, 0.5));
        Vehicle.getVehiclesByType(fleet, "Bus");
    }
}
