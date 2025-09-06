// File: Assignment_5_akhil.java
// Author: Bangaru Chaitanya Venkata Sai Akhil

import java.util.*;

class Book {
    String bookId;
    String title;
    String author;
    String isbn;
    String category;
    boolean isIssued;
    String issueDate;
    String dueDate;

    int issueCount = 0; // for popularity tracking

    public Book(String bookId, String title, String author, String isbn, String category) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.isIssued = false;
        this.issueDate = "";
        this.dueDate = "";
    }
}

class Member {
    String memberId;
    String memberName;
    String memberType;  // Student, Faculty, General
    Book[] booksIssued;
    double totalFines;
    String membershipDate;

    // Static variables
    static int totalBooks = 0;
    static int totalMembers = 0;
    static String libraryName = "OpenAI Central Library";
    static double finePerDay = 2.0;
    static int maxBooksAllowed = 3;

    public Member(String memberId, String memberName, String memberType, String membershipDate) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberType = memberType;
        this.membershipDate = membershipDate;
        this.booksIssued = new Book[maxBooksAllowed];
        this.totalFines = 0.0;
        totalMembers++;
    }

    // Issue a book
    public boolean issueBook(Book book, String issueDate, String dueDate) {
        if (book.isIssued) {
            System.out.println("❌ Book already issued: " + book.title);
            return false;
        }
        for (int i = 0; i < booksIssued.length; i++) {
            if (booksIssued[i] == null) {
                booksIssued[i] = book;
                book.isIssued = true;
                book.issueDate = issueDate;
                book.dueDate = dueDate;
                book.issueCount++;
                System.out.println("✅ Book issued: " + book.title + " to " + memberName);
                return true;
            }
        }
        System.out.println("❌ Member has reached max allowed books.");
        return false;
    }

    // Return a book
    public boolean returnBook(Book book, String returnDate) {
        for (int i = 0; i < booksIssued.length; i++) {
            if (booksIssued[i] != null && booksIssued[i].bookId.equals(book.bookId)) {
                // Calculate fine
                double fine = calculateFine(book.dueDate, returnDate);
                totalFines += fine;
                System.out.println("📕 Book returned: " + book.title + " | Fine: " + fine);

                // Reset book
                booksIssued[i] = null;
                book.isIssued = false;
                book.issueDate = "";
                book.dueDate = "";
                return true;
            }
        }
        System.out.println("❌ Book not found in issued list.");
        return false;
    }

    // Fine calculation
    public double calculateFine(String dueDate, String returnDate) {
        int due = Integer.parseInt(dueDate.replaceAll("[^0-9]", ""));
        int ret = Integer.parseInt(returnDate.replaceAll("[^0-9]", ""));
        int overdueDays = ret - due;
        return (overdueDays > 0) ? overdueDays * finePerDay : 0.0;
    }

    // Renew a book
    public boolean renewBook(Book book, String newDueDate) {
        for (Book b : booksIssued) {
            if (b != null && b.bookId.equals(book.bookId)) {
                b.dueDate = newDueDate;
                System.out.println("🔄 Book renewed: " + book.title + " | New Due Date: " + newDueDate);
                return true;
            }
        }
        System.out.println("❌ Cannot renew. Book not found.");
        return false;
    }

    // Search books
    public static List<Book> searchBooks(Book[] allBooks, String keyword) {
        List<Book> results = new ArrayList<>();
        for (Book b : allBooks) {
            if (b != null && (b.title.contains(keyword) || b.author.contains(keyword) || b.category.contains(keyword))) {
                results.add(b);
            }
        }
        return results;
    }

    // Reserve a book (just a placeholder logic)
    public boolean reserveBook(Book book) {
        if (!book.isIssued) {
            System.out.println("✅ Book is available, no need to reserve: " + book.title);
            return false;
        }
        System.out.println("📌 Reservation placed for: " + book.title + " by " + memberName);
        return true;
    }

    // Static reporting
    public static void generateLibraryReport(Book[] allBooks, Member[] allMembers) {
        System.out.println("\n=== Library Report (" + libraryName + ") ===");
        System.out.println("Total Members: " + totalMembers);
        System.out.println("Total Books: " + allBooks.length);

        // Overdue books
        List<Book> overdue = getOverdueBooks(allBooks, "20250906");
        System.out.println("Overdue Books Count: " + overdue.size());

        // Popular books
        List<Book> popular = getMostPopularBooks(allBooks);
        if (!popular.isEmpty()) {
            System.out.println("Most Popular Book: " + popular.get(0).title + " (Issued " + popular.get(0).issueCount + " times)");
        }
    }

    public static List<Book> getOverdueBooks(Book[] allBooks, String currentDate) {
        List<Book> overdue = new ArrayList<>();
        int today = Integer.parseInt(currentDate.replaceAll("[^0-9]", ""));
        for (Book b : allBooks) {
            if (b != null && b.isIssued) {
                int due = Integer.parseInt(b.dueDate.replaceAll("[^0-9]", ""));
                if (today > due) overdue.add(b);
            }
        }
        return overdue;
    }

    public static List<Book> getMostPopularBooks(Book[] allBooks) {
        List<Book> list = Arrays.asList(allBooks);
        list.sort((a, b) -> Integer.compare(b.issueCount, a.issueCount));
        return list;
    }
}

public class Assignment_5_akhil {
    public static void main(String[] args) {
        // Books
        Book b1 = new Book("B001", "Java Programming", "James Gosling", "1111", "Programming");
        Book b2 = new Book("B002", "Python Essentials", "Guido van Rossum", "2222", "Programming");
        Book b3 = new Book("B003", "Data Structures", "Robert Lafore", "3333", "CS");

        Book[] allBooks = {b1, b2, b3};

        // Members
        Member m1 = new Member("M001", "Akhil", "Student", "20250101");
        Member m2 = new Member("M002", "Ravi", "Faculty", "20250102");

        Member[] allMembers = {m1, m2};

        // Workflow
        m1.issueBook(b1, "20250901", "20250905");
        m1.issueBook(b2, "20250901", "20250905");

        m1.returnBook(b1, "20250907"); // 2 days late => Fine = 4
        m1.renewBook(b2, "20250910");

        // Searching
        List<Book> searchResults = Member.searchBooks(allBooks, "Python");
        System.out.println("\n🔍 Search Results for 'Python':");
        for (Book b : searchResults) {
            System.out.println(b.title + " by " + b.author);
        }

        // Reservation
        m2.reserveBook(b2);

        // Generate Report
        Member.generateLibraryReport(allBooks, allMembers);
    }
}
