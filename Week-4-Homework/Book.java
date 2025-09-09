// Question - 3

public class Book {
    String title;
    String author;
    String isbn;
    boolean isAvailable;

    // Default constructor → empty book
    public Book() {
        this("", "", "", true);
    }

    // Constructor with title and author
    public Book(String title, String author) {
        this(title, author, "Unknown", true);
    }

    // Constructor with all details
    public Book(String title, String author, String isbn, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = isAvailable;
    }

    // Borrow book → sets available = false
    public void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println(title + " has been borrowed.");
        } else {
            System.out.println(title + " is already borrowed.");
        }
    }

    // Return book → sets available = true
    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println(title + " has been returned.");
        } else {
            System.out.println(title + " was not borrowed.");
        }
    }

    // Display book info
    public void displayBookInfo() {
        System.out.println("\n--- Book Info ---");
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Availability: " + (isAvailable ? "Available" : "Borrowed"));
    }

    public static void main(String[] args) {
        Book book1 = new Book(); // Default
        Book book2 = new Book("1984", "George Orwell"); // Title + author
        Book book3 = new Book("The Alchemist", "Paulo Coelho", "123-456789", true); // Full constructor

        // Borrow and return books
        book2.borrowBook();
        book2.borrowBook(); // Try borrowing again
        book2.returnBook();
        book2.returnBook(); // Try returning again

        // Display all books
        book1.displayBookInfo();
        book2.displayBookInfo();
        book3.displayBookInfo();
    }
}
