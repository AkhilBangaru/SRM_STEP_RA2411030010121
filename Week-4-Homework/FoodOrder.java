// Question - 4

public class FoodOrder {
    String customerName;
    String foodItem;
    int quantity;
    double price;
    static final double FIXED_RATE = 150.0; // default price per item

    // Default constructor → "Unknown" order
    public FoodOrder() {
        this("Unknown Customer", "Unknown Food", 0, 0.0);
    }

    // Constructor with food item → quantity = 1, price = default
    public FoodOrder(String foodItem) {
        this("Unknown Customer", foodItem, 1, FIXED_RATE);
    }

    // Constructor with food item and quantity → price = quantity × fixedRate
    public FoodOrder(String foodItem, int quantity) {
        this("Unknown Customer", foodItem, quantity, quantity * FIXED_RATE);
    }

    // Full constructor
    public FoodOrder(String customerName, String foodItem, int quantity, double price) {
        this.customerName = customerName;
        this.foodItem = foodItem;
        this.quantity = quantity;
        this.price = price;
    }

    // Method to print bill
    public void printBill() {
        System.out.println("\n--- Food Order Bill ---");
        System.out.println("Customer: " + customerName);
        System.out.println("Food Item: " + foodItem);
        System.out.println("Quantity: " + quantity);
        System.out.println("Total Price: ₹" + price);
    }

    public static void main(String[] args) {
        FoodOrder order1 = new FoodOrder(); // Default
        FoodOrder order2 = new FoodOrder("Burger"); // Food item only
        FoodOrder order3 = new FoodOrder("Pizza", 3); // Food + quantity
        FoodOrder order4 = new FoodOrder("Alice", "Pasta", 2, 350); // Full constructor

        // Print all bills
        order1.printBill();
        order2.printBill();
        order3.printBill();
        order4.printBill();
    }
}
