// File: Assignment_2_Akhil.java
// Author: Bangaru Chaitanya Venkata Sai Akhil

import java.util.Scanner;

class Product {
    String productId;
    String productName;
    double price;
    String category;
    int stockQuantity;

    // Static variables
    static int totalProducts = 0;
    static String[] categories = {"Electronics", "Clothing", "Books", "Groceries"};

    // Constructor
    public Product(String productId, String productName, double price, String category, int stockQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
        totalProducts++;
    }

    // Static method: find product by ID
    public static Product findProductById(Product[] products, String productId) {
        for (Product p : products) {
            if (p != null && p.productId.equals(productId)) {
                return p;
            }
        }
        return null;
    }

    // Static method: get products by category
    public static void getProductsByCategory(Product[] products, String category) {
        System.out.println("\nProducts in category: " + category);
        for (Product p : products) {
            if (p != null && p.category.equalsIgnoreCase(category)) {
                System.out.println(p.productId + " - " + p.productName + " - Rs." + p.price + " (Stock: " + p.stockQuantity + ")");
            }
        }
    }
}

class ShoppingCart {
    String cartId;
    String customerName;
    Product[] products;
    int[] quantities;
    double cartTotal;
    int itemCount;

    // Constructor
    public ShoppingCart(String cartId, String customerName) {
        this.cartId = cartId;
        this.customerName = customerName;
        this.products = new Product[50];  // fixed size
        this.quantities = new int[50];
        this.cartTotal = 0.0;
        this.itemCount = 0;
    }

    // Add product
    public void addProduct(Product product, int quantity) {
        if (product == null) {
            System.out.println("Product not found!");
            return;
        }
        if (quantity > product.stockQuantity) {
            System.out.println("Not enough stock available for " + product.productName);
            return;
        }
        products[itemCount] = product;
        quantities[itemCount] = quantity;
        itemCount++;
        product.stockQuantity -= quantity;
        calculateTotal();
        System.out.println(product.productName + " added to cart (Qty: " + quantity + ")");
    }

    // Remove product
    public void removeProduct(String productId) {
        for (int i = 0; i < itemCount; i++) {
            if (products[i].productId.equals(productId)) {
                System.out.println(products[i].productName + " removed from cart.");
                products[i].stockQuantity += quantities[i]; // return stock
                for (int j = i; j < itemCount - 1; j++) {
                    products[j] = products[j + 1];
                    quantities[j] = quantities[j + 1];
                }
                itemCount--;
                calculateTotal();
                return;
            }
        }
        System.out.println("Product not found in cart.");
    }

    // Calculate total
    public void calculateTotal() {
        cartTotal = 0.0;
        for (int i = 0; i < itemCount; i++) {
            cartTotal += products[i].price * quantities[i];
        }
    }

    // Display cart
    public void displayCart() {
        System.out.println("\n--- Shopping Cart ---");
        System.out.println("Customer: " + customerName);
        for (int i = 0; i < itemCount; i++) {
            System.out.println(products[i].productName + " x " + quantities[i] + " = Rs." + (products[i].price * quantities[i]));
        }
        System.out.println("Total: Rs." + cartTotal);
    }

    // Checkout
    public void checkout() {
        if (itemCount == 0) {
            System.out.println("Cart is empty!");
            return;
        }
        System.out.println("\nCheckout successful! Total bill: Rs." + cartTotal);
        itemCount = 0;
        cartTotal = 0;
    }
}

public class Assignment_2_akhil {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create 10 products
        Product[] products = new Product[10];
        products[0] = new Product("P101", "Smartphone", 15000, "Electronics", 10);
        products[1] = new Product("P102", "Laptop", 55000, "Electronics", 5);
        products[2] = new Product("P103", "Headphones", 2000, "Electronics", 20);
        products[3] = new Product("P201", "T-Shirt", 500, "Clothing", 30);
        products[4] = new Product("P202", "Jeans", 1200, "Clothing", 15);
        products[5] = new Product("P301", "Novel", 300, "Books", 40);
        products[6] = new Product("P302", "Notebook", 50, "Books", 100);
        products[7] = new Product("P401", "Rice", 60, "Groceries", 200);
        products[8] = new Product("P402", "Milk", 40, "Groceries", 50);
        products[9] = new Product("P403", "Snacks", 100, "Groceries", 80);

        // Create shopping cart
        ShoppingCart cart = new ShoppingCart("C001", "Akhil");

        int choice;
        do {
            System.out.println("\n===== Online Shopping Menu =====");
            System.out.println("1. Browse Products");
            System.out.println("2. Browse by Category");
            System.out.println("3. Add Product to Cart");
            System.out.println("4. Remove Product from Cart");
            System.out.println("5. View Cart");
            System.out.println("6. Checkout");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nAvailable Products:");
                    for (Product p : products) {
                        System.out.println(p.productId + " - " + p.productName + " - Rs." + p.price + " (Stock: " + p.stockQuantity + ")");
                    }
                    break;

                case 2:
                    System.out.print("Enter category (Electronics/Clothing/Books/Groceries): ");
                    String cat = sc.next();
                    Product.getProductsByCategory(products, cat);
                    break;

                case 3:
                    System.out.print("Enter Product ID to add: ");
                    String pid = sc.next();
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();
                    Product prod = Product.findProductById(products, pid);
                    cart.addProduct(prod, qty);
                    break;

                case 4:
                    System.out.print("Enter Product ID to remove: ");
                    String rid = sc.next();
                    cart.removeProduct(rid);
                    break;

                case 5:
                    cart.displayCart();
                    break;

                case 6:
                    cart.checkout();
                    break;

                case 7:
                    System.out.println("Exiting... Thank you for shopping!");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 7);

        sc.close();
    }
}
