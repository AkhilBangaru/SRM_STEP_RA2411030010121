// Question - 1
public class Book {
    String title;
    String author;
    double price;

    // Default constructor
    Book() {
        this.title = "Unknown Title";
        this.author = "Unknown Author";
        this.price = 0.0;
    }

    // Parameterized constructor
    Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // Display method
    void display() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Price: " + price);
        System.out.println("-------------------------");
    }

    public static void main(String[] args) {
        // Create book1 using default constructor
        Book book1 = new Book();

        // Create book2 using parameterized constructor
        Book book2 = new Book("The Alchemist", "Paulo Coelho", 499.99);

        // Display both books
        System.out.println("Book 1 Details:");
        book1.display();

        System.out.println("Book 2 Details:");
        book2.display();
    }
}
