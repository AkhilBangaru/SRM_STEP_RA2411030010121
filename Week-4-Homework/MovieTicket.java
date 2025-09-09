// Question - 1

public class MovieTicket {
    String movieName;
    String theatreName;
    int seatNumber;
    double price;

    // Default constructor
    public MovieTicket() {
        this("Unknown", "Unknown", 0, 0.0);
    }

    // Constructor with movie name
    public MovieTicket(String movieName) {
        this(movieName, "Unknown", 0, 200.0);
    }

    // Constructor with movie name and seat number
    public MovieTicket(String movieName, int seatNumber) {
        this(movieName, "PVR", seatNumber, 200.0);
    }

    // Full constructor
    public MovieTicket(String movieName, String theatreName, int seatNumber, double price) {
        this.movieName = movieName;
        this.theatreName = theatreName;
        this.seatNumber = seatNumber;
        this.price = price;
    }

    public void printTicket() {
        System.out.println("\n--- Movie Ticket ---");
        System.out.println("Movie: " + movieName);
        System.out.println("Theatre: " + theatreName);
        System.out.println("Seat No.: " + seatNumber);
        System.out.println("Price: ₹" + price);
    }

    public static void main(String[] args) {
        MovieTicket ticket1 = new MovieTicket();
        MovieTicket ticket2 = new MovieTicket("Avatar 2");
        MovieTicket ticket3 = new MovieTicket("Inception", 15);
        MovieTicket ticket4 = new MovieTicket("Titanic", "INOX", 20, 350);

        ticket1.printTicket();
        ticket2.printTicket();
        ticket3.printTicket();
        ticket4.printTicket();
    }
}
