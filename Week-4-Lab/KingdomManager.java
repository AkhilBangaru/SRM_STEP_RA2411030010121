// Question - 2

abstract class MagicalStructure {
    protected String structureName;
    protected int magicPower;
    protected String location;
    protected boolean isActive;

    // Constructor chaining
    public MagicalStructure(String structureName) {
        this(structureName, 50, "Unknown", true); // default values
    }

    public MagicalStructure(String structureName, int magicPower, String location, boolean isActive) {
        this.structureName = structureName;
        this.magicPower = magicPower;
        this.location = location;
        this.isActive = isActive;
        System.out.println(this.structureName + " created at " + this.location);
    }

    // Abstract method
    public abstract void castMagicSpell();
}

// WizardTower class
class WizardTower extends MagicalStructure {
    private int spellCapacity;
    private String[] knownSpells;

    public WizardTower(String name) {
        this(name, 100, "Tower Hill", true, 5, new String[]{"Fireball"});
    }

    public WizardTower(String name, int spellCapacity, String[] knownSpells) {
        this(name, 100, "Tower Hill", true, spellCapacity, knownSpells);
    }

    public WizardTower(String name, int magicPower, String location, boolean isActive, int spellCapacity, String[] knownSpells) {
        super(name, magicPower, location, isActive);
        this.spellCapacity = spellCapacity;
        this.knownSpells = knownSpells;
    }

    public void castMagicSpell() {
        System.out.println(structureName + " casts " + knownSpells[0] + "!");
    }
}

// EnchantedCastle class
class EnchantedCastle extends MagicalStructure {
    private int defenseRating;
    private boolean hasDrawbridge;

    public EnchantedCastle(String name) {
        this(name, 100, "Castle Hill", true, 50, true);
    }

    public EnchantedCastle(String name, int defenseRating, boolean hasDrawbridge) {
        this(name, 100, "Castle Hill", true, defenseRating, hasDrawbridge);
    }

    public EnchantedCastle(String name, int magicPower, String location, boolean isActive, int defenseRating, boolean hasDrawbridge) {
        super(name, magicPower, location, isActive);
        this.defenseRating = defenseRating;
        this.hasDrawbridge = hasDrawbridge;
    }

    public void castMagicSpell() {
        System.out.println(structureName + " defends with magical barriers!");
    }
}

// MysticLibrary class
class MysticLibrary extends MagicalStructure {
    private int bookCount;
    private String ancientLanguage;

    public MysticLibrary(String name) {
        this(name, 80, "Library District", true, 100, "Ancient Runes");
    }

    public MysticLibrary(String name, int bookCount, String ancientLanguage) {
        this(name, 80, "Library District", true, bookCount, ancientLanguage);
    }

    public MysticLibrary(String name, int magicPower, String location, boolean isActive, int bookCount, String ancientLanguage) {
        super(name, magicPower, location, isActive);
        this.bookCount = bookCount;
        this.ancientLanguage = ancientLanguage;
    }

    public void castMagicSpell() {
        System.out.println(structureName + " uses knowledge to amplify magic!");
    }
}

// DragonLair class
class DragonLair extends MagicalStructure {
    private String dragonType;
    private int treasureValue;

    public DragonLair(String name, String dragonType) {
        this(name, 150, "Dragon Mountain", true, dragonType, 1000);
    }

    public DragonLair(String name, String dragonType, int treasureValue) {
        this(name, 150, "Dragon Mountain", true, dragonType, treasureValue);
    }

    public DragonLair(String name, int magicPower, String location, boolean isActive, String dragonType, int treasureValue) {
        super(name, magicPower, location, isActive);
        this.dragonType = dragonType;
        this.treasureValue = treasureValue;
    }

    public void castMagicSpell() {
        System.out.println(structureName + " unleashes dragon fire!");
    }
}

// KingdomManager
class KingdomManager {

    public static boolean canStructuresInteract(MagicalStructure s1, MagicalStructure s2) {
        // WizardTower + Library = Knowledge boost
        if ((s1 instanceof WizardTower && s2 instanceof MysticLibrary) ||
            (s1 instanceof MysticLibrary && s2 instanceof WizardTower)) {
            System.out.println("Knowledge boost activated between " + s1.structureName + " and " + s2.structureName);
            return true;
        }
        // Castle + DragonLair = Dragon guard
        if ((s1 instanceof EnchantedCastle && s2 instanceof DragonLair) ||
            (s1 instanceof DragonLair && s2 instanceof EnchantedCastle)) {
            System.out.println("Dragon guard activated between " + s1.structureName + " and " + s2.structureName);
            return true;
        }
        return false;
    }

    public static String performMagicBattle(MagicalStructure attacker, MagicalStructure defender) {
        if (attacker.magicPower > defender.magicPower) return attacker.structureName + " wins!";
        else if (attacker.magicPower < defender.magicPower) return defender.structureName + " wins!";
        else return "It's a tie!";
    }

    public static int calculateKingdomMagicPower(MagicalStructure[] structures) {
        int total = 0;
        for (MagicalStructure s : structures) total += s.magicPower;
        return total;
    }

    public static void main(String[] args) {
        System.out.println("=== MEDIEVAL KINGDOM SIMULATION ===");

        MagicalStructure tower1 = new WizardTower("Arcane Spire");
        MagicalStructure library1 = new MysticLibrary("Grand Library", 200, "Ancient Tongue");
        MagicalStructure castle1 = new EnchantedCastle("Royal Castle", 100, true);
        MagicalStructure dragonLair1 = new DragonLair("Fire Peak", "Red Dragon", 5000);

        MagicalStructure[] kingdom = {tower1, library1, castle1, dragonLair1};

        // Interactions
        canStructuresInteract(tower1, library1);
        canStructuresInteract(castle1, dragonLair1);

        // Magic battles
        System.out.println(performMagicBattle(tower1, dragonLair1));
        System.out.println("Total kingdom magic power: " + calculateKingdomMagicPower(kingdom));

        // Cast magic
        for (MagicalStructure s : kingdom) s.castMagicSpell();
    }
}
