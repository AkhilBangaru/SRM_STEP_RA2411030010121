// Question - 3

public class AudioMixer {
    private String mixerModel;
    private int numberOfChannels;
    private boolean hasBluetoothConnectivity;
    private double maxVolumeDecibels;
    private String[] connectedDevices;
    private int deviceCount;

    // No-argument constructor using this() chaining
    public AudioMixer() {
        this("StandardMix-8", 8, false);  // Calls 3-parameter constructor
        System.out.println("No-argument constructor executed.");
    }

    // Two-parameter constructor using this() chaining
    public AudioMixer(String mixerModel, int numberOfChannels) {
        this(mixerModel, numberOfChannels, false);  // Calls 3-parameter constructor
        System.out.println("Two-parameter constructor executed.");
    }

    // Three-parameter constructor using this() chaining
    public AudioMixer(String mixerModel, int numberOfChannels, boolean hasBluetoothConnectivity) {
        this(mixerModel, numberOfChannels, hasBluetoothConnectivity, 120.0); // Calls full constructor
        System.out.println("Three-parameter constructor executed.");
    }

    // Main constructor - all parameters
    public AudioMixer(String mixerModel, int numberOfChannels,
                      boolean hasBluetoothConnectivity, double maxVolumeDecibels) {
        this.mixerModel = mixerModel;
        this.numberOfChannels = numberOfChannels;
        this.hasBluetoothConnectivity = hasBluetoothConnectivity;
        this.maxVolumeDecibels = maxVolumeDecibels;

        // Initialize connectedDevices array based on numberOfChannels
        this.connectedDevices = new String[numberOfChannels];
        this.deviceCount = 0;

        System.out.println("Full constructor executed for model: " + mixerModel);
    }

    public void connectDevice(String deviceName) {
        if (deviceCount < connectedDevices.length) {
            connectedDevices[deviceCount] = deviceName;
            deviceCount++;
            System.out.println("Connected: " + deviceName);
        } else {
            System.out.println("All channels occupied!");
        }
    }

    public void displayMixerStatus() {
        System.out.println("\n=== " + mixerModel + " STATUS ===");
        System.out.println("Channels: " + numberOfChannels);
        System.out.println("Bluetooth: " + (hasBluetoothConnectivity ? "Enabled" : "Disabled"));
        System.out.println("Max Volume: " + maxVolumeDecibels + " dB");
        System.out.println("Connected Devices: " + deviceCount + "/" + numberOfChannels);
        for (int i = 0; i < deviceCount; i++) {
            System.out.println(" Channel " + (i + 1) + ": " + connectedDevices[i]);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== MUSIC STUDIO SETUP ===");

        // Create mixer using no-argument constructor
        AudioMixer mixer1 = new AudioMixer();

        // Create mixer using two-parameter constructor
        AudioMixer mixer2 = new AudioMixer("ProMix-16", 16);

        // Create mixer using three-parameter constructor
        AudioMixer mixer3 = new AudioMixer("BlueMix-12", 12, true);

        // Create mixer using full constructor
        AudioMixer mixer4 = new AudioMixer("MegaMix-24", 24, true, 150.0);

        // Connect different devices to each mixer
        mixer1.connectDevice("Microphone");
        mixer1.connectDevice("Guitar");

        mixer2.connectDevice("Keyboard");
        mixer2.connectDevice("Drum Machine");

        mixer3.connectDevice("Laptop");
        mixer3.connectDevice("Tablet");
        mixer3.connectDevice("Phone");

        mixer4.connectDevice("DJ Controller");
        mixer4.connectDevice("Synthesizer");

        // Display status of all mixers
        mixer1.displayMixerStatus();
        mixer2.displayMixerStatus();
        mixer3.displayMixerStatus();
        mixer4.displayMixerStatus();

        // Comment on constructor chaining execution order
        System.out.println("\n=== Constructor Chaining Explanation ===");
        System.out.println("No-argument -> 3-parameter -> Full constructor");
        System.out.println("Two-parameter -> 3-parameter -> Full constructor");
        System.out.println("Three-parameter -> Full constructor");
        System.out.println("Full constructor runs directly when called.");
    }
}
