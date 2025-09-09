// Question - 4

public class SmartDevice {
    private String deviceName;
    private String location;
    private boolean isOnline;
    private double powerConsumption;
    private String[] connectedDevices;
    private int connectionCount;

    // Constructor with parameter names matching field names
    public SmartDevice(String deviceName, String location,
                       boolean isOnline, double powerConsumption) {
        this.deviceName = deviceName;            // Disambiguate with this
        this.location = location;
        this.isOnline = isOnline;
        this.powerConsumption = powerConsumption;
        this.connectedDevices = new String[5];   // Array of size 5
        this.connectionCount = 0;
        System.out.println(this.deviceName + " device created.");
    }

    // Method using this for parameter disambiguation
    public void updateLocation(String location) {
        this.location = location;  // Assign parameter to field
        System.out.println(this.deviceName + " moved to " + this.location);
    }

    public void updatePowerConsumption(double powerConsumption) {
        this.powerConsumption = powerConsumption; // Disambiguation
        System.out.println("Power consumption updated for " + this.deviceName);
    }

    // Method returning this for chaining
    public SmartDevice setOnline(boolean isOnline) {
        this.isOnline = isOnline;
        return this;
    }

    public SmartDevice connectToDevice(String deviceName) {
        if (this.connectionCount < this.connectedDevices.length) {
            this.connectedDevices[this.connectionCount] = deviceName;
            this.connectionCount++;
            System.out.println(this.deviceName + " connected to " + deviceName);
        } else {
            System.out.println(this.deviceName + " cannot connect to more devices!");
        }
        return this; // Enable method chaining
    }

    public SmartDevice rename(String deviceName) {
        String oldName = this.deviceName;
        this.deviceName = deviceName;
        System.out.println("Device renamed from " + oldName + " to " + this.deviceName);
        return this;
    }

    public void displayDeviceInfo() {
        System.out.println("\n=== " + this.deviceName + " INFO ===");
        System.out.println("Location: " + this.location);
        System.out.println("Status: " + (this.isOnline ? "Online" : "Offline"));
        System.out.println("Power: " + this.powerConsumption + "W");
        System.out.println("Connections: " + this.connectionCount);
        for (int i = 0; i < this.connectionCount; i++) {
            System.out.println(" -> " + this.connectedDevices[i]);
        }
    }

    // Method that calls other methods using this, now returns this for chaining
    public SmartDevice performInitialSetup() {
        this.setOnline(true);
        System.out.println(this.deviceName + " initial setup completed");
        return this;  // enable chaining
    }

    public static void main(String[] args) {
        System.out.println("=== SMART HOME DEVICE NETWORK ===");

        // Create devices with parameter names matching field names
        SmartDevice device1 = new SmartDevice("Living Room Hub", "Living Room", false, 15.5);
        SmartDevice device2 = new SmartDevice("Bedroom Sensor", "Bedroom", false, 5.0);

        // Demonstrate this keyword usage and method chaining
        device1.setOnline(true)
               .connectToDevice("Alexa")
               .connectToDevice("Smart Light")
               .rename("Main Hub")
               .updateLocation("Hallway");

        device2.performInitialSetup()
               .connectToDevice("Smart AC")
               .connectToDevice("Smart Heater")
               .updatePowerConsumption(6.0);

        // Display final status
        device1.displayDeviceInfo();
        device2.displayDeviceInfo();
    }
}
