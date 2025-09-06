// File: Assignment_3_akhil.java
// Author: Bangaru Chaitanya Venkata Sai Akhil

import java.util.*;

class Room {
    String roomNumber;
    String roomType;
    double pricePerNight;
    boolean isAvailable;
    int maxOccupancy;

    public Room(String roomNumber, String roomType, double pricePerNight, int maxOccupancy) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.isAvailable = true;
        this.maxOccupancy = maxOccupancy;
    }
}

class Guest {
    String guestId;
    String guestName;
    String phoneNumber;
    String email;
    String[] bookingHistory;
    int historyCount;

    public Guest(String guestId, String guestName, String phoneNumber, String email) {
        this.guestId = guestId;
        this.guestName = guestName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bookingHistory = new String[20];
        this.historyCount = 0;
    }

    public void addBookingToHistory(String bookingId) {
        if (historyCount < bookingHistory.length) {
            bookingHistory[historyCount++] = bookingId;
        }
    }
}

class Booking {
    String bookingId;
    Guest guest;
    Room room;
    String checkInDate;
    String checkOutDate;
    double totalAmount;

    // Static variables
    static int totalBookings = 0;
    static double hotelRevenue = 0.0;
    static String hotelName = "Grand OpenAI Hotel";

    public Booking(String bookingId, Guest guest, Room room, String checkInDate, String checkOutDate, double totalAmount) {
        this.bookingId = bookingId;
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalAmount = totalAmount;

        totalBookings++;
        hotelRevenue += totalAmount;
    }

    // Reservation methods
    public static Booking makeReservation(String bookingId, Guest guest, Room room,
                                          String checkInDate, String checkOutDate, int nights) {
        if (!room.isAvailable) {
            System.out.println("Room " + room.roomNumber + " is not available.");
            return null;
        }
        double total = room.pricePerNight * nights;
        room.isAvailable = false;
        Booking booking = new Booking(bookingId, guest, room, checkInDate, checkOutDate, total);
        guest.addBookingToHistory(bookingId);
        System.out.println("Reservation successful! Booking ID: " + bookingId);
        return booking;
    }

    public static void cancelReservation(Booking booking) {
        if (booking == null) return;
        booking.room.isAvailable = true;
        hotelRevenue -= booking.totalAmount;
        totalBookings--;
        System.out.println("Booking " + booking.bookingId + " cancelled successfully.");
    }

    public static boolean checkAvailability(Room room) {
        return room.isAvailable;
    }

    public static double calculateBill(Room room, int nights) {
        return room.pricePerNight * nights;
    }

    // Static reporting methods
    public static double getOccupancyRate(Room[] rooms) {
        int totalRooms = rooms.length;
        int occupied = 0;
        for (Room r : rooms) {
            if (!r.isAvailable) occupied++;
        }
        return (occupied * 100.0) / totalRooms;
    }

    public static double getTotalRevenue() {
        return hotelRevenue;
    }

    public static String getMostPopularRoomType(Room[] rooms, Booking[] bookings, int bookingCount) {
        Map<String, Integer> roomTypeCount = new HashMap<>();
        for (int i = 0; i < bookingCount; i++) {
            String type = bookings[i].room.roomType;
            roomTypeCount.put(type, roomTypeCount.getOrDefault(type, 0) + 1);
        }
        String popular = null;
        int max = 0;
        for (String type : roomTypeCount.keySet()) {
            if (roomTypeCount.get(type) > max) {
                max = roomTypeCount.get(type);
                popular = type;
            }
        }
        return popular == null ? "No bookings yet" : popular;
    }
}

public class Assignment_3_akhil {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Rooms
        Room[] rooms = {
            new Room("R101", "Single", 2000, 1),
            new Room("R102", "Double", 3500, 2),
            new Room("R103", "Suite", 7000, 4),
            new Room("R104", "Double", 3500, 2),
            new Room("R105", "Suite", 7000, 4)
        };

        // Guests
        Guest guest1 = new Guest("G001", "Akhil", "9999999999", "akhil@mail.com");
        Guest guest2 = new Guest("G002", "Ravi", "8888888888", "ravi@mail.com");

        // Bookings
        Booking[] bookings = new Booking[50];
        int bookingCount = 0;

        int choice;
        do {
            System.out.println("\n===== Hotel Reservation Menu =====");
            System.out.println("1. View Rooms");
            System.out.println("2. Make Reservation");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. View Reports");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nAvailable Rooms:");
                    for (Room r : rooms) {
                        System.out.println(r.roomNumber + " - " + r.roomType + " - Rs." +
                                r.pricePerNight + " (Available: " + r.isAvailable + ")");
                    }
                    break;

                case 2:
                    System.out.print("Enter Guest ID (G001/G002): ");
                    String gid = sc.next();
                    Guest g = gid.equals("G001") ? guest1 : guest2;

                    System.out.print("Enter Room Number: ");
                    String rn = sc.next();
                    Room selected = null;
                    for (Room r : rooms) {
                        if (r.roomNumber.equals(rn)) selected = r;
                    }

                    System.out.print("Enter Check-In Date (DD-MM-YYYY): ");
                    String in = sc.next();
                    System.out.print("Enter Check-Out Date (DD-MM-YYYY): ");
                    String out = sc.next();
                    System.out.print("Enter Number of Nights: ");
                    int nights = sc.nextInt();

                    if (selected != null) {
                        String bid = "B" + (bookingCount + 1);
                        Booking b = Booking.makeReservation(bid, g, selected, in, out, nights);
                        if (b != null) bookings[bookingCount++] = b;
                    } else {
                        System.out.println("Invalid room number!");
                    }
                    break;

                case 3:
                    System.out.print("Enter Booking ID to cancel: ");
                    String bid = sc.next();
                    Booking target = null;
                    int idx = -1;
                    for (int i = 0; i < bookingCount; i++) {
                        if (bookings[i].bookingId.equals(bid)) {
                            target = bookings[i];
                            idx = i;
                        }
                    }
                    if (target != null) {
                        Booking.cancelReservation(target);
                        for (int i = idx; i < bookingCount - 1; i++) {
                            bookings[i] = bookings[i + 1];
                        }
                        bookingCount--;
                    } else {
                        System.out.println("Booking not found!");
                    }
                    break;

                case 4:
                    System.out.println("\n--- Hotel Reports ---");
                    System.out.println("Occupancy Rate: " + Booking.getOccupancyRate(rooms) + "%");
                    System.out.println("Total Revenue: Rs." + Booking.getTotalRevenue());
                    System.out.println("Most Popular Room Type: " +
                            Booking.getMostPopularRoomType(rooms, bookings, bookingCount));
                    break;

                case 5:
                    System.out.println("Exiting... Thank you for using " + Booking.hotelName);
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 5);

        sc.close();
    }
}
