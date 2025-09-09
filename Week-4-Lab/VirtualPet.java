// Question - 1
import java.util.Random;

public class VirtualPet {
    private final String petId;
    private String petName;
    private String species;
    private int age;
    private int happiness;
    private int health;
    private String evolutionStage;

    private static final String[] EVOLUTION_STAGES = {"Egg", "Baby", "Child", "Teen", "Adult", "Elder"};
    private static int totalPetsCreated = 0;
    private static Random rand = new Random();

    // Full constructor
    public VirtualPet(String petName, String species, int age, int happiness, int health, String evolutionStage) {
        this.petId = generatePetId();
        this.petName = petName;
        this.species = species;
        this.age = age;
        this.happiness = happiness;
        this.health = health;
        this.evolutionStage = evolutionStage;
        totalPetsCreated++;
    }

    // Constructor with name and species
    public VirtualPet(String petName, String species) {
        this(petName, species, 1, 50, 50, EVOLUTION_STAGES[2]); // Child stage
    }

    // Constructor with name only
    public VirtualPet(String petName) {
        String randomSpecies = "Species-" + (rand.nextInt(100) + 1);
        this.petName = petName;
        this.species = randomSpecies;
        this.age = 0;
        this.happiness = 50;
        this.health = 50;
        this.evolutionStage = EVOLUTION_STAGES[1]; // Baby stage
        this.petId = generatePetId();
        totalPetsCreated++;
    }

    // Default constructor
    public VirtualPet() {
        this("Mysterious Egg", "Unknown", 0, 50, 50, EVOLUTION_STAGES[0]);
    }

    // Unique ID generator
    private static String generatePetId() {
        return "PET-" + System.currentTimeMillis() + "-" + (++totalPetsCreated);
    }

    // Evolution logic
    public void evolvePet() {
        if (health <= 0) {
            this.evolutionStage = "Ghost";
            System.out.println(petName + " has died and is now a Ghost!");
            return;
        }
        if (age < 1) evolutionStage = EVOLUTION_STAGES[0];
        else if (age < 3) evolutionStage = EVOLUTION_STAGES[1];
        else if (age < 6) evolutionStage = EVOLUTION_STAGES[2];
        else if (age < 10) evolutionStage = EVOLUTION_STAGES[3];
        else if (age < 15) evolutionStage = EVOLUTION_STAGES[4];
        else evolutionStage = EVOLUTION_STAGES[5];
    }

    // Feed, play, heal
    public void feedPet(int foodAmount) {
        happiness += foodAmount / 2;
        health += foodAmount / 3;
        if (happiness > 100) happiness = 100;
        if (health > 100) health = 100;
    }

    public void playWithPet(int playTime) {
        happiness += playTime;
        health -= playTime / 2;
        if (happiness > 100) happiness = 100;
        if (health < 0) health = 0;
    }

    public void healPet(int healAmount) {
        health += healAmount;
        if (health > 100) health = 100;
    }

    // Simulate a day
    public void simulateDay() {
        age++;
        int randomEffect = rand.nextInt(20) - 10; // -10 to +9
        happiness += randomEffect;
        health += randomEffect / 2;
        if (happiness > 100) happiness = 100;
        if (happiness < 0) happiness = 0;
        if (health > 100) health = 100;
        if (health < 0) health = 0;
        evolvePet();
    }

    public String getPetStatus() {
        return evolutionStage;
    }

    public void displayPetInfo() {
        System.out.println("\n=== " + petName + " INFO ===");
        System.out.println("ID: " + petId);
        System.out.println("Species: " + species);
        System.out.println("Age: " + age);
        System.out.println("Happiness: " + happiness);
        System.out.println("Health: " + health);
        System.out.println("Stage: " + evolutionStage);
    }

    public static void main(String[] args) {
        System.out.println("=== VIRTUAL PET DAYCARE SIMULATION ===");

        VirtualPet pet1 = new VirtualPet(); // Egg
        VirtualPet pet2 = new VirtualPet("Fluffy"); // Baby
        VirtualPet pet3 = new VirtualPet("Sparky", "Dragon"); // Child
        VirtualPet pet4 = new VirtualPet("Buddy", "Dog", 7, 80, 70, EVOLUTION_STAGES[3]); // Teen

        VirtualPet[] daycare = {pet1, pet2, pet3, pet4};

        // Simulate 5 days
        for (int day = 1; day <= 5; day++) {
            System.out.println("\n--- Day " + day + " ---");
            for (VirtualPet pet : daycare) {
                pet.simulateDay();
                pet.feedPet(5);
                pet.playWithPet(3);
                pet.healPet(2);
                pet.displayPetInfo();
            }
        }
    }
}
